package se.tolk24.todolist_kotlin.ui.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_todo_list.view.*
import se.tolk24.todolist_kotlin.R
import se.tolk24.todolist_kotlin.data.models.List

class TodoListAdapter(private val onListClickListener: TodoListFragment.OnListClickListener) :
    RecyclerView.Adapter<TodoListAdapter.ListViewHolder>(), View.OnClickListener {
    private val data = ArrayList<List>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val cellView =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_todo_list, parent, false)

        return ListViewHolder(cellView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.nameTextView.text = data[position].name
        holder.parentLayoutView.tag = data[position]
        holder.parentLayoutView.setOnClickListener(this)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ListViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nameTextView: TextView = itemView.findViewById(R.id.txt_name)
        var parentLayoutView: View = itemView.findViewById(R.id.layout_parent)
    }

    fun setData(data: ArrayList<List>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onClick(view: View) {
        onListClickListener.onClick(view.tag as List)
    }

}