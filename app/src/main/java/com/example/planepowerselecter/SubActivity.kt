package com.example.planepowerselecter

import android.os.Bundle
import android.widget.Button
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


        //여기서 제공되는 (2차원)배열은 정렬이 끝난 배열이라 가정함
        var arrayString = arrayOf(
            arrayOf("[SUNNYSKY] X2826 1080KV Outrunner Brushless Motor","11.1V-14.8V (3S-4S)","1000","비행기","84000"),
            arrayOf("[SUNNYSKY] X2826 880KV Outrunner Brushless Motor","11.1V-14.8V (3S-4S)","900","비행기","84800"),
            arrayOf("[SUNNYSKY] X2826 740KV Outrunner Brushless Motor","11.1V-14.8V (3S-4S)","880","비행기","84800"))

        Add_Info(arrayString)
        sortButtonTriggered(arrayString)
    }


    fun sortButtonTriggered(arrayString: Array<Array<String>>){
        val button = findViewById<Button>(R.id.sort_button)
        button.setOnClickListener(){
            bubbleSort(arrayString)
            Add_Info(arrayString)
        }

    }

    fun Add_Info(arrayString: Array<Array<String>>){


        //리스트 입력 파트
        var infoList1 = Array<String>(arrayString.size,{""})
        for(i: Int in 0..(arrayString.size-1)){
            for(j: Int in 0..4){
                infoList1[i] = infoList1[i].plus(arrayString[i][j])
                infoList1[i] = infoList1[i].plus(" | ")
            }
        }

        var infoList0:String = ""
        for(i: Int in 0..(arrayString.size-1)){
            infoList0 = infoList0.plus(infoList1[i])
            infoList0 = infoList0.plus("\n\n")
        }

        var MotorList = findViewById<TextView>(R.id.MotorList)
        MotorList.text = infoList0;


    }

    fun bubbleSort(arr: Array<Array<String>>) {
        val n = arr.size

        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (arr[j][2].toInt() > arr[j + 1][2].toInt()) { //모터의 출력을 오름차순으로 정렬
                    // 스와핑
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }
        }
    }
}