package com.demo.branch.registration.enterdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.demo.branch.R
import com.demo.branch.registration.RegistrationActivity
import com.demo.branch.viewdata.RegistrationViewData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class EnterDetailsFragment : Fragment() {

    @Inject
    lateinit var registrationViewData: RegistrationViewData

    @Inject
    lateinit var enterDetailsViewData: EnterDetailsViewData

    private lateinit var errorTextView: TextView
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    lateinit var compositeDisposable: CompositeDisposable

    override fun onAttach(context: Context) {
        super.onAttach(context)
        compositeDisposable = CompositeDisposable()
        (activity as RegistrationActivity).registrationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_enter_details, container, false)
        setupViews(view)
        initialiseObservers()
        return view
    }

    private fun initialiseObservers() {
        enterDetailsViewData.enterDetailsState.subscribe {
            when (it) {
                is EnterDetailsSuccess -> {
                    val username = usernameEditText.text.toString()
                    val password = passwordEditText.text.toString()
                    registrationViewData.updateUserData(username, password)
                    (activity as RegistrationActivity).onDetailsEntered()
                }
                is EnterDetailsError -> {
                    errorTextView.text = it.error
                    errorTextView.visibility = View.VISIBLE
                }
            }
        }.disposedBy(compositeDisposable)
    }

    private fun setupViews(view: View) {
        errorTextView = view.findViewById(R.id.error)
        usernameEditText = view.findViewById(R.id.username)
        usernameEditText.doOnTextChanged { _, _, _, _ -> errorTextView.visibility = View.INVISIBLE }
        passwordEditText = view.findViewById(R.id.password)
        passwordEditText.doOnTextChanged { _, _, _, _ -> errorTextView.visibility = View.INVISIBLE }

        view.findViewById<Button>(R.id.next).setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            enterDetailsViewData.validateInput(username, password)
        }
    }
}

private fun Disposable.disposedBy(disposables: CompositeDisposable) {
    disposables.add(this)
}

sealed class EnterDetailsViewState
object EnterDetailsSuccess : EnterDetailsViewState()
data class EnterDetailsError(val error: String) : EnterDetailsViewState()
