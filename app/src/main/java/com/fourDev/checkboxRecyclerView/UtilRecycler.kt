package com.fourDev.checkboxRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

object UtilRecycler {

    val orderResponseItems = listOf(
        OrderResponseItem(
            orderId = "LDOF5F",
            orderDate = "12/12/2020",
            orderStatus = OrderStatus.PENDING.value,
            orderPrice = 1000.0,
            orderItem = 56
        ),
        OrderResponseItem(
            orderId = "KLFKLKD",
            orderDate = "1/12/2020",
            orderStatus = OrderStatus.PENDING.value,
            orderPrice = 10.0,
            orderItem = 2
        ),
        OrderResponseItem(
            orderId = "3KIFLK",
            orderDate = "2/12/2020",
            orderStatus = OrderStatus.DELIVERED.value,
            orderPrice = 190.0,
            orderItem = 9
        ),
        OrderResponseItem(
            orderId = "FLKFK98",
            orderDate = "3/12/2020",
            orderStatus = OrderStatus.COMPLETED.value,
            orderPrice = 100.0,
            orderItem = 1
        ),
        OrderResponseItem(
            orderId = "KJFKF9",
            orderDate = "4/12/2020",
            orderStatus = OrderStatus.COMPLETED.value,
            orderPrice = 16.0,
            orderItem = 1
        ),
        OrderResponseItem(
            orderId = "LVBLV9",
            orderDate = "5/12/2020",
            orderStatus = OrderStatus.PENDING.value,
            orderPrice = 1630.0,
            orderItem = 30
        ),
        OrderResponseItem(
            orderId = "KMSDK43",
            orderDate = "6/12/2020",
            orderStatus = OrderStatus.DELIVERED.value,
            orderPrice = 103.0,
            orderItem = 5
        ),
        OrderResponseItem(
            orderId = "SDOPFOIF",
            orderDate = "712/2020",
            orderStatus = OrderStatus.DELIVERED.value,
            orderPrice = 180.0,
            orderItem = 13
        ),

        )

    val orderStatus = listOf(OrderStatus.ALL.value ,OrderStatus.PENDING.value, OrderStatus.DELIVERED.value, OrderStatus.COMPLETED.value)

    enum class OrderStatus(val value: String) {
        PENDING("Pending"),
        DELIVERED("Delivered"),
        COMPLETED("Completed"),
        ALL("ALL"),
    }

    /**
     * @author Ali Al Fayed
     * @param parent of ViewGroup Row
     * @param resId of layout Id
     * @return ViewDataBinding
     * you can case ViewDataBinding to child for get views in layout
     */
    fun getBindingRow(parent: ViewGroup, @LayoutRes resId: Int): ViewDataBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.context), resId, parent, false)
    }

    /**
     * @author Ali Al Fayed
     * @param View
     * @see  :set view a VISIBLE
     */
    fun View.exShow() {
        visibility = View.VISIBLE
    }
    /**
     * @author Ali Al Fayed
     * @param View
     * @see  :set view a GONE
     */
    fun View.exHide() {
        visibility = View.GONE
    }


}

@BindingAdapter("app:bindString")
fun TextView.setBindString(txtString: String?) {
    text = txtString?.trim()
}
