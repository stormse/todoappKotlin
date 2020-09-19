package se.tolk24.todolist_kotlin.ui.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import se.tolk24.todolist_kotlin.R
import se.tolk24.todolist_kotlin.data.Item
import se.tolk24.todolist_kotlin.data.List
import se.tolk24.todolist_kotlin.ui.create_list.CreateListFragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TodoListFragment : Fragment() {

    private val data = ArrayList<List>()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        fillDummyData()
    }

    //    List0
//        Item0 List0
//        Item1 List0
//    List1
//        Item0 List1
//    List2
    private fun fillDummyData() {

        for (i in 0..4) {

            val items = ArrayList<Item>()
            for (j in 0..3) {
                items.add(Item("Item$i List$j"))
            }
            data.add(List("List$i", items))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_todo_list, container, false)

        initView(root)
        return root
    }

    private fun initView(root: View) {
        checkCreatingList()

        val listRecyclerView: RecyclerView = root.findViewById(R.id.recycler_view_lists)
        listRecyclerView.adapter = TodoListAdapter(data)

        root.findViewById<View>(R.id.btn_add).setOnClickListener {

            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun checkCreatingList() {
        arguments?.apply {
            val list: List = getSerializable(CreateListFragment.LIST_OBJ_KEY) as List
            data.add(list)
        }
    }

}