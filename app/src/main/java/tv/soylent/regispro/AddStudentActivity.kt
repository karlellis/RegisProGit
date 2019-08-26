package tv.soylent.regispro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_class.*
import kotlinx.android.synthetic.main.activity_add_student.*
import tv.soylent.regispro.MainActivity.Companion.dbHandler

class AddStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        btnStudentSave.setOnClickListener {
            if (editStudentName.text.isEmpty()) {
                Toast.makeText(this, "Inserire nome studente", Toast.LENGTH_SHORT).show()
                editClassName.requestFocus()
            } else if (editStudentSurname.text.isEmpty()) {
                    Toast.makeText(this, "Inserire cognome studente", Toast.LENGTH_SHORT).show()
                    editClassName.requestFocus()
                } else {
                val student = Student()
                student.studentName = editStudentName.text.toString()
                student.studentSurname = editStudentSurname.text.toString()

                student.schoolID = intent.getIntExtra("SchoolID",0)
                student.classID = intent.getIntExtra("ClassID",0)

                dbHandler.addStudent(this, student)
                ClearEdits()
                editStudentName.requestFocus()
            }
        }

        btnStudentCancel.setOnClickListener {
            ClearEdits()
            finish()
        }
    }

    private fun ClearEdits() {
        editStudentName.text.clear()
        editStudentSurname.text.clear()
    }
}

