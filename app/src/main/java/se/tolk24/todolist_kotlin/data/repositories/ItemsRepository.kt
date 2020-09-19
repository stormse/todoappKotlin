package se.tolk24.todolist_kotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import se.tolk24.todolist_kotlin.data.models.Item
import se.tolk24.todolist_kotlin.data.sources.remote.ItemsFirebaseManager
import se.tolk24.todolist_kotlin.data.sources.remote.OnFirebaseCreateListener
import se.tolk24.todolist_kotlin.data.sources.remote.OnGetItemsListener

class ItemsRepository {

    private val itemsFirebaseManager = ItemsFirebaseManager()
    private val _items = MutableLiveData<ArrayList<Item>>()
    val items: LiveData<ArrayList<Item>> = _items
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    private val _isItemCreated = MutableLiveData<Boolean>()
    val isItemCreated = _isItemCreated
    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading

    fun fetchLists(listId: String) {
        loading.postValue(true)
        itemsFirebaseManager.getItems(listId, object : OnGetItemsListener {
            override fun onSuccess(itemsData: ArrayList<Item>) {
                _items.postValue(itemsData)
                loading.postValue(false)
            }

            override fun onError(message: String?) {
                message?.let { _error.postValue(it) }
                loading.postValue(false)
            }
        })
    }

    fun createItem(listId: String, item: Item) {
        addItemToData(item)

        itemsFirebaseManager.addItem(listId, item, object : OnFirebaseCreateListener {
            override fun onSuccess() {
                _isItemCreated.postValue(true)
            }

            override fun onError(message: String?) {
                message?.let { _error.postValue(it) }
            }

        })
    }

    private fun addItemToData(item: Item) {
        val data = ArrayList<Item>()
        _items.value?.let {
            data.addAll(it)
        }
        data.add(item)
        _items.postValue(data)
    }

    fun resetMessages() {
        _error.value = null
        _isItemCreated.value = false
        _loading.value = false
    }
}