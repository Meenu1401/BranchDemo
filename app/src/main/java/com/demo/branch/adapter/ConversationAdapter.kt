package com.demo.branch.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.branch.R
import com.demo.branch.entity.ChatItem
import com.demo.branch.extensions.changeDateFormat
import kotlinx.android.synthetic.main.chat_list_item.view.*

class ConversationAdapter(private val chatList: List<ChatItem>) :
    RecyclerView.Adapter<ConversationAdapter.ConversationItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationItemHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.conversation_list_item, parent, false)
        return ConversationItemHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("meenu", " " + chatList.size)
        return chatList.size
    }

    override fun onBindViewHolder(holder: ConversationItemHolder, position: Int) {
        val item = this.chatList[position]
        holder.userId.text = item.userId
        holder.message.text = item.message
        holder.timestamp.text = changeDateFormat(item.timeStamp)
        holder.itemView.tag = item
    }


    class ConversationItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userId: TextView = itemView.userid
        val message: TextView = itemView.message
        val timestamp: TextView = itemView.timestamp
    }

}