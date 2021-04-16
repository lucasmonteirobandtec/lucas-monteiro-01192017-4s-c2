package com.example.baseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun listDogs(view: View) {
        startActivity(Intent(this, ListActivity::class.java))
    }

    fun saveDogs(view: View) {
        startActivity(Intent(this, SaveActivity::class.java))
    }
}

//    fun getDog(id: Int) {
//        val conn = ApiConnect.criar()
//        conn.get(id).enqueue(object: Callback<Cachorro>{
//
//            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
//                // TODO: 15/04/2021
//            }
//
//            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
//                val data = response.body()
//                if (data != null) {
//                    startSuccessActivity(data)
//                } else {
//                    startErrorActivity()
//                }
//            }
//
//        })
//    }
