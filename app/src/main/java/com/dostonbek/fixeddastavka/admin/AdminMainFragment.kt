package com.dostonbek.fixeddastavka.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dostonbek.fixeddastavka.R
import com.dostonbek.fixeddastavka.database.DatabaseHelper
import com.dostonbek.fixeddastavka.databinding.FragmentAdminMainBinding


class AdminMainFragment : Fragment() {
    private lateinit var database: DatabaseHelper
    private lateinit var binding: FragmentAdminMainBinding
    private lateinit var adapter: AdminAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAdminMainBinding.inflate(layoutInflater)
        database = DatabaseHelper(requireContext())
        adapter = AdminAdapter(database.getAllAdminFood())
        binding.adminRecyclerview.adapter = adapter
        binding.addFoodType.setOnClickListener {
            findNavController().navigate(R.id.adminAddFoodFragment)
        }
       binding.newOrders.setOnClickListener {
            findNavController().navigate(R.id.adminOrderFragment2)
        }


        return binding.root

    }
    override fun onResume() {
        super.onResume()
        adapter.refreshData(database.getAllAdminFood())


    }


}