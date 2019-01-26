package com.example.android.groceryreceipttracker

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class ReceiptDatabaseRepository(private val receiptDao: ReceiptDao) {
    val allReceipts: LiveData<List<Receipt>> = receiptDao.getAllReceipts()

    @WorkerThread
    fun insert(receipt: Receipt) {
        receiptDao.insert(receipt)
    }

    @WorkerThread
    fun getByMonth(month: String): LiveData<List<Receipt>> {
        return receiptDao.getReceiptByMonth(month)
    }
}