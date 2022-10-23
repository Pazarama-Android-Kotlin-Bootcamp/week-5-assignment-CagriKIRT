package com.example.week_5_assignment_cagrikirt.ui.favorite.viewmodel

import com.example.week_5_assignment_cagrikirt.ui.model.post.PostDTO
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.week_5_assignment_cagrikirt.data.repository.IPostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val IPostRepository: IPostRepository) :
    ViewModel() {
    private var _postLiveData = MutableLiveData<List<PostDTO>?>()
    val postLiveData: LiveData<List<PostDTO>?>
        get() = _postLiveData


    fun getPosts() {
        IPostRepository.getFavorites()
        _postLiveData.postValue(IPostRepository.getFavorites().map {
            PostDTO(it.postBody, it.id?.toInt(), it.postId?.toInt(), it.postTitle, true)
        }
        )


    }

    fun onFavoritePost(post: PostDTO) {
        IPostRepository.deleteFavoritePost(post.id.toString())
    }
}


