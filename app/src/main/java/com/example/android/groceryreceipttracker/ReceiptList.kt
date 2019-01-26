package com.example.android.groceryreceipttracker

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class ReceiptList : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var receiptViewModel: ReceiptViewModel
    private lateinit var spMonthSelector: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = ReceiptListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        receiptViewModel = ViewModelProviders.of(this).get(ReceiptViewModel::class.java)

        spMonthSelector = findViewById(R.id.month_spinner)
        spMonthSelector.onItemSelectedListener = this

        receiptViewModel = ViewModelProviders.of(this).get(ReceiptViewModel::class.java)

        //set spinner list from resource array
        ArrayAdapter.createFromResource(
            this,
            R.array.month_name_array,
            android.R.layout.simple_spinner_item
        ).also { adapterList ->
            adapterList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spMonthSelector.adapter = adapterList
        }

        //save value of spinner during onCreate()
        val savedSpinnerString = spMonthSelector.selectedItem.toString()

        //set observer for month query method
        //call view model's getReceiptByMonth method and setReceipt method
        receiptViewModel.getReceiptByMonth(savedSpinnerString)?.observe(this, Observer { receipts ->
            receipts?.let { adapter.setReceipts(it)}
        })
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    //get new spinner value base on user selection
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        val newSavedSpinner = parent?.getItemAtPosition(pos).toString()

        //TODO get new data from view model on user selection

        receiptViewModel.getReceiptByMonth(newSavedSpinner)
    }
}
