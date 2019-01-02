package com.example.edward.vogellaexe

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.string_list_item.view.*

/**
 * Created by Edward on 1/2/2019.
 */
class SimpleStringAdapter(val context: Context): RecyclerView.Adapter<SimpleStringAdapter.ViewHolder>(){
    private val mStrings = ArrayList<String>()

    fun setStrings(newStrings: List<String>){
        mStrings.clear()
        mStrings.addAll(newStrings)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.string_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mStrings.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.colorTextView.text = mStrings[position]
        holder.itemView.setOnClickListener {
            Toast.makeText(context, mStrings[position], Toast.LENGTH_SHORT).show()
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val colorTextView: TextView = itemView.textView
    }
}