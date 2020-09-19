package se.tolk24.todolist_kotlin.data.models

import com.google.firebase.firestore.Exclude
import java.io.Serializable

class List() : Serializable {
    var id: String = ""
        @Exclude
        get
        @Exclude
        set
    var name: String = ""

    constructor(name: String) : this() {
        this.name = name
    }
}