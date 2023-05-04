package com.example.stm

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.Note

class MyAdapter(private val context: Context, private var dataList: List<Note>, private val fragmentManager: FragmentManager) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.recTitle.text = dataList[position].dataTitle


        holder.recCard.setOnClickListener {
            val orientation = context.resources?.configuration?.orientation

            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // We're in landscape mode, so show the fragment in a dialog
                val fragment = DetailFragment.newInstance(dataList[holder.adapterPosition].dataTitle,dataList[holder.adapterPosition].dataContent,dataList[holder.adapterPosition].dataDate)
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()

            } else {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("Title", dataList[holder.adapterPosition].dataTitle)
                intent.putExtra("Content", dataList[holder.adapterPosition].dataContent)

                intent.putExtra("Date", dataList[holder.adapterPosition].dataDate)

                context.startActivity(intent)
            }
        }
        holder.recCard.setOnLongClickListener{


                val builder = AlertDialog.Builder(this.context)
                builder.setMessage("Are you sure you want to Delete?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        Note.data.removeAt(position)

                        val intent = Intent(this.context, NotesActivity::class.java)
                        this.context.startActivity(intent)

                    }
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()

            return@setOnLongClickListener true


        }

    }



    override fun getItemCount(): Int {
        return dataList.size
    }
    fun searchDataList(searchList: List<Note>) {
        dataList = searchList
        notifyDataSetChanged()
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var recTitle: TextView
    var recCard: CardView

    init {
        recTitle = itemView.findViewById(R.id.recTitle)
        recCard = itemView.findViewById(R.id.recCard)
    }
}