package tv.soylent.regispro

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.classes_item.view.*
import kotlinx.android.synthetic.main.lo_class_update.view.*
import tv.soylent.regispro.MainActivity.Companion.dbHandler

class ClassDBAdapter (mCtx : Context, val classes : ArrayList<Klass>) : RecyclerView.Adapter<ClassDBAdapter.ViewHolder>() {


    val mCtx = mCtx

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val txtClassName = itemView.class_item_name
        val btnStudents = itemView.class_students_img
        val btnDelete = itemView.class_trash_img
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassDBAdapter.ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.classes_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return classes.size
    }

    override fun onBindViewHolder(holder: ClassDBAdapter.ViewHolder, position: Int) {
        val  klass  : Klass = classes[position]
        holder.txtClassName.text = klass.className

        holder.btnDelete.setOnClickListener {
            val className = klass.className

            var alertDialog = AlertDialog.Builder(mCtx)
                .setTitle("Attenzione")
                .setMessage("Cancellare definitivamente : $className")
                .setPositiveButton("Si", DialogInterface.OnClickListener {dialog, whitch ->
                    if (dbHandler.deleteClass(klass.classID, klass.schoolID)) {
                        classes.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position,classes.size)
                        Toast.makeText(mCtx, "Classe $className rimossa", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(mCtx, "Errore nella rimozione", Toast.LENGTH_SHORT).show()
                    }
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->})
                .setIcon(R.drawable.ic_warning_black_24dp)
                .show()
        }

        holder.txtClassName.setOnClickListener {
            val inflater = LayoutInflater.from(mCtx)
            val view = inflater.inflate(R.layout.lo_class_update, null)

            val txtClassName : TextView = view.findViewById(R.id.editUpClassName)

            txtClassName.text = klass.className

            val builder = AlertDialog.Builder(mCtx)
                .setTitle("Modifica nome Classe")
                .setView(view)
                .setPositiveButton("Modifica", DialogInterface.OnClickListener { dialog, which ->
                    val isUpdate = dbHandler.updateClass(
                        klass.classID.toString(),
                        view.editUpClassName.text.toString())
                    if (isUpdate == true) {
                        classes[position].className = view.editUpClassName.text.toString()
                        notifyDataSetChanged()
                        Toast.makeText(mCtx, "Modificata correttamente", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(mCtx, "Errore nella modifica", Toast.LENGTH_SHORT).show()
                    }
                })
                .setNegativeButton("Cancella", DialogInterface.OnClickListener { dialog, which ->})
            val alert = builder.create()
            alert.show()
        }

        holder.btnStudents.setOnClickListener { v ->
            val intent = Intent(v.context,StudentsActivity::class.java)
            var className = klass.className
            var schoolID = klass.schoolID
            var classID = klass.classID

//            Toast.makeText(mCtx, "Class+School " + className + " " + schoolID, Toast.LENGTH_SHORT).show()

            intent.putExtra("StudentClassName", className)
            intent.putExtra("StudentSchoolID", schoolID)
            intent.putExtra("StudentClassID", classID)

            v.context.startActivity(intent)
        }


    }




}