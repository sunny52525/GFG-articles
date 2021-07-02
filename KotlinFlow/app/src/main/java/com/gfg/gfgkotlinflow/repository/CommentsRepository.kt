package com.gfg.gfgkotlinflow.repository

import android.util.Log
import com.gfg.gfgkotlinflow.model.CommentModel
import com.gfg.gfgkotlinflow.network.ApiService
import com.gfg.gfgkotlinflow.network.CommentApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CommentsRepository(private val apiService: ApiService) {


    suspend fun getComment(id: Int): Flow<CommentApiState<CommentModel>> {
        return flow {
            val comment=apiService.getComments(id)
            Log.d("TAG", "getComment: $comment")
            emit(CommentApiState.success(comment))
        }.flowOn(Dispatchers.IO)
    }
}