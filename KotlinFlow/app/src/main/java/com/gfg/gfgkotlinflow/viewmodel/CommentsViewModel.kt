package com.gfg.gfgkotlinflow.viewmodel


import com.gfg.gfgkotlinflow.model.CommentModel
import com.gfg.gfgkotlinflow.network.CommentApiState
import com.gfg.gfgkotlinflow.network.Status
import com.gfg.gfgkotlinflow.repository.CommentsRepository
import com.gfg.gfgkotlinflow.utils.AppConfig

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CommentsViewModel : ViewModel() {


    //Create a Repository and pass the api service we created in AppConfig file
    private val repository = CommentsRepository(
        AppConfig.ApiService()
    )

    /**
     * A commentData mutableStateflow which will store the Helper class CommentApiState
     * which may store the api data or the failure state.
     * Views can collect this data in background, as data is emitted whenever value changes, similar to livedata.
     * Set the initial Value to Loading.
     */
    val commentState = MutableStateFlow(
        CommentApiState(
            Status.LOADING,
            CommentModel(), ""
        )
    )

    init {
        //Initiate a starting search with comment Id 1
        getNewComment(1)
    }


    //Function to get new Comments
    fun getNewComment(id: Int) {

        //Since Network Calls takes time,Set the initial value to loading state
        commentState.value = CommentApiState.loading()

        //ApiCalls takes some time, So it has to be run and background thread. Using viewModelScope to call the api
        viewModelScope.launch {

            //Collecting the data emitted by the function in repository

            repository.getComment(id)
                    //If any errors occurs like 404 not found or invalid query, set the state to error State to show some info
                    //on screen
                .catch {
                    commentState.value =
                        CommentApiState.error(it.message.toString())
                }
                    //If Api call is succeeded, set the State to Success and set the response data to data received from api
                .collect {
                    commentState.value = CommentApiState.success(it.data)
                }
        }
    }

}