package tv.soylent.regispro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_subject.*
import tv.soylent.regispro.MainActivity.Companion.dbHandler

class AddSubjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_subject)

        btnSubjectSave.setOnClickListener {
            if (editSubjectName.text.isEmpty()) {
                Toast.makeText(this, "Inserire nome materia", Toast.LENGTH_SHORT).show()
                editSubjectName.requestFocus()
            } else {
                val subject = Subject()
                subject.subjectName = editSubjectName.text.toString()
                dbHandler.addSubject(this, subject)
                ClearEdits()
                editSubjectName.requestFocus()
            }
        }

        btnSubjectCancel.setOnClickListener {
            ClearEdits()
            finish()
        }
    }

    private fun ClearEdits() {
        editSubjectName.text.clear()
    }
}

