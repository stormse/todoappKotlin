package se.tolk24.todolist_kotlin.ui.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import se.tolk24.todolist_kotlin.R
import se.tolk24.todolist_kotlin.data.models.List
import se.tolk24.todolist_kotlin.ui.fragments.items.ItemsFragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TodoListFragment : Fragment() {

    private val todoListViewModel: TodoListViewModel by activityViewModels()
    private lateinit var todoListAdapter: TodoListAdapter
    private lateinit var mLoadingProgressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_todo_list, container, false)

        initView(root)
        return root
    }

    private fun initView(root: View) {

        mLoadingProgressBar = root.findViewById(R.id.progressBar_loading)
        val listRecyclerView: RecyclerView = root.findViewById(R.id.recycler_view_lists)

        todoListAdapter = TodoListAdapter(object : OnListClickListener {
            override fun onClick(list: List) {
                findNavController().navigate(
                    R.id.action_listFragment_to_itemsFragment,
                    ItemsFragment.getListArguments(list)
                )
            }

            override fun onDelete(list: List) {
                todoListViewModel.deleteList(list)
            }

        })
        listRecyclerView.adapter = todoListAdapter

        root.findViewById<View>(R.id.btn_add).setOnClickListener {

            findNavController().navigate(R.id.action_listFragment_to_createListFragment)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todoListViewModel.lists.observe(viewLifecycleOwner, {
            todoListAdapter.setData(it)
        })
        todoListViewModel.isCreateList.observe(viewLifecycleOwner, {
            if (it) {
                showMessage(getString(R.string.list_created_successfully))
            }
        })
        todoListViewModel.error.observe(viewLifecycleOwner, {
            todoListViewModel.error.observe(viewLifecycleOwner, {

                it?.let { showMessage(it) }
            })
        })
        todoListViewModel.loading.observe(viewLifecycleOwner, { loading ->
            if (loading)
                mLoadingProgressBar.visibility = View.VISIBLE
            else
                mLoadingProgressBar.visibility = View.GONE
        })

        todoListViewModel.loadData()
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        todoListViewModel.resetMessages()
    }

    interface OnListClickListener {
        fun onClick(list: List)
        fun onDelete(list: List)
    }
}