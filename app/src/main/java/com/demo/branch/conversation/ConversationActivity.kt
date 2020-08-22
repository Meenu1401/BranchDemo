package com.demo.branch.conversation

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.branch.BaseActivity
import com.demo.branch.MyApplication
import com.demo.branch.R
import com.demo.branch.adapter.ConversationAdapter
import com.demo.branch.controller.ConversationController
import com.demo.branch.entity.ChatItem
import com.demo.branch.entity.ConversationItem
import kotlinx.android.synthetic.main.activity_chat_thread.progressBar
import kotlinx.android.synthetic.main.activity_chat_thread.recyclerView
import kotlinx.android.synthetic.main.activity_conversation.*
import javax.inject.Inject


class ConversationActivity : BaseActivity(), View.OnClickListener {

    private lateinit var conversationComponent: ConversationComponent

    private var conversationItem: ConversationItem? = null

    @Inject
    lateinit var controller: ConversationController

    var conversationAdapter: ConversationAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        setupInjection()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)
        conversationItem = (intent.getParcelableExtra("conversationItem") as? ConversationItem)
        conversationItem?.let { controller.fetchConversations(it).disposedBy(compositeDisposable) }
        btn_send.setOnClickListener(this)
        initialiseObservers()

    }

    private fun setupInjection() {
        conversationComponent = (application as MyApplication).appComponent
            .conversationComponent().create()
        conversationComponent.inject(this)
    }

    private fun initialiseObservers() {
        observeConversation()
        observeProgressBarVisibility()
        observeNewMessage()
        observeFailureMessage()
    }

    private fun observeFailureMessage() {
        controller.getViewData().failureMessage.subscribe {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }.disposedBy(compositeDisposable)
    }

    private fun observeNewMessage() {
        controller.getViewData().newMessage.subscribe {
            controller.getViewData().chatItemsList.add(it)
            notifyAndScrollToBottom()
        }.disposedBy(compositeDisposable)
    }

    private fun notifyAndScrollToBottom() {
        val size = controller.getViewData().chatItemsList.size
        conversationAdapter?.notifyItemInserted(size)
        recyclerView.adapter?.itemCount?.let { recyclerView.smoothScrollToPosition(it) }
    }


    private fun observeProgressBarVisibility() {
        controller.getViewData().progressBarState.subscribe {
            if (it)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE
        }.disposedBy(compositeDisposable)
    }


    private fun observeConversation() {
        controller.getViewData().conversationList
            .subscribe {
                setupRecyclerView(it)
            }.disposedBy(compositeDisposable)
    }

    private fun setupRecyclerView(chatList: List<ChatItem>) {
        conversationAdapter = ConversationAdapter(chatList)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = GridLayoutManager(this, GridLayoutManager.VERTICAL)
        recyclerView.adapter = conversationAdapter
        recyclerView.adapter?.itemCount?.let { recyclerView.smoothScrollToPosition(it) }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_send)
            if (!TextUtils.isEmpty(newMessage.text))
                conversationItem?.threadId?.let { it1 ->
                    ConversationItem(
                        it1,
                        newMessage.text.toString()
                    )
                }
                    ?.let { it2 ->
                        controller.postMessage(it2)
                        newMessage.text.clear()
                    }
    }

}