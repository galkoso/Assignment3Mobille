package com.example.students_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.students_app.model.Student

class StudentsAdapter(
    private val students: List<Student>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View, onItemClick: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val nameTv: TextView = itemView.findViewById(R.id.student_row_name)
        val idTv: TextView = itemView.findViewById(R.id.student_row_id)
        val checkBox: CheckBox = itemView.findViewById(R.id.student_row_check)
        val image: ImageView = itemView.findViewById(R.id.student_row_image)

        init {
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_list_row, parent, false)
        return StudentViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.nameTv.text = student.name
        holder.idTv.text = student.id
        holder.checkBox.isChecked = student.isChecked
        
        holder.checkBox.setOnClickListener {
            student.isChecked = holder.checkBox.isChecked
        }
    }

    override fun getItemCount(): Int = students.size
}
