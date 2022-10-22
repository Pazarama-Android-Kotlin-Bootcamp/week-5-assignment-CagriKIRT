package com.example.week_5_assignment_cagrikirt.ui.posts

import Post
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week_5_assignment_cagrikirt.R
import com.example.week_5_assignment_cagrikirt.ui.posts.adapter.PostsAdapter
import retrofit2.Call
import retrofit2.Response

class PostsFragment : Fragment() {
    private lateinit var rvPostList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvPostList = view.findViewById(R.id.rvPostsList)
        getPosts()
    }

    private fun getPosts() {
        ApiClient.getApiService().getPosts().enqueue(object : retrofit2.Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    post?.let { safePosts ->
                        rvPostList.adapter = PostsAdapter().apply {
                            submitList(safePosts)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("deneme1", t.toString() + call.request().body.toString())
            }
        })
    }
}