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
import kotlinx.android.synthetic.main.schools_item.view.*
import tv.soylent.regispro.MainActivity.Companion.dbHandler

class SchoolDBAdapter (mCtx : Context, val schools : ArrayList<School>) : RecyclerView.Adapter<SchoolDBAdapter.ViewHolder>() {


    val mCtx = mCtx

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val txtSchoolName = itemView.school_item_name
        val btnDelete = itemView.school_trash_img
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolDBAdapter.ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.schools_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return schools.size
    }

    override fun onBindViewHolder(holder: SchoolDBAdapter.ViewHolder, position: Int) {
        val  school  : School = schools[position]
        holder.txtSchoolName.text = school.schoolName

        holder.btnDelete.setOnClickListener {
            val schoolName = school.schoolName

            var alertDialog = AlertDialog.Builder(mCtx)
                .setTitle("Attenzione")
                .setMessage("Cancellare definitivamente : $schoolName")
                .setPositiveButton("Si", DialogInterface.OnClickListener {dialog, whitch ->
                    if (dbHandler.deleteSchool(school.schoolID)) {
                        schools.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position,schools.size)
                        Toast.makeText(mCtx, "Istituto $schoolName Rimosso", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(mCtx, "Errore nella rimozione", Toast.LENGTH_SHORT).show()
                    }
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->})
                .setIcon(R.drawable.ic_warning_black_24dp)
                .show()
        }

        holder.txtSchoolName.setOnClickListener {
            val inflater = LayoutInflater.from(mCtx)
            val view = inflater.inflate(R.layout.lo_school_update, null)

            val txtSchoolName : TextView = view.findViewById(R.id.editUpSchoolName)

            txtSchoolName.text = school.schoolName

            val builder = AlertDialog.Builder(mCtx)
                .setTitle("Modifica nome Istituto")
                .setView(view)
                .setPositiveButton("Modifica", DialogInterface.OnClickListener { dialog, which ->
                    val isUpdate = dbHandler.updateSchool(
                        school.schoolID.toString(),
                        view.editUpSchoolName.text.toString())
                    if (isUpdate == true) {
                        schools[position].schoolName = view.editUpSchoolName.text.toString()
                        notifyDataSetChanged()
                        Toast.makeText(mCtx, "Modificato correttamente", Toast.LENGTH_SHORT).show()
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