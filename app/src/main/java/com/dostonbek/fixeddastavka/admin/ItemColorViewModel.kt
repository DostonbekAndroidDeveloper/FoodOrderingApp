package com.dostonbek.fixeddastavka.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemColorViewModel:ViewModel() {
    // MutableLiveData to hold the selected item color
    private val selectedItemColor = MutableLiveData<Int>()

    // Function to set the selected item color
    fun setSelectedItemColor(color: Int) {
        selectedItemColor.value = color
    }

    // Function to get the selected item color as LiveData (immutable)
    fun getSelectedItemColor(): LiveData<Int> {
        return selectedItemColor
    }
}