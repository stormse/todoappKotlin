package se.tolk24.todolist_kotlin.data.sources.remote

interface OnFirebaseCreateListener {

    fun onSuccess()
    fun onError(message: String?)
}