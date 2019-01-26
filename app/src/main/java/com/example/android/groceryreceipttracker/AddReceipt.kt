package com.example.android.groceryreceipttracker


import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddReceipt : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var editReceiptAmountView: EditText
    private lateinit var spinnerTextStoreView: Spinner
    private lateinit var fullDateString: String
    private lateinit var monthNameString: String
    private lateinit var receiptViewModel: ReceiptViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_receipt)
        val currentDateString = LocalDateTime.now()
        val formatFullDateString = DateTimeFormatter.ofPattern("LL/dd/yyyy")
        val formatMonthString = DateTimeFormatter.ofPattern("MMMM")

        receiptViewModel = ViewModelProviders.of(this).get(ReceiptViewModel::class.java)

        editReceiptAmountView = findViewById(R.id.et_receipt_amount)
        spinnerTextStoreView = findViewById(R.id.sp_store_selector)
        spinnerTextStoreView.onItemSelectedListener = this

        ArrayAdapter.createFromResource(
            this,
            R.array.store_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTextStoreView.adapter = adapter
        }

        fullDateString = currentDateString.format(formatFullDateString)
        monthNameString = currentDateString.format(formatMonthString)

        val submitReceiptButton = findViewById<Button>(R.id.bt_submit_receipt)
        submitReceiptButton.setOnClickListener {
            if(TextUtils.isEmpty(editReceiptAmountView.text)) {
                Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show()
            } else {
                val textAmount = editReceiptAmountView.text.toString()
                val amount = textAmount.toDouble()

                val saveSpinnerString = spinnerTextStoreView.selectedItem.toString()

                val receipt = Receipt(saveSpinnerString, amount, fullDateString, monthNameString)
                receiptViewModel.insert(receipt)

                Toast.makeText(
                    applicationContext,
                    R.string.receipt_saved,
                    Toast.LENGTH_LONG).show()
            }
            finish()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        parent?.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}
