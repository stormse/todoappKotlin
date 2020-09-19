package se.tolk24.todolist_kotlin.data.sources.remote

import com.google.firebase.firestore.FirebaseFirestore
import se.tolk24.todolist_kotlin.data.models.Item


class ItemsFirebaseManager() {
    companion object {

        private const val ITEMS_COLLECTION = "lists/%s/items"
    }

    private var db = FirebaseFirestore.getInstance()

    fun addItem(listId: String, item: Item, onFirebaseCreateListener: OnFirebaseCreateListener) {

        db.collection(String.format(ITEMS_COLLECTION, listId))
            .add(item)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onFirebaseCreateListener.onSuccess()
                } else {
                    onFirebaseCreateListener.onError(it.exception?.message)
                }
            }
    }


    fun getItems(listId: String, onGetItemsListener: OnGetItemsListener) {
        db.collection(String.format(ITEMS_COLLECTION, listId))
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val itemsData = ArrayList<Item>()
                    task.result?.let {
                        for (document in it) {
                            val item = document.toObject(Item::class.java)
                            item.id = document.id
                            itemsData.add(item)
                        }
                    }
                    onGetItemsListener.onSuccess(itemsData)
                } else {
                    onGetItemsListener.onError(task.exception?.message)
                }
            }
    }

    fun deleteItem(
        listId: String,
        item: Item,
        onFirebaseCreateListener: OnFirebaseCreateListener
    ) {

        db.collection(String.format(ITEMS_COLLECTION, listId))
            .document(item.id)
            .delete()
        onFirebaseCreateListener.onSuccess()
    }
}