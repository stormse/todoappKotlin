package se.tolk24.todolist_kotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import se.tolk24.todolist_kotlin.data.models.List
import se.tolk24.todolist_kotlin.data.sources.remote.ListsFirebaseManager
import se.tolk24.todolist_kotlin.data.sources.remote.OnFirebaseCreateListener
import se.tolk24.todolist_kotlin.data.sources.remote.OnGetListsListener

class ListsRepositories {

    private val listsFirebaseManager = ListsFirebaseManager()
    private val _lists = MutableLiveData<ArrayList<List>>()
    val lists: LiveData<ArrayList<List>> = _lists
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchLists() {
        listsFirebaseManager.getListData(object : OnGetListsListener {
            override fun onSuccess(listData: ArrayList<List>) {
                _lists.postValue(listData)
            }

            override fun onError(message: String?) {
                message?.let { _error.postValue(it) }
            }

        })
    }

    fun createList(list: List) {
        listsFirebaseManager.addList(list, object : OnFirebaseCreateListener {
            override fun onSuccess() {
                _error.postValue(null)
            }

            override fun onError(message: String?) {
                message?.let { _error.postValue(it) }
            }

        })
    }
}