package tv.soylent.regispro

import android.R.attr.fastScrollOverlayPosition
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_ranks.*
import kotlinx.android.synthetic.main.activity_subjects.toolbar
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.style.BackgroundColorSpan
import android.util.AttributeSet
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import org.w3c.dom.Text
import android.util.TypedValue
import android.R.attr.gravity
import android.annotation.SuppressLint
import android.icu.text.Transliterator
import android.view.*
import android.widget.*
import android.widget.LinearLayout.LayoutParams
import androidx.core.view.ViewCompat.canScrollVertically
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.ListAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent


class RanksActivity : AppCompatActivity() {
    var rankList = ArrayList<RanksData>()

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranks)
        setSupportActionBar(toolbar)
        var mCurX = 0

        rankList.add(RanksData(1, 1, 1, "16-05", "S", 8, true))
        rankList.add(RanksData(2, 1, 1, "23-05", "O", 9, true))
        rankList.add(RanksData(3, 1, 1, "03-06", "S", 10, true))
        rankList.add(RanksData(4, 1, 1, "16-05", "S", 8, true))
        rankList.add(RanksData(5, 1, 1, "23-05", "O", 8, true))
        rankList.add(RanksData(6, 1, 1, "03-06", "S", 8, true))
        rankList.add(RanksData(7, 1, 1, "16-05", "S", 8, true))
        rankList.add(RanksData(8, 1, 1, "23-05", "O", 8, true))
        rankList.add(RanksData(9, 1, 1, "03-06", "S", 8, true))
        rankList.add(RanksData(10, 1, 1, "16-05", "S", 8, true))
        rankList.add(RanksData(11, 1, 1, "23-05", "O", 8, true))
        rankList.add(RanksData(12, 1, 1, "03-06", "S", 8, true))
        rankList.add(RanksData(13, 1, 1, "16-05", "S", 8, true))
        rankList.add(RanksData(14, 1, 1, "23-05", "O", 9, true))
        rankList.add(RanksData(15, 1, 1, "03-06", "S", 10, true))
        rankList.add(RanksData(16, 1, 1, "16-05", "S", 8, true))
        rankList.add(RanksData(17, 1, 1, "23-05", "O", 8, true))
        rankList.add(RanksData(18, 1, 1, "03-06", "S", 8, true))
        rankList.add(RanksData(19, 1, 1, "16-05", "S", 8, true))
        rankList.add(RanksData(20, 1, 1, "23-05", "O", 8, true))
        rankList.add(RanksData(21, 1, 1, "03-06", "S", 8, true))
        rankList.add(RanksData(22, 1, 1, "16-05", "S", 8, true))
        rankList.add(RanksData(23, 1, 1, "23-05", "O", 8, true))
        rankList.add(RanksData(24, 1, 1, "03-06", "S", 8, true))

        //subjects_recycler.layoutManager = GridLayoutManager(this,2)
        students_ranks_recycler.layoutManager = LinearLayoutManager(this)
        students_ranks_recycler.hasFixedSize()
        students_ranks_recycler.adapter = RankStudentAdapter(rankList)

//        date_ranks_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
////        date_ranks_recycler.layoutManager = LinearLayoutManager(this)
//        date_ranks_recycler.hasFixedSize()
//        date_ranks_recycler.adapter = RankDateAdapter(rankList)
//
////        val lm = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
////        val lm = LinearLayoutManager(this)
//        val lm = GridLayoutManager(this,3)
//        ranks_recycler.layoutManager = lm
//        ranks_recycler.hasFixedSize()
//        ranks_recycler.adapter = RankAdapter(rankList)

        //--------------------------------------------------------------//

//        val dateLLayout = ranks_date_linearlayout
//        Log.d("RankList size ", (rankList.size.toString()))
//        for (i in 0 until rankList.size - 1) {
//
//
//            val view = RecyclerView(this)
//            val lmr = LinearLayoutManager(this)
//            val layoutParams = LayoutParams(
//                LayoutParams.MATCH_PARENT,
//                LayoutParams.MATCH_PARENT
//            )
//
//            view.layoutParams = layoutParams
//            view.layoutManager = lmr
//            view.hasFixedSize()
//            view.adapter = RankDateAdapter(rankList, i)
//            view.id = (i)
//
//            dateLLayout.addView(view)
//        }
//
//        Log.d("DATE CHILDS NUMBER: ", dateLLayout.childCount.toString())

        //--------------------------------------------------------------//

        //--------------------------------------------------------------//

