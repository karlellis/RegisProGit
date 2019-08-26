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
import kotlinx.android.synthetic.main.lo_student_update.view.*
import kotlinx.android.synthetic.main.students_item.view.*
import tv.soylent.regispro.MainActivity.Companion.dbHandler

class StudentDBAdapter (mCtx : Context, val students : ArrayList<Student>) : RecyclerView.Adapter<StudentDBAdapter.ViewHolder>() {


    val mCtx = mCtx

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val txtStudentPos = itemView.student_item_pos
        val txtStudentName = itemView.student_item_name
        val txtStudentSurname = itemView.student_item_surname
        val btnRanks = itemView.student_ranks_img
        val btnDelete = itemView.student_trash_img

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentDBAdapter.ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.students_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onBindViewHolder(holder: StudentDBAdapter.ViewHolder, position: Int) {
        val  student  : Student = students[position]
        holder.txtStudentName.text = student.studentName
        holder.txtStudentSurname.text = student.studentSurname
        holder.txtStudentPos.text = position.toString()

        holder.btnRanks.setOnClickListener {
            // to do
        }

        holder.btnDelete.setOnClickListener {
            val studentName = student.studentName
            val studentSurname = student.studentSurname

            var alertDialog = AlertDialog.Builder(mCtx)
                .setTitle("Attenzione")
                .setMessage("Cancellare definitivamente : $studentName $studentSurname")
                .setPositiveButton("Si", DialogInterface.OnClickListener {dialog, whitch ->
                    if (dbHandler.deleteStudent(student.studentID, student.schoolID, student.classID)) {
                        students.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position,students.size)
                        Toast.makeText(mCtx, "Studente $studentName $studentSurname rimosso", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(mCtx, "Errore nella rimozione", Toast.LENGTH_SHORT).show()
                    }
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->})
                .setIcon(R.drawable.ic_warning_black_24dp)
                .show()
        }

        holder.txtStudentName.setOnClickListener {
            val inflater = LayoutInflater.from(mCtx)
            val view = inflater.inflate(R.layout.lo_student_update, null)

            val txtStudentName : TextView = view.findViewById(R.id.editUpStudentName)
            val txtStudentSurname : TextView = view.findViewById(R.id.editUpStudentSurname)

            txtStudentName.text = student.studentName
            txtStudentSurname.text = student.studentSurname

            val builder = AlertDialog.Builder(mCtx)
                .setTitle("Modifica nome Studente")
                .setView(view)
                .setPositiveButton("Modifica", DialogInterface.OnClickListener { dialog, which ->
                    val isUpdate = dbHandler.updateStudent(
                        student.studentID.toString(),
                        view.editUpStudentName.text.toString(), view.editUpStudentSurname.text.toString())
                    if (isUpdate == true) {
                        students[position].studentName = view.editUpStudentName.text.toString()
                        students[position].studentSurname = view.editUpStudentSurname.text.toString()
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

        holder.btnRanks.setOnClickListener { v ->
            val intent = Intent(v.context,StudentsActivity::class.java)
            var studentName = student.studentName
            var studentSurname = student.studentSurname
            var className = student.classID.toString()
            var schoolName = student.schoolID.toString()

            intent.putExtra("Studentname", studentName)
            intent.putExtra("StudentSurname", studentSurname)
            intent.putExtra("Classname", className)
            intent.putExtra("Schoolname", schoolName)
            v.context.startActivity(intent)
        }


    }




}