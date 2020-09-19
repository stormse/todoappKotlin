package se.tolk24.todolist_kotlin.data

import java.io.Serializable

class List(val name: String, val itemsList: ArrayList<Item>) : Serializable {
}