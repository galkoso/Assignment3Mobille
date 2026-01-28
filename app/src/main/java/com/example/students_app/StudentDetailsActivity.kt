package com.example.students_app
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.students_app.model.Model

class StudentDetailsActivity : AppCompatActivity() {
    private var studentPos: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        studentPos = intent.getIntExtra("student_pos", -1)
        
        val nameTv: TextView = findViewById(R.id.details_student_name)
        val idTv: TextView = findViewById(R.id.details_student_id)
        val phoneTv: TextView = findViewById(R.id.details_student_phone)
        val addressTv: TextView = findViewById(R.id.details_student_address)
        val checkBox: CheckBox = findViewById(R.id.details_student_check)
        val editBtn: Button = findViewById(R.id.details_edit_btn)

        if (studentPos != -1) {
            val student = Model.data[studentPos]
            nameTv.text = "Name: ${student.name}"
            idTv.text = "ID: ${student.id}"
            phoneTv.text = "Phone: ${student.phone}"
            addressTv.text = "Address: ${student.address}"
            checkBox.isChecked = student.isChecked
        }

        editBtn.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student_pos", studentPos)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        if (studentPos != -1 && studentPos < Model.data.size) {
            val student = Model.data[studentPos]
            findViewById<TextView>(R.id.details_student_name).text = "Name: ${student.name}"
            findViewById<TextView>(R.id.details_student_id).text = "ID: ${student.id}"
            findViewById<TextView>(R.id.details_student_phone).text = "Phone: ${student.phone}"
            findViewById<TextView>(R.id.details_student_address).text = "Address: ${student.address}"
            findViewById<CheckBox>(R.id.details_student_check).isChecked = student.isChecked
        } else {
            finish() 
        }
    }
}
