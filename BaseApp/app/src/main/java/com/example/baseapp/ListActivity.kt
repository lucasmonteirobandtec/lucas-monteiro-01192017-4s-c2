package com.example.baseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    lateinit var layoutListDog: LinearLayout

    lateinit var tvValueIndicated: TextView
    lateinit var tvValueNotIndicated:TextView
    lateinit var tvValueTotal:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        layoutListDog = findViewById(R.id.layout_list_dogs)
        tvValueIndicated = findViewById(R.id.tv_value_indicated)
        tvValueNotIndicated = findViewById(R.id.tv_value_not_indicated)
        tvValueTotal = findViewById(R.id.tv_value_total)

        loadData()
    }

    fun loadData() {

        val conn = ApiConnect.criar()

        var total:Int = 0
        var indicated:Int = 0
        var notIndicated:Int = 0

        conn.list().enqueue(object : Callback<List<Dog>> {

            override fun onResponse(call: Call<List<Dog>>, response: Response<List<Dog>>) {

                response.body()?.forEach {

                    val tvCachorro = TextView(baseContext)
                    tvCachorro.text = getString(R.string.text_breed)+ it.raca +getString(R.string.avg_price) + it.precoMedio.toString() + getString(R.string.text_indicated) + it.indicadoCriancas + "\n\n"

                    if (it.indicadoCriancas) indicated++ else notIndicated++

                    layoutListDog.addView(tvCachorro)
                }

                total = indicated + notIndicated

                tvValueIndicated.text = indicated.toString()
                tvValueNotIndicated.text = notIndicated.toString()
                tvValueTotal.text =  total.toString()
            }

            override fun onFailure(call: Call<List<Dog>>, t: Throwable) {
                Toast.makeText(baseContext, getString(R.string.res_error) , Toast.LENGTH_SHORT).show()
            }

        })

    }
}