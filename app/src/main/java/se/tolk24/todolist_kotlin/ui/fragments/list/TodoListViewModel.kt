package se.tolk24.todolist_kotlin.ui.fragments.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import se.tolk24.todolist_kotlin.data.models.List
import se.tolk24.todolist_kotlin.data.repositories.ListsRepository

class TodoListViewModel(application: Application) : AndroidViewModel(application) {


    private val listsRepository = ListsRepository()
    val lists = listsRepository.lists
    val error = listsRepository.error
    val isCreateList = listsRepository.isCreateList
    val loading = listsRepository.loading
    fun createList(list: List) {
        listsRepository.createList(list)
    }

    fun loadData() {
        if (lists.value.isNullOrEmpty()) {
            listsRepository.fetchLists()
        }
    }

    fun resetMessages() {
        listsRepository.resetMessages()
    }
}