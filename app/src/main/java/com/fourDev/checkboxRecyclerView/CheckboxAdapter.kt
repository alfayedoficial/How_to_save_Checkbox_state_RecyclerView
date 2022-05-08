package com.fourDev.checkboxRecyclerView

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fourDev.checkboxRecyclerView.UtilRecycler.exHide
import com.fourDev.checkboxRecyclerView.UtilRecycler.exShow
import com.fourDev.checkboxRecyclerView.UtilRecycler.getBindingRow
import com.fourDev.checkboxRecyclerView.databinding.ItemRvOrdersBinding

class CheckboxAdapter :RecyclerView.Adapter<CheckboxAdapter.OrdersAdapterViewHolder>() {

    inner class OrdersAdapterViewHolder(var dataBinder : ItemRvOrdersBinding) : RecyclerView.ViewHolder(dataBinder.root) {

        fun bind(result: OrderResponseItem) {
            dataBinder.apply {
                setVariable(BR.model, result)
                executePendingBindings()
            }
        }
    }

    private var orderModels = mutableListOf<OrderResponseItem>()
    private var isCheckedOrderModels = mutableListOf<OrderResponseItem>()

    private val diffUtil = object : DiffUtil.ItemCallback<OrderResponseItem>() {
        override fun areItemsTheSame(oldItem: OrderResponseItem, newItem: OrderResponseItem): Boolean {
            return oldItem.orderId == newItem.orderId
        }

        override fun areContentsTheSame(oldItem: OrderResponseItem, newItem: OrderResponseItem): Boolean {
            return oldItem == newItem
        }

    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    fun returnOrderModels(): List<OrderResponseItem> {
        return isCheckedOrderModels
    }

    fun saveData(orderResponse: List<OrderResponseItem>) {
        asyncListDiffer.submitList(orderResponse)
    }

    fun clearSelectedItems() {
        if (orderModels.isNotEmpty()) {
            orderModels.clear()
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersAdapterViewHolder {
        return OrdersAdapterViewHolder(getBindingRow(parent, R.layout.item_rv_orders) as ItemRvOrdersBinding)
    }

    override fun onBindViewHolder(holder: OrdersAdapterViewHolder, position: Int) {
        val model = asyncListDiffer.currentList[position]

        holder.apply {
            bind(model)

            dataBinder.apply {
                checkboxOrder.setOnCheckedChangeListener(null)
                orderCard.setOnLongClickListener(null)

                if (orderModels.contains(model)) {
                    checkboxOrder.exShow()
                    checkboxOrder.isChecked = true
                } else {
                    checkboxOrder.exHide()
                    checkboxOrder.isChecked = false
                }

                orderCard.setOnLongClickListener(View.OnLongClickListener {
                    checkboxOrder.exShow()
                    checkboxOrder.isChecked = true
                    return@OnLongClickListener true
                })

                checkboxOrder.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        if (!orderModels.contains(model))
                            orderModels.add(model)
                        isCheckedOrderModels.add(model)
                    } else {
                        checkboxOrder.exHide()
                        orderModels.remove(model)
                        isCheckedOrderModels.remove(model)
                    }
                }
            }
        }

    }

    override fun getItemCount() = asyncListDiffer.currentList.size
}