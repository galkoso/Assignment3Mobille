package com.example.students_app

import android.os.Bundle
import android.view.*
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.students_app.model.Model

class StudentDetailsFragment : Fragment() {
    private val args: StudentDetailsFragmentArgs by navArgs()
    private var studentPos: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_details, container, false)
        studentPos = args.studentPos
        
        setupUI(view)
        setupMenu()

        return view
    }

    private fun setupUI(view: View) {
        if (studentPos != -1 && studentPos < Model.data.size) {
            val student = Model.data[studentPos]
            view.findViewById<TextView>(R.id.details_student_name).text = "Name: ${student.name}"
            view.findViewById<TextView>(R.id.details_student_id).text = "ID: ${student.id}"
            view.findViewById<TextView>(R.id.details_student_phone).text = "Phone: ${student.phone}"
            view.findViewById<TextView>(R.id.details_student_address).text = "Address: ${student.address}"
            view.findViewById<CheckBox>(R.id.details_student_check).isChecked = student.isChecked
            view.findViewById<TextView>(R.id.details_student_birth_date).text = "Birth Date: ${student.birthDate}"
            view.findViewById<TextView>(R.id.details_student_birth_time).text = "Birth Time: ${student.birthTime}"
        }
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.student_details_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.editStudentFragment) {
                    val action = StudentDetailsFragmentDirections.actionStudentDetailsFragmentToEditStudentFragment(studentPos)
                    findNavController().navigate(action)
                    return true
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onResume() {
        super.onResume()
        view?.let { setupUI(it) }
        
        // If student was deleted in edit screen, we should pop back
        if (studentPos >= Model.data.size) {
            findNavController().navigateUp()
        }
    }
}
