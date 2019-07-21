package tv.soylent.regispro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.subjects_item.view.*

class SubjectsAdapter (val subjectItemList: MutableList<SubjectsData> = mutableListOf()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Inflate XML. Last parameter: don't immediately attach new view to the parent view group
        val view = inflater.inflate(R.layout.subjects_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun getItemCount() = subjectItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SubjectViewHolder).bind(subjectItemList[position])
    }
    inner class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(subjects: SubjectsData) {
            itemView.subj_trash_img.setImageResource(subjects.subjectDelete)
            itemView.subject_item_name.text = subjects.subjectName
            itemView.subj_trash_img.setOnClickListener(remove())

        }
        private fun remove(): (View) -> Unit = {v: View ->
            layoutPosition.also { currentPosition ->
                subjectItemList.removeAt(currentPosition)
                notifyItemRemoved(currentPosition)
            }

        }
        private fun add(){}
    }
}