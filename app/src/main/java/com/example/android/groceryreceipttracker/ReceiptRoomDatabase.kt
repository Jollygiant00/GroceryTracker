package com.example.android.groceryreceipttracker

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask

@Database(entities = [Receipt::class], version = 2, exportSchema = false)
abstract class ReceiptRoomDatabase: RoomDatabase() {

    abstract fun receiptDao(): ReceiptDao

    companion object {
        @Volatile
        private var INSTANCE: ReceiptRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): ReceiptRoomDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReceiptRoomDatabase::class.java,
                    "receipt_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(ReceiptDatabaseCallBack()
                    ).build()
                INSTANCE = instance
                return instance
            }
        }

        private class ReceiptDatabaseCallBack: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsync().execute()
            }
        }

        private class PopulateDbAsync: AsyncTask<Void, Void, Void>() {
            private lateinit var receiptDao: ReceiptDao

            override fun doInBackground(vararg voids: Void): Void? {
                var receipt = Receipt("Target", 500.00, "1/2/19", "January")
                receiptDao.insert(receipt)
                receipt = Receipt("Costco", 137.56, "1/8/19", "January")
                receiptDao.insert(receipt)
                receipt = Receipt("Target", 76.32, "1/15/19", "January")
                receiptDao.insert(receipt)
                receipt = Receipt("Costco", 137.25, "1/20/19", "January")
                receiptDao.insert(receipt)
                receipt = Receipt("Target", 76.32, "2/5/19", "February")
                receiptDao.insert(receipt)
                receipt = Receipt("Costco", 137.85, "9/28/19", "September")
                receiptDao.insert(receipt)
                receipt = Receipt("Target", 76.32, "9/5/19", "September")
                receiptDao.insert(receipt)

                return null
            }
        }
    }
}
