package com.demo.branch

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseFragment : Fragment() {
    var compositeDisposable: CompositeDisposable? = null

    private var activity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.activity = context
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compositeDisposable = CompositeDisposable()
    }


    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable?.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable?.dispose()
        compositeDisposable = null
        super.onDestroy()
    }

}