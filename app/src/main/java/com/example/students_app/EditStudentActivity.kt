package com.example.students_app

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.students_app.model.Model

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val studentPos = intent.getIntExtra("student_pos", -1)
        if (studentPos == -1) {
            finish()
            return
        }

        val student = Model.data[studentPos]

        val nameEt: EditText = findViewById(R.id.edit_student_name)
        val idEt: EditText = findViewById(R.id.edit_student_id)
        val phoneEt: EditText = findViewById(R.id.edit_student_phone)
        val addressEt: EditText = findViewById(R.id.edit_student_address)
        val checkBox: CheckBox = findViewById(R.id.edit_student_check)
        val saveBtn: Button = findViewById(R.id.edit_student_save_btn)
        val cancelBtn: Button = findViewById(R.id.edit_student_cancel_btn)
        val deleteBtn: Button = findViewById(R.id.edit_student_delete_btn)

        nameEt.setText(student.name)
        idEt.setText(student.id)
        phoneEt.setText(student.phone)
        addressEt.setText(student.address)
        checkBox.isChecked = student.isChecked

        saveBtn.setOnClickListener {
            student.name = nameEt.text.toString()
            student.id = idEt.text.toString()
            student.phone = phoneEt.text.toString()
            student.address = addressEt.text.toString()
            student.isChecked = checkBox.isChecked
            finish()
        }

        deleteBtn.setOnClickListener {
            Model.data.removeAt(studentPos)
            finish()
        }

        cancelBtn.setOnClickListener {
            finish()
        }
    }
}
