package se.tolk24.todolist_kotlin.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import se.tolk24.todolist_kotlin.R
import se.tolk24.todolist_kotlin.data.models.List
import se.tolk24.todolist_kotlin.ui.create_list.CreateListFragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TodoListFragment : Fragment() {

    private val todoListViewModel: TodoListViewModel by viewModels()
    private lateinit var todoListAdapter: TodoListAdapter

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

        todoListAdapter = TodoListAdapter()
        listRecyclerView.adapter = todoListAdapter

        root.findViewById<View>(R.id.btn_add).setOnClickListener {

            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        todoListViewModel.lists.observe(viewLifecycleOwner, {
            todoListAdapter.setData(it)
        })
        todoListViewModel.error.observe(viewLifecycleOwner, {
            it?.let { Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show() }
        })
    }
}