package se.tolk24.todolist_kotlin.ui.fragments.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import se.tolk24.todolist_kotlin.R
import se.tolk24.todolist_kotlin.data.models.Item

class ItemsAdapter(private val onItemDeletedListener: ItemsFragment.OnItemDeletedListener) :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>(), View.OnClickListener {
    private val data = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val cellView =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_item, parent, false)

        return ItemViewHolder(cellView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.nameTextView.text = data[position].name

        holder.deleteButton.tag = data[position]
        holder.deleteButton.setOnClickListener(this)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameTextView: TextView = itemView.findViewById(R.id.txt_name)
        val deleteButton: ImageButton = itemView.findViewById(R.id.btn_delete)

    }

    fun setData(data: ArrayList<Item>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onClick(view: View) {

        if (view.id == R.id.btn_delete) {
            onItemDeletedListener.onItemDeleted(view.tag as Item)
        }
    }
}