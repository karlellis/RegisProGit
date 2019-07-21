package tv.soylent.regispro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.ranks_date_item.view.*

class RankDateAdapter (val rankItemList: MutableList<RanksData> = mutableListOf()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Inflate XML. Last parameter: don't immediately attach new view to the parent view group
        val viewDate = inflater.inflate(R.layout.ranks_date_item, parent, false)
        return RankViewHolder(viewDate)

    }

    override fun getItemCount() = rankItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RankViewHolder).bind(rankItemList[position])
    }
    inner class RankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(ranks: RanksData) {

            itemView.date_ranks_date.text = ranks.rankDate
            itemView.date_ranks_subject.text = ranks.subjectID.toString()
            itemView.date_ranks_type.text = ranks.rankType



        }
        private fun remove(): (View) -> Unit = {v: View ->
            layoutPosition.also { currentPosition ->
                rankItemList.removeAt(currentPosition)
                notifyItemRemoved(currentPosition)
            }

        }
        private fun add(){}
    }
}
