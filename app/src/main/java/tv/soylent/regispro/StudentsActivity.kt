package tv.soylent.regispro

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import kotlinx.android.synthetic.main.activity_students.toolbar
import kotlinx.android.synthetic.main.activity_students.*
import kotlinx.android.synthetic.main.classes_item.*


class StudentsActivity : AppCompatActivity() {
    //var cName = className
    //className : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)
        setSupportActionBar(toolbar)


        var studentList = ArrayList<StudentsData>()
        studentList.add(StudentsData(1,1,1,"Cognome Nome 1", R.drawable.ic_delete))
        studentList.add(StudentsData(2,1,1,"Cognome Nome 2", R.drawable.ic_delete))
        studentList.add(StudentsData(3,1,1,"Cognome Nome 3", R.drawable.ic_delete))
        studentList.add(StudentsData(4,1,1,"Cognome Nome 4", R.drawable.ic_delete))
        studentList.add(StudentsData(5,1,1,"Cognome Nome 5", R.drawable.ic_delete))
        studentList.add(StudentsData(6,1,1,"Cognome Nome 6", R.drawable.ic_delete))

        students_recycler.layoutManager = LinearLayoutManager(this)
        students_recycler.hasFixedSize()
        students_recycler.adapter = StudentAdapter(studentList)
//        students_class_title.text = getResources().getString(R.string.class_label) + " " + intent.getStringExtra("Classname")
        students_school_title.text = "Istituto " + intent.getStringExtra("Schoolname") + " " + intent.getStringExtra("Classname")
        student_add_btn.setOnClickListener {

                view ->
            Snackbar.make(view, "Add student", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

    }
}

