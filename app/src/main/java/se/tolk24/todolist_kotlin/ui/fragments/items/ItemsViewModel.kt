package se.tolk24.todolist_kotlin.ui.fragments.items

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import se.tolk24.todolist_kotlin.data.models.Item
import se.tolk24.todolist_kotlin.data.repositories.ItemsRepository

class ItemsViewModel(application: Application) : AndroidViewModel(application) {


    private val itemsRepositoryy = ItemsRepository()
    val items = itemsRepositoryy.items
    val error = itemsRepositoryy.error
    val isItemCreated = itemsRepositoryy.isItemCreated
    val loading = itemsRepositoryy.loading
    fun createList(listId: String, item: Item) {
        itemsRepositoryy.createItem(listId, item)
    }

    fun loadData(listId: String) {
        if (items.value.isNullOrEmpty()) {
            itemsRepositoryy.fetchItems(listId)
        }
    }

    fun resetMessages() {
        itemsRepositoryy.resetMessages()
    }
}