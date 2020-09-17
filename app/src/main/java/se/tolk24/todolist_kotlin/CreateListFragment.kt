package se.tolk24.todolist_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import se.tolk24.todolist_kotlin.data.List

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateListFragment : Fragment() {

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

            onBack()
        }

        view.findViewById<View>(R.id.btn_create).setOnClickListener {

            val list = List(mNameEditText.text.toString(), ArrayList())
            onBack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//        onBack()
//        }
    }

    private fun onBack() {
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }
}