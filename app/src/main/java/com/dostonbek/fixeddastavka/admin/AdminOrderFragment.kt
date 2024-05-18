package com.dostonbek.fixeddastavka.admin

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dostonbek.fixeddastavka.customer.CustomerOrderAdapter
import com.dostonbek.fixeddastavka.database.DatabaseHelper
import com.dostonbek.fixeddastavka.databinding.FragmentAdminOrderBinding

class AdminOrderFragment : Fragment() {
    private lateinit var binding: FragmentAdminOrderBinding
    private lateinit var database: DatabaseHelper
    private lateinit var adapter: CustomerOrderAdapter

    private lateinit var itemColorViewModel: ItemColorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = DatabaseHelper(requireContext())

        // Barcha mijoz buyurtmalari ro'yxatini olish va adapter yaratish
        adapter = CustomerOrderAdapter(database.getAllCustomerFoodOrder())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragmentning interfeysini yaratish
        binding = FragmentAdminOrderBinding.inflate(inflater, container, false)

        // RecyclerView uchun adapterni bog'lash
        binding.orderRecyclerview.adapter = adapter
        itemColorViewModel = ViewModelProvider(requireActivity())[ItemColorViewModel::class.java]
        itemColorViewModel.getSelectedItemColor().observe(viewLifecycleOwner) { color ->
            // Update item background color
            adapter.updateSelectedItemColor(color)
        }

        return binding.root
    }


    override fun onResume() {
        super.onResume()

        // Adapterdagi ma'lumotlarni yangilash va RecyclerViewni qayta yuklash
        adapter.refreshData(database.getAllCustomerFoodOrder())
    }
}
