package se.tolk24.todolist_kotlin.data.sources.remote

import com.google.firebase.firestore.FirebaseFirestore
import se.tolk24.todolist_kotlin.data.models.List


class ListsFirebaseManager() {
    companion object {

        private const val LISTS_COLLECTION = "lists"
    }

    private var db = FirebaseFirestore.getInstance()

    fun addList(list: List, onFirebaseCreateListener: OnFirebaseCreateListener) {

        db.collection(LISTS_COLLECTION)
            .add(list)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onFirebaseCreateListener.onSuccess()
                } else {
                    onFirebaseCreateListener.onError(it.exception?.message)
                }
            }
    }


    fun getListData(onGetListsListener: OnGetListsListener) {
        db.collection(LISTS_COLLECTION)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val listData = ArrayList<List>()
                    task.result?.let {
                        for (document in it) {
                            val list = document.toObject(List::class.java)
                            list.id = document.id
                            listData.add(list)
                        }
                    }
                    onGetListsListener.onSuccess(listData)
                } else {
                    onGetListsListener.onError(task.exception?.message)
                }
            }
    }

    fun deleteList(list: List, onFirebaseCreateListener: OnFirebaseCreateListener) {
        db.collection(LISTS_COLLECTION)
            .document(list.id)
            .delete()
        onFirebaseCreateListener.onSuccess()
    }
}