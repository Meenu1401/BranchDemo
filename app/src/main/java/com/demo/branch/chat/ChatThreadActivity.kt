package com.demo.branch.chat

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.branch.BaseActivity
import com.demo.branch.MyApplication
import com.demo.branch.R
import com.demo.branch.adapter.ChatThreadAdapter
import com.demo.branch.controller.ChatController
import com.demo.branch.conversation.ConversationActivity
import com.demo.branch.entity.ChatItem
import com.demo.branch.entity.ConversationItem
import kotlinx.android.synthetic.main.activity_chat_thread.*
import javax.inject.Inject

class ChatThreadActivity : BaseActivity(), ChatThreadAdapter.OnItemSelectListener {

    private lateinit var chatComponent: ChatComponent

    @Inject
    lateinit var controller: ChatController

    override fun onCreate(savedInstanceState: Bundle?) {
        chatComponent = (application as MyApplication).appComponent
            .chatComponent().create()
        chatComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_thread)
        controller.fetchChats().disposedBy(compositeDisposable)
        initialiseObservers()
    }

    private fun initialiseObservers() {
        observeChatList()
        observeProgressBarVisibility()
    }


    private fun observeProgressBarVisibility() {
        controller.getViewData()
            .observeProgressBarVisibility()
            .subscribe {
                if (it)
                    progressBar.visibility = View.VISIBLE
                else
                    progressBar.visibility = View.GONE
            }.disposedBy(compositeDisposable)
    }

    private fun observeChatList() {
        controller.getViewData().chatList.subscribe {
            setupRecyclerView(it)
        }.disposedBy(compositeDisposable)
    }

    private fun setupRecyclerView(chatList: List<ChatItem>) {
        val chatAdapter = ChatThreadAdapter(chatList)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = GridLayoutManager(this, GridLayoutManager.VERTICAL)
        recyclerView.adapter = chatAdapter
        chatAdapter.setListener(this)
    }

    override fun onItemClickListener(conversationItem: ConversationItem) {
        val intent = Intent(this, ConversationActivity::class.java)
        intent.putExtra("conversationItem", conversationItem)
        startActivity(intent)
    }
}


