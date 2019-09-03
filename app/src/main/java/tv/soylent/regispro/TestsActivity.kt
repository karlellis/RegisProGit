package tv.soylent.regispro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_schools.*
import kotlinx.android.synthetic.main.activity_schools.toolbar
import kotlinx.android.synthetic.main.activity_subjects.*
import kotlinx.android.synthetic.main.activity_tests.*
import com.applandeo.materialcalendarview.utils.DateUtils.getCalendar
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import java.util.*
import kotlin.collections.ArrayList


class TestsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tests)
        setSupportActionBar(toolbar)

        var schoolList = ArrayList<SchoolsData>()
        schoolList.add(SchoolsData(1, "Scuola1", R.drawable.ic_delete))
        schoolList.add(SchoolsData(2, "Scuola2", R.drawable.ic_delete))

        var testClassName = intent.getStringExtra("ClassName")
        var testSchoolID = intent.getIntExtra("SchoolID",0)
        var testClassID = intent.getIntExtra("ClassID",0)

        tests_schools_title.text = "Istituto " + testSchoolID + " " + testClassName

        tests_calendar.setOnDayClickListener(OnDayClickListener { eventDay ->
            val clickedDayCalendar = eventDay.calendar
            val date = clickedDayCalendar.get(Calendar.DATE).toString() + "/" + (clickedDayCalendar.get(Calendar.MONTH) + 1).toString()

//            Toast.makeText(this,"Date: " + clickedDayCalendar.get(Calendar.DATE), Toast.LENGTH_SHORT).show()

            val intent = Intent(this,RanksActivity::class.java)
            intent.putExtra("Day", date)
            intent.putExtra("ClassName", testClassName)
            intent.putExtra("SchoolID", testSchoolID)
            intent.putExtra("ClassID", testClassID)
            this.startActivity(intent)

        })

        avarages_btn.setOnClickListener {

                view ->
            Snackbar.make(view, "Go avarages", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

    }
}

