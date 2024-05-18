package com.dostonbek.fixeddastavka.admin

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.dostonbek.fixeddastavka.database.DatabaseHelper
import com.dostonbek.fixeddastavka.databinding.ActivityAdminOrderInfoBinding

class AdminOrderInfoActivity : AppCompatActivity() {

    private val SMS_PERMISSION_REQUEST_CODE = 123
    private lateinit var binding: ActivityAdminOrderInfoBinding
    private lateinit var database: DatabaseHelper
    var text = ""

    private lateinit var itemColorViewModel: ItemColorViewModel
    private var orderId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminOrderInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseHelper(this)
        orderId = intent.getIntExtra("order_Id", -1)
        if (orderId == -1) {
            finish()
            return
        }
        val order = database.getCustomerFoodOrderId(orderId)
        binding.infoOrderItemFoodName.text = order.name
        binding.infoOrderItemFoodCost.text = order.cost
        binding.infoOrderItemFoodNumber.text = order.number
        binding.infoOrderItemFoodLocation.text = order.location
        binding.infoCustomerPhoneNumber.text = order.phoneNumber
        binding.infoCustomerHomeNumber.text = order.homeNumber
        binding.infoTotalPayment.text = order.totalCost
        itemColorViewModel = ViewModelProvider(this)[ItemColorViewModel::class.java]


        binding.infoAccept.setOnClickListener {
            binding.infoAccept.visibility = View.GONE
            binding.infoDecline.visibility = View.GONE
            binding.infoMainText.visibility = View.VISIBLE
            binding.infoMainText.text = "Qabul qilindi"
            text = "Qabul qilindi"
            val selectedColor = Color.RED

            // Update selected item color in ViewModel
            itemColorViewModel.setSelectedItemColor(selectedColor)
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.SEND_SMS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Permission already granted, send SMS
                sendSMS()
            } else {
                // Permission not granted, request it
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.SEND_SMS), SMS_PERMISSION_REQUEST_CODE
                )
            }

        }
        binding.infoDecline.setOnClickListener {
            binding.infoAccept.visibility = View.GONE
            binding.infoDecline.visibility = View.GONE
            binding.infoMainText.visibility = View.VISIBLE
            binding.infoMainText.text = "Qabul qilinmadi"
            text = "Qabul qilinmadi"
            val selectedColor = Color.YELLOW

            // Update selected item color in ViewModel
            itemColorViewModel.setSelectedItemColor(selectedColor)
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.SEND_SMS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Permission already granted, send SMS
                sendSMS()
            } else {
                // Permission not granted, request it
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.SEND_SMS), SMS_PERMISSION_REQUEST_CODE
                )
            }

        }
    }

    private fun sendSMS() {
        try {
            val smsManager = SmsManager.getDefault()
            val phoneNumber = binding.infoCustomerPhoneNumber
            smsManager.sendTextMessage(phoneNumber.text.toString(), null, " Sizning buyurtmangiz $text", null, null)
            Toast.makeText(this, "SMS sent successfully!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to send SMS: ${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()


        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, send SMS
                sendSMS()
            } else {
                // Permission denied, show toast or handle accordingly
                Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
