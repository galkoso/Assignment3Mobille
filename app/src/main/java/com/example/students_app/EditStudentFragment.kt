package com.example.students_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.students_app.model.Model
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

class EditStudentFragment : Fragment() {
    private val args: EditStudentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_student, container, false)

        val studentPos = args.studentPos
        if (studentPos == -1) {
            findNavController().navigateUp()
            return null
        }

        val student = Model.data[studentPos]

        val nameEt: TextInputEditText = view.findViewById(R.id.edit_student_name)
        val idEt: TextInputEditText = view.findViewById(R.id.edit_student_id)
        val phoneEt: TextInputEditText = view.findViewById(R.id.edit_student_phone)
        val addressEt: TextInputEditText = view.findViewById(R.id.edit_student_address)
        val birthDateEt: TextInputEditText = view.findViewById(R.id.edit_student_birth_date)
        val birthTimeEt: TextInputEditText = view.findViewById(R.id.edit_student_birth_time)
        val checkBox: CheckBox = view.findViewById(R.id.edit_student_check)
        val saveBtn: Button = view.findViewById(R.id.edit_student_save_btn)
        val cancelBtn: Button = view.findViewById(R.id.edit_student_cancel_btn)
        val deleteBtn: Button = view.findViewById(R.id.edit_student_delete_btn)

        nameEt.setText(student.name)
        idEt.setText(student.id)
        phoneEt.setText(student.phone)
        addressEt.setText(student.address)
        birthDateEt.setText(student.birthDate)
        birthTimeEt.setText(student.birthTime)
        checkBox.isChecked = student.isChecked

        birthDateEt.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Birth Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            datePicker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                calendar.timeInMillis = it
                val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                birthDateEt.setText(format.format(calendar.time))
            }
            datePicker.show(parentFragmentManager, "DATE_PICKER")
        }

        birthTimeEt.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Birth Time")
                .build()
            timePicker.addOnPositiveButtonClickListener {
                val hour = timePicker.hour
                val minute = timePicker.minute
                val amPm = if (hour < 12) "AM" else "PM"
                val displayHour = if (hour == 0 || hour == 12) 12 else hour % 12
                birthTimeEt.setText(String.format("%02d:%02d %s", displayHour, minute, amPm))
            }
            timePicker.show(parentFragmentManager, "TIME_PICKER")
        }

        saveBtn.setOnClickListener {
            student.name = nameEt.text.toString()
            student.id = idEt.text.toString()
            student.phone = phoneEt.text.toString()
            student.address = addressEt.text.toString()
            student.birthDate = birthDateEt.text.toString()
            student.birthTime = birthTimeEt.text.toString()
            student.isChecked = checkBox.isChecked
            
            showSuccessDialog()
        }

        deleteBtn.setOnClickListener {
            Model.data.removeAt(studentPos)
            findNavController().popBackStack(R.id.studentsListFragment, false)
        }

        cancelBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }

    private fun showSuccessDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.title_success)
            .setMessage(R.string.msg_save_success)
            .setPositiveButton(R.string.ok) { _, _ ->
                findNavController().navigateUp()
            }
            .setCancelable(false)
            .show()
    }
}
