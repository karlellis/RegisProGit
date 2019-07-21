package tv.soylent.regispro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_subjects.toolbar
import kotlinx.android.synthetic.main.activity_subjects.*
import tv.soylent.regispro.MainActivity.Companion.dbHandler


class SubjectsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)
        setSupportActionBar(toolbar)

        subjects_recycler.layoutManager = LinearLayoutManager(this)
        subjects_recycler.hasFixedSize()
        subjects_recycler.adapter = SubjectDBAdapter(this, dbHandler.getSubjects(this))

        subjects_add_btn.setOnClickListener {
            val i = Intent(this, AddSubjectActivity::class.java)
            startActivity(i)
        }
    }

    override fun onResume() {
        subjects_recycler.layoutManager = LinearLayoutManager(this)
        subjects_recycler.hasFixedSize()
        //schools_recycler.adapter = SchoolAdapter(schoolList)
        subjects_recycler.adapter = SubjectDBAdapter(this, dbHandler.getSubjects(this))
        super.onResume()
    }
}

