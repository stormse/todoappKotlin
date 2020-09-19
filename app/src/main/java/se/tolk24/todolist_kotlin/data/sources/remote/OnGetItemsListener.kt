package se.tolk24.todolist_kotlin.data.sources.remote

import se.tolk24.todolist_kotlin.data.models.Item

interface OnGetItemsListener {

    fun onSuccess(itemsData: ArrayList<Item>)
    fun onError(message: String?)
}