package com.dostonbek.fixeddastavka.customer

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dostonbek.fixeddastavka.R
import com.dostonbek.fixeddastavka.database.DatabaseHelper
import com.dostonbek.fixeddastavka.databinding.ActivityCustomerAddOrderBinding
import com.dostonbek.fixeddastavka.utils.AdminData
import com.dostonbek.fixeddastavka.utils.CustomerDataClass
import kotlin.math.cos

class CustomerAddOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerAddOrderBinding
    private lateinit var database: DatabaseHelper
    private lateinit var adminData: AdminData
    private var customerId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerAddOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = DatabaseHelper(this)

        // Customer ID ni Intent orqali olish
        customerId = intent.getIntExtra("customer_id", -1)
        if (customerId == -1) {
            // Agar customer_id topilmagan bo'lsa aktivitini yopamiz
            finish()
            return
        }

        // Customer ID bo'yicha admin taomini olish
        adminData = database.getAdminFoodId(customerId!!)

        // Interfeys elementlarni ma'lumotlar bilan to'ldirish
        binding.foodName.text = adminData.name
        binding.paymentForAPiece.text = adminData.cost

        // Spinner uchun adapterlar
        val foodNumber = resources.getStringArray(R.array.food_number)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_menu, foodNumber)
        binding.itsNumber.setAdapter(arrayAdapter)

        val customerLocation = resources.getStringArray(R.array.customer_location)
        val locationAdapter = ArrayAdapter(this, R.layout.dropdown_menu, customerLocation)
        binding.customerLocation.setAdapter(locationAdapter)
        // 10 000
        // 10 000

        // Buyurtma qo'shish tugmasi


        binding.finishOrder.setOnClickListener {

            var name = binding.foodName.text.toString()

            var cost = binding.paymentForAPiece.text.toString()
            var number = binding.itsNumber.text.toString()
            var street = binding.customerLocation.text.toString()


            var phoneNumber = binding.phoneNumber.text.toString()
            var homeNumber = binding.homeNumber.text.toString()
            var totalCost = binding.overallPayment.text.toString()
            if (cost == "10000" && binding.itsNumber.text.toString() == "1") {
                totalCost= "10000"
            } else if (cost == "10000" && number == "2") {
                totalCost = "20000"
            } else if (cost == "10000" && number == "3") {
                totalCost = (10000*3).toString()
            } else if (cost == "10000" && number == "4") {
                totalCost = (10000*4).toString()
            } else if (cost == "10000" && number == "5") {
                totalCost= (10000*5).toString()
            } else if (cost == "10000" && number == "6") {
                totalCost= (10000*6).toString()
            } else if (cost== "10000" && number == "7") {
                totalCost= (10000*7).toString()
            } else if (cost== "10000" && number == "8") {
                totalCost = (10000*8).toString()
            } else if (cost == "10000" && number == "9") {
                totalCost =(10000*9).toString()
            } else if (cost== "10000" && number == "10") {
                totalCost= (10000*10).toString()
            }
            // 20 000
            // 20 000
            if (cost== "20000" && number == "1") {
                totalCost = (20000*1).toString()
            } else if (cost == "20000" && number == "2") {
                totalCost = (20000*2).toString()
            } else if (cost == "20000" && number == "3") {
                totalCost=  (20000*3).toString()
            } else if (cost == "20000" && number== "4") {
                totalCost =  (20000*4).toString()
            } else if (cost == "20000" && number == "5") {
                totalCost =  (20000*5).toString()
            } else if (cost  == "20000" && number == "6") {
                totalCost=  (20000*6).toString()
            } else if (cost  == "20000" && number == "7") {
                totalCost =  (20000*7).toString()
            } else if (cost  == "20000" && number== "8") {
                totalCost =  (20000*8).toString()
            } else if (cost == "20000" && number == "9") {
                totalCost=  (20000*9).toString()
            } else if (cost  == "20000" && number == "10") {
                totalCost=  (20000*10).toString()
            }
            // 30 000
            // 30 000
            if (cost == "30000" && number == "1") {
                totalCost = (30000*1).toString()
            } else if (cost  == "30000" && number == "2") {
                totalCost= (30000*2).toString()
            } else if (cost  == "30000" && number == "3") {
                totalCost= (30000*3).toString()
            } else if (cost  == "30000" && number == "4") {
                totalCost = (30000*4).toString()
            } else if (cost  == "30000" && number == "5") {
                totalCost = (30000*5).toString()
            } else if (cost  == "30000" && number == "6") {
                totalCost = (30000*6).toString()
            } else if (cost == "30000" && number== "7") {
                totalCost=(30000*7).toString()
            } else if (cost == "30000" && number == "8") {
                totalCost= (30000*8).toString()
            } else if (cost == "30000" && number == "9") {
                totalCost =(30000*9).toString()
            } else if (cost == "30000" && number == "10") {
                totalCost = (30000*10).toString()
            }

            if (name.isNotEmpty() && number.isNotEmpty() && street.isNotEmpty() && homeNumber.isNotEmpty() && phoneNumber.isNotEmpty() && cost.isNotEmpty() && totalCost.isNotEmpty()) {
                // Yangi mijoz buyurtmasini bazaga qo'shish
                val customerData = CustomerDataClass(
                    0, name, cost, number, street,

                    phoneNumber, homeNumber, totalCost
                )
                database.insertCustomerFoodOrder(customerData)


                Toast.makeText(this, "Buyurtma qo'shildi", Toast.LENGTH_SHORT).show()


                finish()
            } else {

                Toast.makeText(this, "Ma'lumotlar bo'sh! Iltimos to'ldiring", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}

