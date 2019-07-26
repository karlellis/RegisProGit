package tv.soylent.regispro

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.classes_item.view.class_item_name
import kotlinx.android.synthetic.main.home_classes_item.view.*

class HomeClassDBAdapter (mCtx : Context, val classes : ArrayList<Klass>) : RecyclerView.Adapter<HomeClassDBAdapter.ViewHolder>() {


    val mCtx = mCtx

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val txtClassName = itemView.class_item_name
        val btnAvarage = itemView.home_class_avarages_img
        val btnRanks = itemView.home_class_ranks_img
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeClassDBAdapter.ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.home_classes_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return classes.size
    }

    override fun onBindViewHolder(holder: HomeClassDBAdapter.ViewHolder, position: Int) {
        val  klass  : Klass = classes[position]
        holder.txtClassName.text = klass.className

        holder.btnAvarage.setOnClickListener {
//            val className = klass.className
//
//            var alertDialog = AlertDialog.Builder(mCtx)
//                .setTitle("Attenzione")
//                .setMessage("Cancellare definitivamente : $className")
//                .setPositiveButton("Si", DialogInterface.OnClickListener {dialog, whitch ->
//                    if (dbHandler.deleteClass(klass.classID, klass.schoolID)) {
//                        classes.removeAt(position)
//                        notifyItemRemoved(position)
//                        notifyItemRangeChanged(position,classes.size)
//                        Toast.makeText(mCtx, "Classe $className rimossa", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(mCtx, "Errore nella rimozione", Toast.LENGTH_SHORT).show()
//                    }
//                })
//                .setNegativeButton("No", DialogInterface.OnClickListener(){dialog,which ->})
//                .setIcon(R.drawable.ic_warning_black_24dp)
//                .show()
        }

        holder.txtClassName.setOnClickListener {v ->
            val intent = Intent(v.context,StudentsActivity::class.java)
            var className = klass.className
            var schoolName = klass.schoolID.toString()
            intent.putExtra("Classname", className)
            intent.putExtra("Schoolname", schoolName)
            v.context.startActivity(intent)
        }

        holder.btnRanks.setOnClickListener { v ->
            val intent = Intent(v.context,TestsActivity::class.java)
            var className = klass.className
            var schoolName = klass.schoolID.toString()
            intent.putExtra("Classname", className)
            intent.putExtra("Schoolname", schoolName)
            v.context.startActivity(intent)
        }


    }




}