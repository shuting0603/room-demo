package com.example.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.roomdemo.data.Student
import com.example.roomdemo.data.StudentDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd: Button = findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener(){
            val newRec: Student = Student(id = 0, name = "Jason", programme = "RIT")

            CoroutineScope(IO).launch {
                val studentDAO = StudentDB.getDatabase(application).studentDao()
                studentDAO.addStudent(newRec)
            }

        }

        val btnGet:Button  = findViewById(R.id.btnGetAll)
        btnGet.setOnClickListener() {

            CoroutineScope(Main).launch {
                var name: String = ""
                val studentDAO = StudentDB.getDatabase(application).studentDao()
                val studentList: Array<Student> = studentDAO.getAllStudent()

                if(studentList != null){
                    for(s:Student in studentList)
                        name += s.name +"\n"
                }

                val tvData: TextView = findViewById(R.id.tvData)
                tvData.setText(name)
            }
        }
    }
}