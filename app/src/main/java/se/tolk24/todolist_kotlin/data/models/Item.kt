package se.tolk24.todolist_kotlin.data.models

import com.google.firebase.firestore.Exclude

class Item() {

    @Exclude
    var id: String = ""
    var name: String = ""

    constructor(name: String) : this() {
        this.name = name
    }

}