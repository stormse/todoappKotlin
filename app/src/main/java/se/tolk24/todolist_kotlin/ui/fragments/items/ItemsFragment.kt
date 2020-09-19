package se.tolk24.todolist_kotlin.ui.fragments.items

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import se.tolk24.todolist_kotlin.R
import se.tolk24.todolist_kotlin.data.models.Item
import se.tolk24.todolist_kotlin.data.models.List

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ItemsFragment : Fragment() {

    companion object {
        const val LIST_KEY = "LIST_KEY"

        fun getListArguments(list: List): Bundle {
            val args = Bundle()
            args.putSerializable(LIST_KEY, list)
            return args
        }
    }

    private val itemsViewModel: ItemsViewModel by activityViewModels()
    private lateinit var itemsAdapter: ItemsAdapter
    private lateinit var mLoadingProgressBar: ProgressBar
    private lateinit var list: List

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = arguments?.get(LIST_KEY) as List
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_items, container, false)

        initView(root)
        return root
    }

    private fun initView(root: View) {

        mLoadingProgressBar = root.findViewById(R.id.progressBar_loading)
        val listRecyclerView: RecyclerView = root.findViewById(R.id.recycler_view_items)

        itemsAdapter = ItemsAdapter(object : OnItemDeletedListener {
            override fun onItemDeleted(item: Item) {
                itemsViewModel.deleteItem(list.id, item)
            }
        })
        listRecyclerView.adapter = itemsAdapter

        root.findViewById<TextView>(R.id.txt_list_name).text = list.name

        root.findViewById<View>(R.id.btn_add).setOnClickListener {

            findNavController().navigate(
                R.id.action_itemsFragment_to_createItemFragment,
                getListArguments(list)
            )
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsViewModel.items.observe(viewLifecycleOwner, {
            itemsAdapter.setData(it)
        })
        itemsViewModel.isItemCreated.observe(viewLifecycleOwner, {
            if (it) {
                showMessage(getString(R.string.item_created_successfully))
            }
        })
        itemsViewModel.error.observe(viewLifecycleOwner, {
            itemsViewModel.error.observe(viewLifecycleOwner, {

                it?.let { showMessage(it) }
            })
        })
        itemsViewModel.loading.observe(viewLifecycleOwner, { loading ->
            if (loading)
                mLoadingProgressBar.visibility = View.VISIBLE
            else
                mLoadingProgressBar.visibility = View.GONE
        })

        itemsViewModel.loadData(list.id)
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        itemsViewModel.resetMessages()
    }

    override fun onDestroy() {
        super.onDestroy()
        itemsViewModel.onDestroy()
    }

    interface OnItemDeletedListener {
        fun onItemDeleted(item: Item)
    }
}