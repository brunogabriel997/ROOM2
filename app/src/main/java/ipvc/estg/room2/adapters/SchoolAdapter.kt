package ipvc.estg.room2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.room2.R
import ipvc.estg.room2.entities.Escola

class SchoolAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var schools = emptyList<Escola>() // Cached copy of cities

    class SchoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val schoolItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return SchoolViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        val current = schools[position]
        holder.schoolItemView.text = current.id.toString() + " - " + current.school + "-" + current.distrit
    }

    internal fun setSchools(schools: List<Escola>) {
        this.schools = schools
        notifyDataSetChanged()
    }

    override fun getItemCount() = schools.size
}