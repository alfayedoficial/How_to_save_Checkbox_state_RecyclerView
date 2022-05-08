package com.fourDev.checkboxRecyclerView

data class OrderResponseItem(
    var orderId : String ? = null,
    var orderDate : String ? = null,
    var orderPrice : Double ? = null,
    var orderItem : Int ? = null,
    var orderStatus : String ? = null,
    var isChecked : Boolean  = false,
)