//        val lLayout = ranks_linearlayout
//        Log.d("RankList size ", (rankList.size.toString()))
//        for (i in 0 until rankList.size - 1) {
//
//
//            val view = RecyclerView(this)
//            val lmr = LinearLayoutManager(this)
////            val lmr = GridLayoutManager(this, 1)
////            val lmr = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//
////            val lmr = CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
////            lmr.setScrollEnabled(false)
//
//            val layoutParams = LayoutParams(
//                LayoutParams.MATCH_PARENT,
//                LayoutParams.WRAP_CONTENT
//            )
//
////        lLayout.layoutParams = layoutParams
//            view.layoutParams = layoutParams
//            view.layoutManager = lmr
//            view.hasFixedSize()
//            view.adapter = RankAdapter(rankList)
//            view.id = (i)
//
////            Log.d("I ", (i.toString()))
//
////            date_ranks_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
////                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
////                    super.onScrolled(recyclerView, dx, dy)
////                    //Log.v("onScrolled", "dx:$dx dy:$dy")
////                    if (view.id == rankList.size - 2){
////                        findViewById<RecyclerView>(i).scrollBy(dx, 0)
////                        findViewById<RecyclerView>(i-1).scrollBy(dx, 0)
//////                        Log.d("LAST View ID ", (i.toString()))
////                    } else if (view.id == 0){
////                        ranks_recycler.scrollBy(dx, 0)
//////                        Log.d("First View ID: ", (i.toString()))
////                    } else if(view.id  >= 1) {
////                        findViewById<RecyclerView>(i-1).scrollBy(dx, 0)
//////                        Log.d("View ID: ", (i.toString()))
////                    }
////                }
////            })
//
//            lLayout.addView(view)
//        }
//
////        val scrollListener =  object: RecyclerView.OnScrollListener()
////        //val viewScrollListener = object: HorizontalScrollView.viewTreeObserver.addOnScrollChangedListener {}
////
////        date_ranks_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
////            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
////                super.onScrolled(recyclerView, dx, dy)
////                horiz_ranks_scrollview.viewTreeObserver.removeOnScrollChangedListener(viewScrollListener)
////                horiz_ranks_scrollview.scrollBy(dx, 0)
////                horiz_ranks_scrollview.viewTreeObserver.addOnScrollChangedListener(viewScrollListener)
////            }
////        })
////
////        val scrollListener = object : RecyclerView.OnScrollListener() {
////            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
////                super.onScrolled(recyclerView, dx, dy)
////                date_ranks_recycler.removeOnScrollListener(viewScrollListener)
////                date_ranks_recycler.scrollBy(dx, dy)
////                date_ranks_recycler.addOnScrollListener(scrollListeners[1]!!)
////            }
////        }
////
////        val viewScrollListener = horiz_ranks_scrollview.viewTreeObserver.addOnScrollChangedListener {
////            override fun onScrolled(horizView: HorizontalScrollView, dx: Int, dy: Int) {
////                super.onScrolled(recyclerView, dx, dy)
////                horiz_ranks_scrollview.viewTreeObserver.removeOnScrollChangedListener(scrollListener)
////                horiz_ranks_scrollview.scrollBy(dx, dy)
////                horiz_ranks_scrollview.viewTreeObserver.addOnScrollChangedListener(scrollListener)
////            }
////        }
////
////        horiz_ranks_scrollview.viewTreeObserver.addOnScrollListener(scrollListeners[0])
////        date_ranks_recycler.addOnScrollListener(scrollListeners[1])
//
////        horiz_ranks_scrollview.viewTreeObserver.addOnScrollChangedListener(
////            ViewTreeObserver.OnScrollChangedListener {
////                val scrollX = horiz_ranks_scrollview.scrollX
////                Log.v("onScrolled", scrollX.toString())
////                date_ranks_recycler.scrollBy(scrollX, 0)
////
////            })
////
////        horiz_ranks_scrollview.setOnScrollChangeListener {
////                v, scrollX, scrollY, oldScrollX, oldScrollY ->
////
//////            Log.v("Horiz scroll value ", "dx:$scrollX - $oldScrollX")
////            hosudoriz_date_scrollview.scrollBy(scrollX - oldScrollX, 0)
//////            date_ranks_recycler.scrollBy(scrollX - oldScrollX, 0)
////        }
////
////        horiz_ranks_scrollview.setOnScrollChangeListener( {
////                v, scrollX, scrollY, oldScrollX, oldScrollY ->
////            Log.d(
////                "ScrollView",
////                "scrollX_" + scrollX + "_scrollY_" + scrollY + "_oldScrollX_" + oldScrollX + "_oldScrollY_" + oldScrollY
////            )
////            //Do something
////        })
//
////        horiz_ranks_scrollview.setOnTouchListener(View.OnTouchListener { v, event ->
////            val scrollX = v.scrollX
////            Log.v("onScrolled", scrollX.toString())
////            date_ranks_recycler.scrollBy(scrollX, 0)
////            return@OnTouchListener false
////        })
//
////        date_ranks_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
////            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
////                super.onScrolled(recyclerView, dx, dy)
////                if (recyclerView == date_ranks_recycler) {
////                Log.v("Recycler scroll value ", "dx:$dx")
//////                students_ranks_recycler.scrollBy(dx, dy)
////                horiz_ranks_scrollview.scrollBy(dx, 0)
////                }
////
////            }
////        })
//
////        val layoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
////            override fun onGlobalLayout() {
////                horiz_ranks_scrollview.getViewTreeObserver().removeGlobalOnLayoutListener(this)
////                horiz_ranks_scrollview.getViewTreeObserver().addOnScrollChangedListener(OnScrollListener)
////            }
////        }
//
////        horiz_ranks_scrollview.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener)
////
////        horiz_ranks_scrollview.viewTreeObserver.addOnGlobalLayoutListener( ViewTreeObserver.OnGlobalLayoutListener() {
////
////            @Override fun onGlobalLayout() {
////                date_ranks_recycler.scrollTo(horiz_ranks_scrollview.scrollTo(horiz_ranks_scrollview.getScrollX(), 0).hashCode(),0);
////            }
////        })
//
////        val inflater = LayoutInflater.from(this)
////        val rootView = inflater.inflate(R.layout.ranks_item, null)
////        Log.d("Step: ", "After rootView")
////        val lLayout = ranks_linearlayout
//////        val lLayout = rootView.findViewById<LinearLayout>(R.id.RanksRatelinearLayout)
////        val view = rootView.findViewById<View>(R.id.ranks_rate) as TextView
////
////        for (i in 0 until rankList.size-1) {
////            val textView2 = TextView(this)
////            val layoutParams = LayoutParams(
////                LayoutParams.MATCH_PARENT,
////                LayoutParams.WRAP_CONTENT
////            )
////            layoutParams.gravity = Gravity.CENTER
////            layoutParams.setMargins(4, 6, 4, 2) // (left, top, right, bottom)
////
////            textView2.text = "TEST"
////            textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
////            textView2.setBackgroundColor(-0x2425) // hex color 0xAARRGGBB
////            textView2.layoutParams = layoutParams
////
//////            if (textView2.parent != null) {
//////                Log.d("PARENT: ", textView2.parent.toString())
//////                (textView2.parent as ViewGroup).removeView(textView2) // <- fix
//////            }
////
////            lLayout.addView(textView2)
////            Log.d("PARENT: ", textView2.parent.toString())
////
////        }
//
//        Log.d("CHILDS NUMBER: ", lLayout.childCount.toString())

        //--------------------------------------------------------------//

        ranks_school_title.text = "Istituto " + intent.getStringExtra("Schoolname") + " " + intent.getStringExtra("Classname")

        ranks_date_label.text = intent.getStringExtra("Day")

//        nested_ranks_scrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener {
//                v, scrollX, scrollY, oldScrollX, oldScrollY ->
////            Log.d(
////                "ScrollView",
////                "scrollX_" + scrollX + "_scrollY_" + scrollY + "_oldScrollX_" + oldScrollX + "_oldScrollY_" + oldScrollY
////            )
//            if (v == nested_ranks_scrollview) {
//                students_ranks_recycler.scrollBy(scrollX - oldScrollX, scrollY - oldScrollY)
//                Log.v("Students onScrolled", "dx:$scrollX dy:$oldScrollX")
//            }
//        })



        ranks_add_btn.setOnClickListener {

                view ->
            Snackbar.make(view, "Add rank", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

//        if (nested_ranks_scrollview.parent != null) {
//                Log.d("PARENT: ", nested_ranks_scrollview.parent.toString())
//                (nested_ranks_scrollview.parent as ViewGroup).removeView(nested_ranks_scrollview) // <- fix
//            }
//        setContentView(nested_ranks_scrollview)

    }


}

