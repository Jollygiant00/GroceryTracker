package com.example.android.groceryreceipttracker

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface ReceiptDao {

    @Query("SELECT * FROM receipt_list ORDER BY store ASC")
    fun getAllReceipts(): LiveData<List<Receipt>>

    @Query("SELECT * FROM receipt_list WHERE month LIKE :queryMonth ORDER BY date ASC")
    fun getReceiptByMonth(queryMonth: String): LiveData<List<Receipt>>

    @Insert
    fun insert(receipt: Receipt)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateReceipt(receipt: Receipt)

    @Delete
    fun delete(receipt: Receipt)
}