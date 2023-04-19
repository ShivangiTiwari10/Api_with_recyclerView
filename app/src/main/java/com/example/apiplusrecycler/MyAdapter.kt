package com.example.apiplusrecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val list: ArrayList<DataModelItem>, val context: Context) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = list[position]
        holder.apply {
            userIdTxt.text = currentItem.id.toString()
            idTxt.text = currentItem.userId.toString()
            titleTxt.text = currentItem.title
            bodyText.text = currentItem.body

        }
        holder.itemView.setOnClickListener{
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val userIdTxt: TextView = itemView.findViewById(R.id.userIdText)
        val idTxt: TextView = itemView.findViewById(R.id.idText)
        val titleTxt: TextView = itemView.findViewById(R.id.titleText)

        val bodyText: TextView = itemView.findViewById(R.id.bodyText)

    }

}