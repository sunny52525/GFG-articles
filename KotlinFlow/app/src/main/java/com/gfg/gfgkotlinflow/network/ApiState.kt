package com.gfg.gfgkotlinflow.network


data class CommentApiState<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): CommentApiState<T> {
            return CommentApiState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): CommentApiState<T> {
            return CommentApiState(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): CommentApiState<T> {
            return CommentApiState(Status.LOADING, data, null)
        }

    }

}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
