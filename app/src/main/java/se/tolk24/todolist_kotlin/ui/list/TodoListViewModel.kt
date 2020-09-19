package se.tolk24.todolist_kotlin.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import se.tolk24.todolist_kotlin.data.models.List
import se.tolk24.todolist_kotlin.data.repositories.ListsRepository

class TodoListViewModel(application: Application) : AndroidViewModel(application) {

    private val listsRepository = ListsRepository()
    val lists = listsRepository.lists
    val error = listsRepository.error
    val isCreateList = listsRepository.isCreateList

    init {
        listsRepository.fetchLists()
    }

    fun createList(list: List) {
        listsRepository.createList(list)
    }
}