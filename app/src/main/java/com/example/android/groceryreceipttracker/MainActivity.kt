package com.example.android.groceryreceipttracker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonAddReceipt: Button = findViewById(R.id.bt_add_receipt)
        val buttonViewReceipt: Button = findViewById(R.id.bt_view_receipts)
        //val textViewMonthTotal: TextView = findViewById(R.id.tv_header_amount_spent)
        //val textViewSpent: TextView = findViewById(R.id.tv_data_amount_spent)

        buttonAddReceipt.setOnClickListener {
            val addReceiptIntent = Intent(this, AddReceipt::class.java)

            startActivity(addReceiptIntent)
        }

        buttonViewReceipt.setOnClickListener {
            val viewReceiptIntent = Intent(this, ReceiptList::class.java)

            startActivity(viewReceiptIntent)
        }

        //TODO get receipt totals for the month and pass to month total
        //TODO subtract month total from budget amount
    }

    //TODO long term create graph (amount spent/remaining?) or (user selected data?)

}
