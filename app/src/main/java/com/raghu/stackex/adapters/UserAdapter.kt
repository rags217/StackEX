package com.raghu.stackex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.raghu.stackex.R
import com.raghu.stackex.models.User
import java.util.*

class UserAdapter(
    var mUsers: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    interface ClickListener {
        fun onItemClick(position: Int, v: View?)
    }

    fun setOnItemClickListener(clickListener: ClickListener?) {
        Companion.clickListener = clickListener
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var userIDTV: TextView
        var nameTV: TextView
        override fun onClick(v: View) {
            clickListener!!.onItemClick(
                adapterPosition,
                v
            )
        }

        init {
            userIDTV = itemView.findViewById(R.id.user_id_tv)
            nameTV = itemView.findViewById(R.id.user_name_tv)
            itemView.setOnClickListener(this)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.row_user, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val (user_id, display_name) = mUsers[position]
        holder.userIDTV.text = user_id.toString()
        holder.nameTV.text = display_name
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    companion object {
        private var clickListener: ClickListener? = null
    }

}