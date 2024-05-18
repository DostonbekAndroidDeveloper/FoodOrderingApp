package com.dostonbek.fixeddastavka.customer

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dostonbek.fixeddastavka.R
import com.dostonbek.fixeddastavka.admin.AdminOrderInfoActivity
import com.dostonbek.fixeddastavka.utils.CustomerDataClass

class CustomerOrderAdapter(private var list: List<CustomerDataClass>, private var selectedItemColor: Int? = null) :
    RecyclerView.Adapter<CustomerOrderAdapter.CustomerOrderViewHolder>() {
    class CustomerOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.order_item_food_name)
        val cost: TextView = itemView.findViewById(R.id.order_item_food_cost)
        val number: TextView = itemView.findViewById(R.id.order_item_food_number)
        val location: TextView = itemView.findViewById(R.id.order_item_food_location)

        val phoneNumber: TextView = itemView.findViewById(R.id.customer_phone_number)
        val homeNumber: TextView = itemView.findViewById(R.id.customer_home_Number)

        val totalCost: TextView = itemView.findViewById(R.id.totalPayment)

    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateSelectedItemColor(color: Int) {
        selectedItemColor = color
        notifyDataSetChanged()
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerOrderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.customer_item_order, parent, false)
        return CustomerOrderViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CustomerOrderViewHolder, position: Int) {
        val admin = list[position]
        holder.name.text = admin.name
        holder.cost.text = admin.cost
        holder.number.text = admin.number
        holder.location.text = admin.location

        holder.phoneNumber.text = admin.phoneNumber
        holder.homeNumber.text = admin.homeNumber
        holder.totalCost.text = admin.totalCost
        holder.itemView.setOnClickListener{
val intent= Intent(holder.itemView.context,AdminOrderInfoActivity::class.java).apply {
    putExtra("order_Id",admin.id)
}
            holder.itemView.context.startActivity(intent)
        }







    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshData(newCustomerOrder: List<CustomerDataClass>) {
        list = newCustomerOrder
        notifyDataSetChanged()

    }
}