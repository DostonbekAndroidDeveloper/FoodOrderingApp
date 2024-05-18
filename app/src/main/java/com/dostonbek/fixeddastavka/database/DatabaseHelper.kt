package com.dostonbek.fixeddastavka.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.dostonbek.fixeddastavka.utils.AdminData
import com.dostonbek.fixeddastavka.utils.CustomerDataClass


class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), DatabaseService {
    companion object {

        private const val DATABASE_NAME: String = "dastavka.db"
        private const val DATABASE_VERSION: Int = 2


        private const val TABLE_NAME: String = "allAdmin"
        private const val FOOD_ID: String = "id"
        private const val FOOD_NAME: String = "name"
        private const val FOOD_COST: String = "cost"


        private const val CUSTOMER_TABLE: String = "customerFood"
        private const val CUSTOMER_ID: String = "customerId"
        private const val CUSTOMER_FOOD_NAME: String = "name"
        private const val CUSTOMER_FOOD_COST: String = "cost"
        private const val CUSTOMER_FOOD_NUMBER: String = "number"
        private const val CUSTOMER_FOOD_LOCATION: String = "location"

        private const val CUSTOMER_FOOD_PHONE_NUMBER: String = "phoneNumber"
        private const val CUSTOMER_FOOD_HOME_NUMBER: String = "homeNumber"

        private const val CUSTOMER_FOOD_TOTAL_COST: String = "totalCost"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("DatabaseHelper", "Creating admin table...")

        val adminQuery: String =
            "create table $TABLE_NAME($FOOD_ID integer not null primary key autoincrement unique,$FOOD_NAME text not null,$FOOD_COST text not null)"
        Log.d("DatabaseHelper", "Creating customer table...")
        val customerQuery: String =
            "create table $CUSTOMER_TABLE($CUSTOMER_ID integer not null primary key autoincrement unique,$CUSTOMER_FOOD_NAME text not null,$CUSTOMER_FOOD_COST text not null,$CUSTOMER_FOOD_NUMBER text not null,$CUSTOMER_FOOD_LOCATION text not null,$CUSTOMER_FOOD_PHONE_NUMBER text not null,$CUSTOMER_FOOD_HOME_NUMBER text not null,$CUSTOMER_FOOD_TOTAL_COST text not null)"
        db?.execSQL(customerQuery)
        db?.execSQL(adminQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db?.execSQL("DROP TABLE IF EXISTS $CUSTOMER_TABLE")
        onCreate(db)
    }

    override fun insertFood(adminData: AdminData) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FOOD_NAME, adminData.name)
        contentValues.put(FOOD_COST, adminData.cost)
        database.insert(TABLE_NAME, null, contentValues)
        database.close()
    }

    override fun getAllAdminFood(): ArrayList<AdminData> {
        val adminList = ArrayList<AdminData>()
        val query: String = "select * from $TABLE_NAME"
        val database = this.readableDatabase
        val cursor: Cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val admin = AdminData(cursor.getInt(0), cursor.getString(1), cursor.getString(2))
                adminList.add(admin)
            } while (cursor.moveToNext())
        }
        return adminList
    }

    override fun editAdminFood(adminData: AdminData): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FOOD_ID, adminData.id)
        contentValues.put(FOOD_NAME, adminData.name)
        contentValues.put(FOOD_COST, adminData.cost)
        return database.update(
            TABLE_NAME, contentValues, "$FOOD_ID = ?", arrayOf("${adminData.id}")
        )


    }

    override fun getAdminFoodId(adminFoodId: Int): AdminData {
        val database = this.writableDatabase
        val cursor: Cursor = database.query(
            TABLE_NAME,
            arrayOf(FOOD_ID, FOOD_NAME, FOOD_COST),
            "$FOOD_ID=?",
            arrayOf("$adminFoodId"),
            null,
            null,
            null
        )
        cursor.moveToFirst()
        return AdminData(cursor.getInt(0), cursor.getString(1), cursor.getString(2))


    }

    override fun deleteAdminFood(adminFoodId: AdminData) {

        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$FOOD_ID=?", arrayOf("${adminFoodId.id}"))
        database.close()

    }

    override fun insertCustomerFoodOrder(customerDataClass: CustomerDataClass) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(CUSTOMER_FOOD_NAME, customerDataClass.name)
        contentValues.put(CUSTOMER_FOOD_COST, customerDataClass.cost)
        contentValues.put(CUSTOMER_FOOD_NUMBER, customerDataClass.number)
        contentValues.put(CUSTOMER_FOOD_LOCATION, customerDataClass.location)
        contentValues.put(CUSTOMER_FOOD_PHONE_NUMBER, customerDataClass.phoneNumber)

        contentValues.put(CUSTOMER_FOOD_HOME_NUMBER, customerDataClass.homeNumber)
        contentValues.put(CUSTOMER_FOOD_TOTAL_COST, customerDataClass.totalCost)
        database.insert(CUSTOMER_TABLE, null, contentValues)
        database.close()


    }

    @SuppressLint("Recycle")
    override fun getAllCustomerFoodOrder(): ArrayList<CustomerDataClass> {
        val customerList = ArrayList<CustomerDataClass>()
        val query: String = "select * from $CUSTOMER_TABLE"
        val database = this.readableDatabase
        val cursor: Cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val customer = CustomerDataClass(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
                )
                customerList.add(customer)

            } while (cursor.moveToNext())
        }
        return customerList


    }

    override fun editCustomerFoodOrder(customerDataClass: CustomerDataClass): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(CUSTOMER_ID, customerDataClass.id)
        contentValues.put(CUSTOMER_FOOD_NAME, customerDataClass.name)

        contentValues.put(CUSTOMER_FOOD_COST, customerDataClass.cost)
        contentValues.put(CUSTOMER_FOOD_NUMBER, customerDataClass.number)
        contentValues.put(CUSTOMER_FOOD_LOCATION, customerDataClass.location)

        contentValues.put(CUSTOMER_FOOD_NUMBER, customerDataClass.phoneNumber)
        contentValues.put(CUSTOMER_FOOD_HOME_NUMBER, customerDataClass.homeNumber)
        contentValues.put(CUSTOMER_FOOD_TOTAL_COST, customerDataClass.totalCost)
        return database.update(
            CUSTOMER_TABLE, contentValues, "$CUSTOMER_ID=?", arrayOf("${customerDataClass.id}")
        )

    }

    @SuppressLint("Recycle")
    override fun getCustomerFoodOrderId(customerFoodId: Int): CustomerDataClass {
        val database = this.writableDatabase
        val cursor: Cursor = database.query(
            CUSTOMER_TABLE,
            arrayOf("$CUSTOMER_ID, $CUSTOMER_FOOD_NAME,$CUSTOMER_FOOD_COST, $CUSTOMER_FOOD_NUMBER, $CUSTOMER_FOOD_LOCATION,$CUSTOMER_FOOD_PHONE_NUMBER , $CUSTOMER_FOOD_HOME_NUMBER,  $CUSTOMER_FOOD_TOTAL_COST"),
            "$CUSTOMER_ID=?",
            arrayOf("$customerFoodId"),
            null,
            null,
            null
        )
        cursor.moveToFirst()
        return CustomerDataClass(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getString(4),
            cursor.getString(5),
            cursor.getString(6),
            cursor.getString(7)
        )

    }

    override fun deleteCustomerFoodOrder(customerDataClass: CustomerDataClass) {
        val database = this.writableDatabase
        database.delete(CUSTOMER_TABLE, "$CUSTOMER_ID=?", arrayOf("${customerDataClass.id}"))
        database.close()
    }
}