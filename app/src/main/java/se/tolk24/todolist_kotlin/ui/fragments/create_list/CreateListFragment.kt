package se.tolk24.todolist_kotlin.ui.fragments.create_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.TextInputEditText
import se.tolk24.todolist_kotlin.R
import se.tolk24.todolist_kotlin.data.models.List
import se.tolk24.todolist_kotlin.ui.fragments.list.TodoListViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateListFragment : Fragment() {

    companion object {
        val LIST_OBJ_KEY = "LIST_OBJ_KEY"
    }

    private val todoListViewModel: TodoListViewModel by activityViewModels()
    private lateinit var mNameEditText: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_list, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {

        mNameEditText = view.findViewById(R.id.edit_name)
        view.findViewById<Button>(R.id.btn_cancel).setOnClickListener {

            requireActivity().onBackPressed()
        }

        view.findViewById<View>(R.id.btn_create).setOnClickListener {

            val list = List(mNameEditText.text.toString())
            todoListViewModel.createList(list)
            requireActivity().onBackPressed()
        }
    }
}