package tv.soylent.regispro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_classes.*
import kotlinx.android.synthetic.main.activity_classes.toolbar
import tv.soylent.regispro.MainActivity.Companion.dbHandler
import android.content.SharedPreferences
import android.preference.PreferenceManager


class ClassesActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classes)
        setSupportActionBar(toolbar)
        schools_spinner!!.setOnItemSelectedListener(this)

        val schoolList = dbHandler.getSchools(this)
        var schoolListName = ArrayList<String>()
        for (i in 0 until schoolList.size) {
            schoolListName.add(i, schoolList[i].schoolName.toString())

        }


        val spinner_schools_aa = ArrayAdapter(this, R.layout.classes_textview , schoolListName)
        // Set layout to use when the list of choices appear
        spinner_schools_aa.setDropDownViewResource(R.layout.classes_textview)
        // Set Adapter to Spinner
        schools_spinner!!.setAdapter(spinner_schools_aa)

//        var classList = ArrayList<ClassesData>()
//        classList.add(ClassesData(1, 1, "5eleG", R.drawable.ic_students, R.drawable.ic_delete))
//        classList.add(ClassesData(2, 1, "5eleH", R.drawable.ic_students, R.drawable.ic_delete))
//        classList.add(ClassesData(3, 1, "5eleL", R.drawable.ic_students, R.drawable.ic_delete))

        classes_recycler.layoutManager = LinearLayoutManager(this)
        classes_recycler.hasFixedSize()
//        classes_recycler.adapter = ClassAdapter(classList)
        classes_recycler.adapter = ClassDBAdapter(this, dbHandler.getClasses(this))

        classes_add_btn.setOnClickListener {

            val i = Intent(this, AddClassActivity::class.java)
            var schoolID = schoolList[schools_spinner.selectedItemPosition].schoolID
            intent.putExtra("SchoolID", schoolID)
            startActivity(i)

//                view ->
//            Snackbar.make(view, "Add classes", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
        }

    }

    override fun onResume() {
        classes_recycler.layoutManager = LinearLayoutManager(this)
        classes_recycler.hasFixedSize()
        //schools_recycler.adapter = SchoolAdapter(schoolList)
        classes_recycler.adapter = ClassDBAdapter(this, dbHandler.getClasses(this))
        super.onResume()
    }

    fun studentsItemClicked(classItem : ClassesData) {

            Toast.makeText(this, "Clicked: ${classItem.className}", Toast.LENGTH_LONG).show()

            val intent = Intent(this,StudentsActivity::class.java);
            var className = classItem.className
            var schoolName = schools_spinner.selectedItem.toString()
            intent.putExtra("Classname", className)
            intent.putExtra("Schoolname", schoolName)
            startActivity(intent);

    }
}

