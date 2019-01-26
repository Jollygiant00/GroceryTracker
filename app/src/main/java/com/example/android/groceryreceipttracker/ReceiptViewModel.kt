package com.example.android.groceryreceipttracker

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class ReceiptViewModel(application: Application): AndroidViewModel(application) {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val receiptDatabaseRepository: ReceiptDatabaseRepository
    val allReceipts: LiveData<List<Receipt>>

    init {
        val receiptDao = ReceiptRoomDatabase.getDatabase(application).receiptDao()
        receiptDatabaseRepository = ReceiptDatabaseRepository(receiptDao)
        allReceipts = receiptDatabaseRepository.allReceipts
    }

    fun insert(receipt: Receipt) = scope.launch(Dispatchers.IO) {
        receiptDatabaseRepository.insert(receipt)
    }

    fun getReceiptByMonth(month: String): LiveData<List<Receipt>>? {
        return receiptDatabaseRepository.getByMonth(month)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}