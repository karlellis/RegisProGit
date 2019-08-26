package tv.soylent.regispro

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_schools.*
import kotlinx.android.synthetic.main.activity_schools.toolbar
import kotlinx.android.synthetic.main.activity_subjects.*
import tv.soylent.regispro.MainActivity.Companion.dbHandler


class SchoolsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schools)
        setSupportActionBar(toolbar)

//        var schoolList = ArrayList<SchoolsData>()
//        schoolList.add(SchoolsData(1, "Scuola1", R.drawable.ic_delete))
//        schoolList.add(SchoolsData(2, "Scuola2", R.drawable.ic_delete))

        schools_recycler.layoutManager = LinearLayoutManager(this)
        schools_recycler.hasFixedSize()
        //schools_recycler.adapter = SchoolAdapter(schoolList)
        schools_recycler.adapter = SchoolDBAdapter(this, dbHandler.getSchools(this))

        schools_add_btn.setOnClickListener {

            val i = Intent(this, AddSchoolActivity::class.java)
            startActivity(i)

//                view ->
//            Snackbar.make(view, "Add schools", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
        }

    }

    override fun onResume() {
        schools_recycler.layoutManager = LinearLayoutManager(this)
        schools_recycler.hasFixedSize()
        //schools_recycler.adapter = SchoolAdapter(schoolList)
        schools_recycler.adapter = SchoolDBAdapter(this, dbHandler.getSchools(this))
        super.onResume()
    }
}

