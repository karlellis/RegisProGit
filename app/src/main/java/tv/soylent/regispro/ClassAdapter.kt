package tv.soylent.regispro

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.classes_item.view.*

class ClassAdapter (val classItemList: MutableList<ClassesData> = mutableListOf()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Inflate XML. Last parameter: don't immediately attach new view to the parent view group
        val view = inflater.inflate(tv.soylent.regispro.R.layout.classes_item, parent, false)
        return ClassViewHolder(view)
    }

    override fun getItemCount() = classItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ClassViewHolder).bind(classItemList[position])
    }

    inner class ClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(classes: ClassesData) {

            itemView.class_trash_img.setImageResource(classes.classDelete)
            itemView.class_students_img.setImageResource(classes.classStudents)
            itemView.class_item_name.text = classes.className

            itemView.class_trash_img.setOnClickListener(remove())

            itemView.class_students_img.setOnClickListener { v ->
                val intent = Intent(v.context,StudentsActivity::class.java)
                var className = classes.className
                var schoolName = classes.schoolID.toString()
                intent.putExtra("Classname", className)
                intent.putExtra("Schoolname", schoolName)
                v.context.startActivity(intent)
            }
        }
        private fun remove(): (View) -> Unit = {v: View ->
            layoutPosition.also { currentPosition ->
                classItemList.removeAt(currentPosition)
                notifyItemRemoved(currentPosition)
            }

        }
        private fun add(){}
    }
}