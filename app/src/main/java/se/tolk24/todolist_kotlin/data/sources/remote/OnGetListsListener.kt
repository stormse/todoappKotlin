package se.tolk24.todolist_kotlin.data.sources.remote

import se.tolk24.todolist_kotlin.data.models.List

interface OnGetListsListener {

    fun onSuccess(listData: ArrayList<List>)
    fun onError(message: String?)
}