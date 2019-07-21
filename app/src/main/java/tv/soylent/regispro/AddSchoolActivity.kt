package tv.soylent.regispro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_school.*
import kotlinx.android.synthetic.main.activity_classes.*
import tv.soylent.regispro.MainActivity.Companion.dbHandler

class AddSchoolActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_school)

        btnSchoolSave.setOnClickListener {
            if (editSchoolName.text.isEmpty()) {
                Toast.makeText(this, "Inserire nome istituto", Toast.LENGTH_SHORT).show()
                editSchoolName.requestFocus()
            } else {
                val school = School()
                school.schoolName = editSchoolName.text.toString()
                dbHandler.addSchool(this , school)
                ClearEdits()
                editSchoolName.requestFocus()
            }
        }

        btnSchoolCancel.setOnClickListener {
            ClearEdits()
            finish()
        }
    }

    private fun ClearEdits() {
        editSchoolName.text.clear()
    }
}

