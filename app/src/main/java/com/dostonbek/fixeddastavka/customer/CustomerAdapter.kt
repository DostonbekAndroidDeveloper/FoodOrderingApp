package com.dostonbek.fixeddastavka.customer

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dostonbek.fixeddastavka.R
import com.dostonbek.fixeddastavka.utils.AdminData


class CustomerAdapter(private var list:List<AdminData>):RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {
    class CustomerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val name: TextView =itemView.findViewById(R.id.item_food_name)
        val cost: TextView =itemView.findViewById(R.id.item_food_cost)
        val button:TextView=itemView.findViewById(R.id.buyurtma_berish)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_food,parent,false)
        return CustomerViewHolder(view)
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val admin=list[position]
        holder.name.text=admin.name
        holder.cost.text=admin.cost
        holder.button.setOnClickListener {
            val intent=Intent(holder.itemView.context,CustomerAddOrderActivity::class.java).apply {
                putExtra("customer_id",admin.id)
            }
            holder.itemView.context.startActivity(intent)
        }


    }
    @SuppressLint("NotifyDataSetChanged")
    fun refreshData(newAdmin: List<AdminData>){
        list=newAdmin
        notifyDataSetChanged()

    }
}