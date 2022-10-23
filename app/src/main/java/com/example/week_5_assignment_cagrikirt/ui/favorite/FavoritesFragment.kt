package com.example.week_5_assignment_cagrikirt.ui.favorite

import com.example.week_5_assignment_cagrikirt.ui.model.post.PostDTO
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.week_5_assignment_cagrikirt.databinding.FragmentFavoritesBinding
import com.example.week_5_assignment_cagrikirt.ui.favorite.adapter.FavoritesAdapter
import com.example.week_5_assignment_cagrikirt.ui.favorite.adapter.OnFavoriteClickListener
import com.example.week_5_assignment_cagrikirt.ui.favorite.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), OnFavoriteClickListener {
    private val viewModel by viewModels<FavoritesViewModel>()
    private lateinit var binding: FragmentFavoritesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.postLiveData.observe(viewLifecycleOwner) {

            binding.favoriteRecycling.adapter = FavoritesAdapter(this).apply {
                submitList(it)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPosts()
    }

    override fun onFavoriteClick(post: PostDTO) {
        viewModel.onFavoritePost(post)
    }


}
