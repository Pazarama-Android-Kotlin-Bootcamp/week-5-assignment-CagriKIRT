package com.example.week_5_assignment_cagrikirt.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.week_5_assignment_cagrikirt.databinding.FragmentPostsBinding
import com.example.week_5_assignment_cagrikirt.ui.model.DataState
import com.example.week_5_assignment_cagrikirt.ui.model.post.PostDTO
import com.example.week_5_assignment_cagrikirt.ui.posts.adapter.OnPostClickListener
import com.example.week_5_assignment_cagrikirt.ui.posts.adapter.PostsAdapter
import com.example.week_5_assignment_cagrikirt.ui.posts.viewmodel.PostViewEvent
import com.example.week_5_assignment_cagrikirt.ui.posts.viewmodel.PostsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment(), OnPostClickListener {

    private lateinit var binding:FragmentPostsBinding
    private val viewModel by viewModels<PostsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.postLiveData.observe(viewLifecycleOwner){
            when(it){
                is DataState.Success -> {

                    it.data?.let { safeData->
                        binding.rvPostsList.adapter = PostsAdapter(this).apply {
                            submitList(safeData)
                        }
                    } ?: run {
                        Toast.makeText(requireContext(),"No data", Toast.LENGTH_SHORT).show()
                    }
                }
                is DataState.Error -> {

                    Snackbar.make(binding.root,it.message,Snackbar.LENGTH_LONG).show()

                }
                is DataState.Loading -> {

                }
            }
        }

        viewModel.eventStateLiveData.observe(viewLifecycleOwner){
            when (it) {
                is PostViewEvent.ShowMessage -> {}
                is PostViewEvent.NavigateToDetail -> {}
            }
        }
    }

    override fun onPostClick(post: PostDTO) {
        viewModel.onFavoritePost(post)
    }


}