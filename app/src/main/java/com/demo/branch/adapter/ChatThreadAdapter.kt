package com.demo.branch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.branch.R
import com.demo.branch.entity.ChatItem
import com.demo.branch.entity.ConversationItem
import com.demo.branch.extensions.changeDateFormat
import kotlinx.android.synthetic.main.chat_list_item.view.*

class ChatThreadAdapter(private val chatList: List<ChatItem>) :
    RecyclerView.Adapter<ChatThreadAdapter.ChatThreadHolder>() {

    private lateinit var onItemSelectListener: OnItemSelectListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatThreadHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.chat_list_item, parent, false)
        return ChatThreadHolder(view)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ChatThreadHolder, position: Int) {
        val item = this.chatList[position]
        holder.userId.text = item.userId
        holder.message.text = item.message
        holder.timestamp.text = changeDateFormat(item.timeStamp)
        holder.itemView.tag = item
    }

    fun setListener(onItemSelectListener: OnItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener
    }

    interface OnItemSelectListener {
        fun onItemClickListener(conversationItem: ConversationItem)
    }

    inner class ChatThreadHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userId: TextView = itemView.userid
        val message: TextView = itemView.message
        val timestamp: TextView = itemView.timestamp

        init {
            itemView.setOnClickListener {
                val item = itemView.tag as ChatItem
                onItemSelectListener.onItemClickListener(
                    ConversationItem(
                        item.threadId,
                        item.message
                    )
                )
            }
        }
    }

}