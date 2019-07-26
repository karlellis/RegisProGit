package tv.soylent.regispro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_class.*
import tv.soylent.regispro.MainActivity.Companion.dbHandler

class AddClassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_class)

        btnClassSave.setOnClickListener {
            if (editClassName.text.isEmpty()) {
                Toast.makeText(this, "Inserire nome classe", Toast.LENGTH_SHORT).show()
                editClassName.requestFocus()
            } else {
                val klass = Klass()
                klass.className = editClassName.text.toString()

                klass.schoolID = intent.getIntExtra("SchoolID",0)

                dbHandler.addClass(this, klass)
                ClearEdits()
                editClassName.requestFocus()
            }
        }

        btnClassCancel.setOnClickListener {
            ClearEdits()
            finish()
        }
    }

    private fun ClearEdits() {
        editClassName.text.clear()
    }
}

