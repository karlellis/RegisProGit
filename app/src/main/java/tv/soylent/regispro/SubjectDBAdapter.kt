package tv.soylent.regispro

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.lo_school_update.view.*
import kotlinx.android.synthetic.main.lo_subject_update.view.*
import kotlinx.android.synthetic.main.schools_item.view.*
import kotlinx.android.synthetic.main.subjects_item.view.*
import tv.soylent.regispro.MainActivity.Companion.dbHandler

class SubjectDBAdapter (mCtx : Context, val subjects : ArrayList<Subject>) : RecyclerView.Adapter<SubjectDBAdapter.ViewHolder>() {


    val mCtx = mCtx

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val txtSubjectName = itemView.subject_item_name
        val btnDelete = itemView.subj_trash_img
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectDBAdapter.ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.subjects_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    override fun onBindViewHolder(holder: SubjectDBAdapter.ViewHolder, position: Int) {
        val  subject  : Subject = subjects[position]
        holder.txtSubjectName.text = subject.subjectName
        holder.btnDelete.setOnClickListener {
            val subjectName = subject.subjectName

            var alertDialog = AlertDialog.Builder(mCtx)
                .setTitle("Attenzione")
                .setMessage("Cancellare definitivamente : $subjectName")
                .setPositiveButton("Si", DialogInterface.OnClickListener {dialog, whitch ->
                    if (dbHandler.deleteSubject(subject.subjectID)) {
                        subjects.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position,subjects.size)
                        Toast.makeText(mCtx, "Materia $subjectName Rimossa", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(mCtx, "Errore nella rimozione", Toast.LENGTH_SHORT).show()
                    }
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->})
                .setIcon(R.drawable.ic_warning_black_24dp)
                .show()
        }

        holder.txtSubjectName.setOnClickListener {
            val inflater = LayoutInflater.from(mCtx)
            val view = inflater.inflate(R.layout.lo_subject_update, null)

            val txtSubjectName : TextView = view.findViewById(R.id.editUpSubjectName)

            txtSubjectName.text = subject.subjectName

            val builder = AlertDialog.Builder(mCtx)
                .setTitle("Modifica nome Materia")
                .setView(view)
                .setPositiveButton("Modifica", DialogInterface.OnClickListener { dialog, which ->
                    val isUpdate = dbHandler.updateSubject(
                        subject.subjectID.toString(),
                        view.editUpSubjectName.text.toString())
                    if (isUpdate == true) {
                        subjects[position].subjectName = view.editUpSubjectName.text.toString()
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


    }




}