package com.example.students_app

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.students_app.model.Model

class StudentsListFragment : Fragment() {
    private lateinit var adapter: StudentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_students_list, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.students_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        
        adapter = StudentsAdapter(Model.data) { position ->
            val action = StudentsListFragmentDirections.actionStudentsListFragmentToStudentDetailsFragment(position)
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter

        setupMenu()

        return view
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.students_list_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.addStudentFragment) {
                    findNavController().navigate(R.id.action_studentsListFragment_to_addStudentFragment)
                    return true
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}
