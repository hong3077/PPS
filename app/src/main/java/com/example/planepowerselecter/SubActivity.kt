package com.example.planepowerselecter

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Math.round


class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.required_motor)


        var weight = intent.getIntExtra("aircraftWeight",0)
        
        
        var requiredPower = findViewById<TextView>(R.id.required_motor_power)
        requiredPower.text = round(((weight * 80) / 453.6)).toString() + " (watt)"

    }
}