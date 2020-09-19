package se.tolk24.todolist_kotlin.ui.fragments.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import se.tolk24.todolist_kotlin.R
import se.tolk24.todolist_kotlin.data.models.Item

class ItemsAdapter() :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {
    private val data = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val cellView =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_todo_list, parent, false)

        return ItemViewHolder(cellView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.nameTextView.text = data[position].name
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ItemViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nameTextView: TextView

        init {
            nameTextView = itemView.findViewById(R.id.txt_name)
        }
    }

    fun setData(data: ArrayList<Item>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

}