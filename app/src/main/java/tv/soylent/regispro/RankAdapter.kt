package tv.soylent.regispro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.ranks_item.view.*



class RankAdapter (val rankItemList: MutableList<RanksData> = mutableListOf()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Inflate XML. Last parameter: don't immediately attach new view to the parent view group
        val view = inflater.inflate(R.layout.ranks_item, parent, false)
        return RankViewHolder(view)
    }

    override fun getItemCount() = rankItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RankViewHolder).bind(rankItemList[position])
    }

    inner class RankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(ranks: RanksData) {

            itemView.ranks_rate.text = ranks.rankRate.toString()
            // notifyDataSetChanged()

        }
        private fun remove(): (View) -> Unit = {v: View ->
            layoutPosition.also { currentPosition ->
                rankItemList.removeAt(currentPosition)
                notifyItemRemoved(currentPosition)
            }

        }
        private fun add(){}
        fun refresh() {
            notifyDataSetChanged()
        }
    }
}