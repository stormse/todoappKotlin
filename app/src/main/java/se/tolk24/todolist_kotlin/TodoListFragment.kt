package se.tolk24.todolist_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import se.tolk24.todolist_kotlin.data.Item
import se.tolk24.todolist_kotlin.data.List

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TodoListFragment : Fragment() {

    private val data = ArrayList<List>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        val listRecyclerView: RecyclerView = root.findViewById(R.id.recycler_view_lists)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }


}