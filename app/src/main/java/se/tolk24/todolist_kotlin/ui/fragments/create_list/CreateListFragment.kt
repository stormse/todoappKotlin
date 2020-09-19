package se.tolk24.todolist_kotlin.ui.fragments.create_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.TextInputEditText
import se.tolk24.todolist_kotlin.R
import se.tolk24.todolist_kotlin.data.models.List
import se.tolk24.todolist_kotlin.ui.fragments.list.TodoListViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateListFragment : Fragment() {

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

            val name = mNameEditText.text.toString()
            if (name.isNullOrEmpty()) {

                Toast.makeText(
                    requireActivity(),
                    getString(R.string.blank_list_name_error),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val list = List(mNameEditText.text.toString())
                todoListViewModel.createList(list)
                requireActivity().onBackPressed()
            }
        }
    }
}