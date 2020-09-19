package se.tolk24.todolist_kotlin.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import se.tolk24.todolist_kotlin.data.repositories.ListsRepository

class TodoListViewModel(application: Application) : AndroidViewModel(application) {

    private val listsRepository = ListsRepository()
    val lists = listsRepository.lists
    val error = listsRepository.error

    init {
        listsRepository.fetchLists()
    }
}