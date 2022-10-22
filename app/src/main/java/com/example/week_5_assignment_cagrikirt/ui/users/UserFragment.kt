package com.example.week_5_assignment_cagrikirt.ui.users

import ApiClient
import Post
import User
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week_5_assignment_cagrikirt.R
import com.example.week_5_assignment_cagrikirt.ui.posts.adapter.PostsAdapter
import com.example.week_5_assignment_cagrikirt.ui.users.adapter.UserAdapter
import retrofit2.Call
import retrofit2.Response


class UserFragment : Fragment() {

    private lateinit var userRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userRecyclerView = view.findViewById(R.id.user_recyclerview)
        getUser()
    }

    private fun getUser() {
        ApiClient.getApiService().getUsers().enqueue(object : retrofit2.Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    post?.let { safePosts ->
                        userRecyclerView.adapter = UserAdapter(safePosts);
                    }
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("deneme1", t.toString() + call.request().body.toString())
            }

        })
    }


}