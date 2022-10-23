package com.example.week_5_assignment_cagrikirt.ui.posts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.week_5_assignment_cagrikirt.data.local.database.entity.PostEntity
import com.example.week_5_assignment_cagrikirt.data.repository.IPostRepository
import com.example.week_5_assignment_cagrikirt.ui.model.DataState
import com.example.week_5_assignment_cagrikirt.ui.model.post.Post
import com.example.week_5_assignment_cagrikirt.ui.model.post.PostDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val IPostRepository: IPostRepository) : ViewModel() {
    private var _postLiveData = MutableLiveData<DataState<List<PostDTO>?>>()
    val postLiveData: LiveData<DataState<List<PostDTO>?>>
        get() = _postLiveData

    private val _eventStateLiveData = MutableLiveData<PostViewEvent>()
    val eventStateLiveData: LiveData<PostViewEvent>
        get() = _eventStateLiveData

    init {
        getPosts()
    }

    private fun getPosts() {
        _postLiveData.postValue(DataState.Loading())
        IPostRepository.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let {

                        _postLiveData.postValue(DataState.Success(it.map { safePost ->
                            PostDTO(
                                id = safePost.id,
                                title = safePost.title,
                                body = safePost.body,
                                userId = safePost.userId,
                                isFavorite = isExists(safePost.id)
                            )
                        }))

                    } ?: kotlin.run {
                        _postLiveData.postValue(DataState.Error("Data Empty"))
                    }
                } else {
                    _postLiveData.postValue(DataState.Error(response.message()))
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                _postLiveData.postValue(DataState.Error(t.message.toString()))
                _eventStateLiveData.postValue(PostViewEvent.ShowMessage(t.message.toString()))
            }
        })
    }

    fun onFavoritePost(post: PostDTO) {
        IPostRepository.getPostById(post.id ?: 0)?.let {
            IPostRepository.deleteFavoritePost(
                post.id.toString()
            )
            //favoriden çıkarma
            updateFavoriteState( post.id,false)
        } ?: kotlin.run {
            IPostRepository.insertFavoritePost(
                PostEntity(
                    postId = post.id.toString(),
                    postTitle = post.title,
                    postBody = post.body
                )
            )
            //favorileme
            updateFavoriteState( post.id,true)
        }
    }
    //favorileme
    private fun updateFavoriteState(id:Int?, isFavorite:Boolean){
        when(val current= _postLiveData.value){
            is DataState.Success -> {
                val currentList= current.data?.map {
                    if (it.id==id){
                        it.copy(isFavorite = isFavorite)

                    }
                    else it
                }
                _postLiveData.value= DataState.Success(currentList)
            }
            is DataState.Error -> {}
            is DataState.Loading -> {}

            null -> {}
        }
    }
    private fun isExists(postId: Int?): Boolean {
        postId?.let {
            IPostRepository.getPostById(it)?.let {
                return true
            }
        }
        return false
    }
}

sealed class PostViewEvent {
    object NavigateToDetail : PostViewEvent()
    class ShowMessage(val message: String?) : PostViewEvent()
}