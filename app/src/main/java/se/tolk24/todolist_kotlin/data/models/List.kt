package se.tolk24.todolist_kotlin.data.models

import com.google.firebase.firestore.Exclude
import java.io.Serializable

class List(val name: String) : Serializable {
    @Exclude
    var id: String = ""
}