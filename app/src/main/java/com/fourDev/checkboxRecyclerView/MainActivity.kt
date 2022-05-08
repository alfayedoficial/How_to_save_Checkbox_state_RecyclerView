package com.fourDev.checkboxRecyclerView

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fourDev.checkboxRecyclerView.UtilRecycler.exHide
import com.fourDev.checkboxRecyclerView.UtilRecycler.exShow
import com.fourDev.checkboxRecyclerView.UtilRecycler.orderResponseItems
import com.fourDev.checkboxRecyclerView.UtilRecycler.orderStatus
import com.fourDev.checkboxRecyclerView.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var dataBinder : ActivityMainBinding? = null
    private val adapter by lazy { CheckboxAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinder = DataBindingUtil.setContentView(  this , R.layout.activity_main)

        dataBinder?.apply {

            activity =this@MainActivity

            adapter.saveData(orderResponseItems)
            rv.adapter = adapter

            spinnerStatus.adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, orderStatus )
            spinnerStatus.setSelection(0)
            spinnerStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                    orderStatus[position].apply {
                        filterList(this)
                    }
                }

                override fun onNothingSelected(adapter: AdapterView<*>?) { }

            }

            setSupportActionBar(toolbar)

        }



    }

    fun calculateIsChecked() {
        val message : String = if (adapter.returnOrderModels().map { it.isChecked }.isEmpty()) {
            "No items selected"
        } else {
            "count is == ${ adapter.returnOrderModels().map { it.isChecked }.size}"
        }
        Toast.makeText(this,message , Toast.LENGTH_SHORT).show()
    }

    private fun filterList(status: String) {
        adapter.apply {
            if (status == UtilRecycler.OrderStatus.ALL.value) {
                clearSelectedItems()
                saveData(orderResponseItems)
            } else {
                clearSelectedItems()
                saveData(orderResponseItems.filter { it.orderStatus == status })
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_filter, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_filter) {
            dataBinder?.apply {
                if (lyFilter.visibility == View.GONE) {
                    lyFilter.exShow()
                }else{
                    lyFilter.exHide()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        dataBinder = null
    }
}