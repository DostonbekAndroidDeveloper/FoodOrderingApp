package com.dostonbek.fixeddastavka.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dostonbek.fixeddastavka.R
import com.dostonbek.fixeddastavka.database.DatabaseHelper
import com.dostonbek.fixeddastavka.databinding.FragmentAdminAddFoodBinding
import com.dostonbek.fixeddastavka.utils.AdminData


class AdminAddFoodFragment : Fragment() {
    private lateinit var binding: FragmentAdminAddFoodBinding
    private lateinit var database: DatabaseHelper


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAdminAddFoodBinding.inflate(layoutInflater)
        database= DatabaseHelper(requireContext())
        val foodType=resources.getStringArray(R.array.foodType)
        val arrayAdapter=ArrayAdapter(requireContext(),R.layout.dropdown_menu,foodType)
        binding.addFoodName.setAdapter(arrayAdapter)
        val foodCost=resources.getStringArray(R.array.cost_array)
        val costAdapter=ArrayAdapter(requireContext(),R.layout.dropdown_menu,foodCost)
        binding.addFoodCost.setAdapter(costAdapter)
        binding.save.setOnClickListener {
            val foodName=binding.addFoodName.text.toString()
            val foodCost=binding.addFoodCost.text.toString()
            if (foodName.isNotEmpty() && foodCost.isNotEmpty()){
                val admin= AdminData(0, foodName,foodCost)
                database.insertFood(admin)
                findNavController().popBackStack()

                Toast.makeText(requireContext(),"Ovqat qo'shildi",Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(requireContext(),"Malumotlar bo'sh",Toast.LENGTH_SHORT).show()
            }
            onResume()



        }

        return binding.root
    }





}