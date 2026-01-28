package com.example.students_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.students_app.model.Model
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentsListActivity : AppCompatActivity() {
    private lateinit var adapter: StudentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)

        val recyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        adapter = StudentsAdapter(Model.data) { position ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("student_pos", position)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.add_student_fab).setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}
