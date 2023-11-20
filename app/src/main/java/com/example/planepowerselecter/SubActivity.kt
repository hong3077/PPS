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

        Add_Info()
    }

    fun Add_Info(){

        //여기서 제공되는 (2차원)배열은 정렬이 끝난 배열이라 가정함
        var arrayString = arrayOf(
            arrayOf("하나","둘"),
            arrayOf("셋","넷"),
            arrayOf("다섯","여섯"),
            arrayOf("일곱","여덟"),)

        var infoList1 = Array<String>(arrayString.count(),{""})
        for(i: Int in 0..3){
            for(j: Int in 0..1){
                infoList1[i] = infoList1[i].plus(arrayString[i][j])
                infoList1[i] = infoList1[i].plus(" ")
            }
        }

        var infoList0:String = ""
        for(i: Int in 0..3){
            infoList0 = infoList0.plus(infoList1[i])
            infoList0 = infoList0.plus("\n")
        }

        var MotorList = findViewById<TextView>(R.id.MotorList)
        MotorList.text = infoList0;


    }
}