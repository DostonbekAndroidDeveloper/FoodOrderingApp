package com.dostonbek.fixeddastavka.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dostonbek.fixeddastavka.database.DatabaseHelper
import com.dostonbek.fixeddastavka.databinding.FragmentCustomerMainBinding


class CustomerMainFragment : Fragment() {
    private lateinit var binding: FragmentCustomerMainBinding
    private  lateinit var  database: DatabaseHelper
    private lateinit var adapter: CustomerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCustomerMainBinding.inflate(layoutInflater)
        database= DatabaseHelper(requireContext())
adapter=CustomerAdapter(database.getAllAdminFood())

        binding.customerRecyclerview.adapter=adapter
        return binding.root

    }

    override fun onResume() {
        super.onResume()
adapter.refreshData(database.getAllAdminFood())
    }

}