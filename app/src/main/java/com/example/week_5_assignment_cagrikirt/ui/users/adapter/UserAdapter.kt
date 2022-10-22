package com.example.week_5_assignment_cagrikirt.ui.users.adapter

import User
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week_5_assignment_cagrikirt.R

class UserAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var username: TextView = view.findViewById(R.id.user_name_txt)
        var userEmail: TextView = view.findViewById(R.id.user_email)

        fun bind(user: User) {
            username.text = user.name
            userEmail.text = user.email

        }

    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_user_layout, viewGroup, false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) {

        viewHolder.bind(users[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = users.size

}
