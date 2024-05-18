package com.dostonbek.fixeddastavka.admin

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dostonbek.fixeddastavka.R
import com.dostonbek.fixeddastavka.utils.AdminData

class AdminAdapter(private var admins:List<AdminData>):RecyclerView.Adapter<AdminAdapter.AdminViewHolder>() {
    class AdminViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val name:TextView=itemView.findViewById(R.id.item_admin_food_name)
        val cost:TextView=itemView.findViewById(R.id.item_admin_food_cost)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminViewHolder {
     val view=LayoutInflater.from(parent.context).inflate(R.layout.item_admin_food,parent,false)
        return AdminViewHolder(view)
    }

    override fun getItemCount(): Int =admins.size

    override fun onBindViewHolder(holder: AdminViewHolder, position: Int) {
val admin=admins[position]
        holder.name.text=admin.name
        holder.cost.text=admin.cost

    }
    fun refreshData(newAdmin: List<AdminData>){
        admins=newAdmin
        notifyDataSetChanged()

    }
}