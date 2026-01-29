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
import com.example.students_app.model.Model
import com.example.students_app.model.Student
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

class AddStudentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

        val nameEt: TextInputEditText = view.findViewById(R.id.add_student_name)
        val idEt: TextInputEditText = view.findViewById(R.id.add_student_id)
        val phoneEt: TextInputEditText = view.findViewById(R.id.add_student_phone)
        val addressEt: TextInputEditText = view.findViewById(R.id.add_student_address)
        val birthDateEt: TextInputEditText = view.findViewById(R.id.add_student_birth_date)
        val birthTimeEt: TextInputEditText = view.findViewById(R.id.add_student_birth_time)
        val checkBox: CheckBox = view.findViewById(R.id.add_student_check)
        val saveBtn: Button = view.findViewById(R.id.add_student_save_btn)
        val cancelBtn: Button = view.findViewById(R.id.add_student_cancel_btn)

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
            val name = nameEt.text.toString()
            val id = idEt.text.toString()
            val phone = phoneEt.text.toString()
            val address = addressEt.text.toString()
            val birthDate = birthDateEt.text.toString()
            val birthTime = birthTimeEt.text.toString()
            val isChecked = checkBox.isChecked

            val student = Student(name, id, phone, address, isChecked, birthDate, birthTime)
            Model.data.add(student)
            
            showSuccessDialog()
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
