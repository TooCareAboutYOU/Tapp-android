package com.zs.tapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.view.Gravity

import android.widget.TextView

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatSpinner


class MainActivity : AppCompatActivity() {

    private var province = mutableListOf("-Select Size -", "江西", "湖南")
    private var provinceindex = 0
    private val city = mutableListOf(
        mutableListOf("-Select Color -"),
        mutableListOf("南昌", "赣州"),
        mutableListOf("长沙", "湘潭")
    )
    private val counstryside = mutableListOf(
        mutableListOf(mutableListOf("-Select Style -")),
        mutableListOf(mutableListOf("青山湖区", "南昌县"), mutableListOf("章贡区", "赣县")),
        mutableListOf(mutableListOf("长沙县", "沙县"), mutableListOf("湘潭县", "象限"))
    )

    private lateinit var spinner1: AppCompatSpinner
    private lateinit var spinner2: AppCompatSpinner
    private lateinit var spinner3: AppCompatSpinner
    private lateinit var adapter1: ArrayAdapter<String?>
    private lateinit var adapter2: ArrayAdapter<String?>
    private lateinit var adapter3: ArrayAdapter<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner1 = findViewById(R.id.spn)
        adapter1 = ArrayAdapter(this, R.layout.spinner_layout, province as List<String?>)
        spinner1.adapter = adapter1

        spinner2 = findViewById(R.id.city)
        adapter2 = ArrayAdapter(this, R.layout.spinner_layout, city[0] as List<String?>)
        spinner2.adapter = adapter2

        spinner3 = findViewById(R.id.counstryside)
        adapter3 = ArrayAdapter(this, R.layout.spinner_layout, counstryside[0][0] as List<String?>)
        spinner3.adapter = adapter3

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {

                provinceindex = position
                adapter2 = ArrayAdapter(
                    this@MainActivity, R.layout.spinner_layout, city[position] as List<String?>
                )
                spinner2.adapter = adapter2
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {

                adapter3 = ArrayAdapter(
                    this@MainActivity, R.layout.spinner_layout,
                    counstryside[provinceindex][position] as List<String?>
                )
                spinner3.adapter = adapter3
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //当时据为空的时候触发的
            }
        }

        findViewById<AppCompatButton>(R.id.acBtnAdd).setOnClickListener {
            province.add("新增条目-1")
            city.add(mutableListOf("城市-1","城市-2"))
            counstryside.add(mutableListOf(mutableListOf("区-1","区-2","区-3"),mutableListOf("区-4","区-5","区-6")))
            adapter1.notifyDataSetChanged()
        }
    }
}