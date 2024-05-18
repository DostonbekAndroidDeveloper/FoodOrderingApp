package com.dostonbek.fixeddastavka.database

import com.dostonbek.fixeddastavka.utils.AdminData
import com.dostonbek.fixeddastavka.utils.CustomerDataClass

interface DatabaseService {


    fun insertFood(adminData: AdminData)
    fun getAllAdminFood(): ArrayList<AdminData>
    fun editAdminFood(adminData: AdminData):Int
    fun getAdminFoodId(adminFoodId: Int): AdminData
    fun deleteAdminFood(adminFoodId: AdminData)

    fun insertCustomerFoodOrder(customerDataClass: CustomerDataClass)
    fun getAllCustomerFoodOrder(): ArrayList<CustomerDataClass>
    fun editCustomerFoodOrder(customerDataClass: CustomerDataClass):Int
    fun getCustomerFoodOrderId(customerFoodId: Int): CustomerDataClass
    fun deleteCustomerFoodOrder(customerDataClass: CustomerDataClass)
}