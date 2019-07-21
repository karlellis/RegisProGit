package tv.soylent.regispro

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.classes_item.view.class_item_name
import kotlinx.android.synthetic.main.home_classes_item.view.*


class HomeClassAdapter (val homeClassItemList: List<HomeClassesData> /* , val clickListener: (SubjectsData) -> Unit */) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Inflate XML. Last parameter: don't immediately attach new view to the parent view group
        val view = inflater.inflate(R.layout.home_classes_item, parent, false)
        return HomeClassViewHolder(view)
    }

    override fun getItemCount() = homeClassItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeClassViewHolder).bind(homeClassItemList[position])
    }
    inner class HomeClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(homeClasses: HomeClassesData) {
            itemView.class_item_name.text = homeClasses.className
            itemView.home_class_ranks_img.setImageResource(homeClasses.classRanks)
            itemView.home_class_avarages_img.setImageResource(homeClasses.classAvarages)

            itemView.class_item_name.setOnClickListener { v ->
                val intent = Intent(v.context,StudentsActivity::class.java)
                var className = homeClasses.className
                var schoolName = homeClasses.schoolID.toString()
                intent.putExtra("Classname", className)
                intent.putExtra("Schoolname", schoolName)
                v.context.startActivity(intent)
            }

            itemView.home_class_ranks_img.setOnClickListener { v ->
                val intent = Intent(v.context,TestsActivity::class.java)
                var className = homeClasses.className
                var schoolName = homeClasses.schoolID.toString()
                intent.putExtra("Classname", className)
                intent.putExtra("Schoolname", schoolName)
                v.context.startActivity(intent)

            }


        }
    }
}