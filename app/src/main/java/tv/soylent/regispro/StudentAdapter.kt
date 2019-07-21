package tv.soylent.regispro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.students_item.view.*

class StudentAdapter (val studentItemList: MutableList<StudentsData> = mutableListOf()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Inflate XML. Last parameter: don't immediately attach new view to the parent view group
        val view = inflater.inflate(R.layout.students_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount() = studentItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StudentViewHolder).bind(studentItemList[position])
    }
    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(students: StudentsData) {
            itemView.stud_trash_img.setImageResource(students.studentDelete)
            itemView.student_item_name.text = students.studentName
            itemView.student_item_id.text = students.studentID.toString()
            itemView.stud_trash_img.setOnClickListener(remove())
        }
        private fun remove(): (View) -> Unit = {v: View ->
            layoutPosition.also { currentPosition ->
                studentItemList.removeAt(currentPosition)
                notifyItemRemoved(currentPosition)
            }

        }
        private fun add(){}
    }
}