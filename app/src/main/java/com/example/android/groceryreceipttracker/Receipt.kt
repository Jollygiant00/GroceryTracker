package com.example.android.groceryreceipttracker

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "receipt_list")
data class Receipt(
                    var store: String,
                    var amount: Double,
                    var date: String,
                    var month: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}