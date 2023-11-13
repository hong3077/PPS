package com.example.planepowerselecter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var requiredPower: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButtonTriggered()
    }

    fun ButtonTriggered(){
        val button = findViewById<Button>(R.id.calculateButton)
        button.setOnClickListener{

            val intent = Intent(this,SubActivity::class.java)
            var input: String = findViewById<TextView>(R.id.aircraft_weight_value).text.toString()
            var weight:Int = input.toInt()
            intent.putExtra("aircraftWeight",weight)

            
            startActivity(intent)

        }
    }
}