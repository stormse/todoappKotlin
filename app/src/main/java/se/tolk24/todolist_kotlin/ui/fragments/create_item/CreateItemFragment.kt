package se.tolk24.todolist_kotlin.ui.fragments.create_item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.TextInputEditText
import se.tolk24.todolist_kotlin.R
import se.tolk24.todolist_kotlin.data.models.Item
import se.tolk24.todolist_kotlin.data.models.List
import se.tolk24.todolist_kotlin.ui.fragments.items.ItemsFragment
import se.tolk24.todolist_kotlin.ui.fragments.items.ItemsViewModel
import se.tolk24.todolist_kotlin.ui.fragments.list.TodoListViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateItemFragment : Fragment() {

    private val itemsViewModel: ItemsViewModel by activityViewModels()
    private lateinit var mNameEditText: TextInputEditText
    private lateinit var list: List

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = arguments?.get(ItemsFragment.LIST_KEY) as List
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_item, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {

        mNameEditText = view.findViewById(R.id.edit_name)
        view.findViewById<Button>(R.id.btn_cancel).setOnClickListener {

            requireActivity().onBackPressed()
        }

        view.findViewById<View>(R.id.btn_create).setOnClickListener {

            val item = Item(mNameEditText.text.toString())
            itemsViewModel.createList(list.id, item)
            requireActivity().onBackPressed()
        }
    }
}