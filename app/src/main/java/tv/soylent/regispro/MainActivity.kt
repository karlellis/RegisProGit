package tv.soylent.regispro

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_subjects.*
import android.app.Activity




class MainActivity : AppCompatActivity(), ClassesFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {

    }

    companion object {
        lateinit var dbHandler: DBHandler
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        dbHandler = DBHandler(this,null,null,1)

        val schoolList = dbHandler.getSchools(this)

        val tabAdapter = TabViewPagerAdapter(supportFragmentManager)
        for (i in 0 until schoolList.size) {
            tabAdapter.addFragment(ClassesFragment(), title = schoolList[i].schoolName.toString())
//            tabAdapter.addFragment(ClassesFragment(), title = "Scuola2")
        }

        viewPager.adapter = tabAdapter
        schools_tab.setupWithViewPager(viewPager)

        schools_btn.setOnClickListener {
            startActivity(
                Intent(this, SchoolsActivity::class.java))
        }

        subjects_btn.setOnClickListener {
            startActivity(
                Intent(this, SubjectsActivity::class.java))

        }
        classes_btn.setOnClickListener {
        startActivity(
            Intent(this, ClassesActivity::class.java))
        }

    }

    override fun onResume() {
        schools_tab.removeAllTabs()
        val schoolList = dbHandler.getSchools(this)
        val tabAdapter = TabViewPagerAdapter(supportFragmentManager)
        for (i in 0 until schoolList.size) {
            tabAdapter.addFragment(ClassesFragment(), title = schoolList[i].schoolName.toString())
//            tabAdapter.addFragment(ClassesFragment(), title = "Scuola2")
        }
        viewPager.adapter = tabAdapter
        schools_tab.setupWithViewPager(viewPager)
        super.onResume()
    }

    class TabViewPagerAdapter (manager: FragmentManager) : FragmentPagerAdapter(manager) {

        private val fragmentList : MutableList<Fragment> = ArrayList()
        private val titleList : MutableList<String> = ArrayList()

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        fun addFragment (fragment: Fragment,title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        var selectedOption = ""

        when(item.itemId) {
            R.id.action_settings -> selectedOption = "Impostazioni"
            R.id.action_load -> selectedOption = "Carica"
            R.id.action_save -> selectedOption = "Salva"
        }

    Toast.makeText(this,"option: " + selectedOption,Toast.LENGTH_SHORT).show()

    return super.onOptionsItemSelected(item)
    }
}
