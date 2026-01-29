package com.example.students_app.model

object Model {
    val data = mutableListOf<Student>()

    init {
        for (i in 0..20) {
            data.add(
                Student(
                    name = "Student $i",
                    id = "$i",
                    phone = "050123456$i",
                    address = "Address $i",
                    isChecked = false,
                    birthDate = "26/07/2004",
                    birthTime = "10:00"
                )
            )
        }
    }
}
