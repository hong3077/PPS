package com.example.planepowerselecter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    //lateinit var requiredPower: TextView

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
            var weight: Int

            //입력이 없을 경우
            if (input == ""||input == "0") {
                Toast.makeText(getApplicationContext(), "모터 중량이 입력되지 않았습니다", Toast.LENGTH_LONG).show()
            }

            //입력이 있을 경우
            else {
                weight = input.toInt()
                intent.putExtra("aircraftWeight", weight)
                startActivity(intent)
            }
        }
    }
}