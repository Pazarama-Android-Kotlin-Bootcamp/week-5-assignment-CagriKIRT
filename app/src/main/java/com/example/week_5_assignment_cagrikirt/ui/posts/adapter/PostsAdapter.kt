package com.example.week_5_assignment_cagrikirt.ui.posts.adapter

import com.example.week_5_assignment_cagrikirt.data.models.Post
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week_5_assignment_cagrikirt.R
import com.example.week_5_assignment_cagrikirt.data.database.PostRepository


class PostsAdapter(var posts: List<Post>, repository: PostRepository) :
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post_layout, parent, false)

        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTitle = view.findViewById<TextView>(R.id.tvTitleValue)
        private val tvBody = view.findViewById<TextView>(R.id.tvBodyTitle)
        private val imageView = view.findViewById<ImageView>(R.id.imageView)

        fun bind(post: Post) {
            tvTitle.text = post.title
            tvBody.text = post.body
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }


}