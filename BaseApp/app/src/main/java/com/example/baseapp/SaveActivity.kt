package com.example.baseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SaveActivity : AppCompatActivity() {

    private var isValid: Boolean = false
    lateinit var edBreed: EditText
    lateinit var edPrice: EditText
    lateinit var sw_ask: Switch
    lateinit var imgDog: ImageView

    lateinit var dog: Dog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        edBreed = findViewById(R.id.ed_breed)
        edPrice = findViewById(R.id.ed_price)
        sw_ask = findViewById(R.id.sw_ask)
        imgDog = findViewById(R.id.iv_dog)
    }

    fun save(view: View) {
        validate()

        if (isValid) {
            dog = Dog(edBreed.text.toString(), edPrice.text.toString().toDouble(), sw_ask.isChecked)
            callSaveDog(dog)
        }else {
            Toast.makeText(this, getString(R.string.error_invalid), Toast.LENGTH_SHORT).show()
        }

    }

    fun validate() {

        isValid = true

        if (edBreed.text.isBlank()) {
            edBreed.error = getString(R.string.error_breed)
            isValid = false
        }

        if(edPrice.text.isBlank()) {
            edPrice.error = getString(R.string.error_price)
            isValid = false
        }

    }

    fun callSaveDog(dog: Dog) {
        val conn = ApiConnect.criar()

        conn.save(dog).enqueue(object: Callback<Dog> {
            override fun onFailure(call: Call<Dog>, t: Throwable) {
                Toast.makeText(this@SaveActivity, getString(R.string.res_error), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Dog>, response: Response<Dog>) {
                Toast.makeText(this@SaveActivity, getString(R.string.res_success), Toast.LENGTH_SHORT).show()
                imgDog.visibility = View.VISIBLE
            }
        })
    }
}