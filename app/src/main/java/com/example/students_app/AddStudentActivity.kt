package com.example.students_app

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.students_app.model.Model
import com.example.students_app.model.Student

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val nameEt: EditText = findViewById(R.id.add_student_name)
        val idEt: EditText = findViewById(R.id.add_student_id)
        val phoneEt: EditText = findViewById(R.id.add_student_phone)
        val addressEt: EditText = findViewById(R.id.add_student_address)
        val checkBox: CheckBox = findViewById(R.id.add_student_check)
        val saveBtn: Button = findViewById(R.id.add_student_save_btn)
        val cancelBtn: Button = findViewById(R.id.add_student_cancel_btn)

        saveBtn.setOnClickListener {
            val name = nameEt.text.toString()
            val id = idEt.text.toString()
            val phone = phoneEt.text.toString()
            val address = addressEt.text.toString()
            val isChecked = checkBox.isChecked

            val student = Student(name, id, phone, address, isChecked)
            Model.data.add(student)
            finish()
        }

        cancelBtn.setOnClickListener {
            finish()
        }
    }
}
