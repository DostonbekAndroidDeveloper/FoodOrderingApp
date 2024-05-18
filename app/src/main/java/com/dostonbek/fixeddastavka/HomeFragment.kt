package com.dostonbek.fixeddastavka

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dostonbek.fixeddastavka.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private  val SMS_PERMISSION_REQUEST_CODE = 123
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.adminBtn.setOnClickListener {
            findNavController().navigate(R.id.adminMainFragment)
        }
        binding.customerBtn.setOnClickListener {
            findNavController().navigate(R.id.customerMainFragment)
        }

        return binding.root
    }


}