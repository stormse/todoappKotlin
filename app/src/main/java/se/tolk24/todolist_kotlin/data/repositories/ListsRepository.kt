package se.tolk24.todolist_kotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import se.tolk24.todolist_kotlin.data.models.List
import se.tolk24.todolist_kotlin.data.sources.remote.ListsFirebaseManager
import se.tolk24.todolist_kotlin.data.sources.remote.OnFirebaseCreateListener
import se.tolk24.todolist_kotlin.data.sources.remote.OnGetListsListener

class ListsRepository {

    private val listsFirebaseManager = ListsFirebaseManager()
    private val _lists = MutableLiveData<ArrayList<List>>()
    val lists: LiveData<ArrayList<List>> = _lists
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    private val _isCreateList = MutableLiveData<Boolean>()
    val isCreateList = _isCreateList

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
                _isCreateList.postValue(true)
                val lists = ArrayList<List>()
                _lists.value?.let {
                    lists.addAll(it)
                }
                lists.add(list)
                _lists.postValue(lists)
            }

            override fun onError(message: String?) {
                message?.let { _error.postValue(it) }
            }

        })
    }
}