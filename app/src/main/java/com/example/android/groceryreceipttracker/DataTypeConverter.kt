package com.example.android.groceryreceipttracker

import android.arch.persistence.room.TypeConverter
import java.util.*

class DataTypeConverter {

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}