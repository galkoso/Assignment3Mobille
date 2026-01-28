package com.example.students_app.model

object Model {
    val data = mutableListOf<Student>()

    init {
        for (i in 0..20) {
            data.add(Student("Student $i", "$i", "050123456$i", "Address $i", false))
        }
    }
}
