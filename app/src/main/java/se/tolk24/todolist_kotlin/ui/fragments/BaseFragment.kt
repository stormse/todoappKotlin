package se.tolk24.todolist_kotlin.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import se.tolk24.todolist_kotlin.ui.activities.MainActivity

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).setTitle(getTitle())
        (requireActivity() as MainActivity).setBackButton(canBack())
    }

    abstract fun getTitle(): String
    abstract fun canBack(): Boolean
}