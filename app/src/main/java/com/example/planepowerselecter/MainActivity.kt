package com.example.planepowerselecter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Math.round

class MainActivity : AppCompatActivity() {

    lateinit var requiredPower: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val aircraftWeight: TextView = findViewById(R.id.aircraft_weight_value)
        val calculation: Button = findViewById(R.id.calculate_button)


        calculation.setOnClickListener{
            setContentView(R.layout.required_motor)
            requiredPower = findViewById(R.id.required_motor_power)
            var weight0 = aircraftWeight.text.toString()
            var weight:Int = weight0.toInt()
            requiredPower.text = round(((weight * 80) / 453.6)).toString() + " (watt)"

        }
    }
}