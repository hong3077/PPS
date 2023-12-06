package com.example.planepowerselecter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * The main activity of the Plane Power Selector app.
 * Allows users to input the weight of an aircraft and navigate to a SubActivity for further calculations.
 */
class MainActivity : AppCompatActivity() {

    //lateinit var requiredPower: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTriggered()
    }

    /**
     * Sets up the click listener for the calculate button.
     */
    private fun buttonTriggered(){
        val button = findViewById<Button>(R.id.calculateButton)
        button.setOnClickListener{
            val intent = Intent(this,SubActivity::class.java)
            val input: String = findViewById<TextView>(R.id.aircraft_weight_value).text.toString()
            val weight: Int

            //입력이 없을 경우
            if (input == ""||input == "0") {
                Toast.makeText(applicationContext, "모터 중량이 입력되지 않았습니다", Toast.LENGTH_LONG).show()
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