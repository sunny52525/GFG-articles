package com.gfg.gfgkotlinflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gfg.gfgkotlinflow.model.CommentModel
import com.gfg.gfgkotlinflow.network.CommentApiState
import com.gfg.gfgkotlinflow.network.Status
import com.gfg.gfgkotlinflow.repository.CommentsRepository
import com.gfg.gfgkotlinflow.utils.AppConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CommentsViewModel : ViewModel() {

    private val repository = CommentsRepository(
        AppConfig.ApiService()
    )
    val commentState = MutableStateFlow(
        CommentApiState(
            Status.LOADING,
            CommentModel(), ""
        )
    )

    init {
        getNewComment(1)
    }


    fun getNewComment(id: Int) {

        commentState.value = CommentApiState.loading(CommentModel())
        viewModelScope.launch {
            repository.getComment(id)

                .catch {
                    commentState.value =
                        CommentApiState.error(it.message.toString(), CommentModel())
                }
                .collect {
                    commentState.value = CommentApiState.success(it.data)
                }
        }
    }

}