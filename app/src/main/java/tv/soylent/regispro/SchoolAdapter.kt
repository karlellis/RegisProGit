package tv.soylent.regispro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.schools_item.view.*

class SchoolAdapter (val schoolItemList: MutableList<SchoolsData> = mutableListOf()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Inflate XML. Last parameter: don't immediately attach new view to the parent view group
        val view = inflater.inflate(R.layout.schools_item, parent, false)
        return SchoolViewHolder(view)
    }

    override fun getItemCount() = schoolItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SchoolViewHolder).bind(schoolItemList[position])
    }
    inner class SchoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(schools: SchoolsData) {
            itemView.school_trash_img.setImageResource(schools.schoolDelete)
            itemView.school_item_name.text = schools.schoolName
            itemView.school_trash_img.setOnClickListener(remove())
        }
        private fun remove(): (View) -> Unit = {v: View ->
            layoutPosition.also { currentPosition ->
                schoolItemList.removeAt(currentPosition)
                notifyItemRemoved(currentPosition)
            }

        }
    }

}
