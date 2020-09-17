package se.tolk24.todolist_kotlin.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_todo_list.view.*
import se.tolk24.todolist_kotlin.R
import se.tolk24.todolist_kotlin.data.List

class TodoListAdapter(val data: ArrayList<List>) :
    RecyclerView.Adapter<TodoListAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val cellView =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_todo_list, parent, false)

        return ListViewHolder(cellView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.nameTextView.text = data[position].name
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ListViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nameTextView: TextView

        init {
            nameTextView = itemView.findViewById(R.id.txt_name)
        }
    }

}