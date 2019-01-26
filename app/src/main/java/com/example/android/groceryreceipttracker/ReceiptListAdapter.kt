package com.example.android.groceryreceipttracker

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView

class ReceiptListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<ReceiptListAdapter.ReceiptViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var receipts = emptyList<Receipt>() // Cached copy of words

    inner class ReceiptViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val receiptStoreView: TextView = itemView.findViewById(R.id.tv_store_name)
        val receiptAmountView: TextView = itemView.findViewById(R.id.tv_amount_total)
        val receiptDateView: TextView = itemView.findViewById(R.id.tv_receipt_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val itemView = inflater.inflate(R.layout.receipt_recycler_view_item, parent, false)
        return ReceiptViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val current = receipts[position]
        holder.receiptStoreView.text = current.store
        holder.receiptAmountView.text = current.amount.toString()
        holder.receiptDateView.text = current.date
    }

    internal fun setReceipts(receipts: List<Receipt>) {
        this.receipts = receipts
        notifyDataSetChanged()
    }

    override fun getItemCount() = receipts.size
}