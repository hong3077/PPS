package com.example.planepowerselecter

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SubActivity : AppCompatActivity() {

    var arrayString = Array(100,{Array<String>(5,{""})})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.required_motor)

        var weight:Int = intent.getIntExtra("aircraftWeight",0)
        var requiredPowerText = findViewById<TextView>(R.id.required_motor_power)
        weight = ((weight * 80) / 453.6).toInt()

        requiredPowerText.text = weight.toString() + " (watt)"
        fetchData(arrayString,weight)

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
        var infoList1 = Array<String>(arrayString.count(),{""})
        for(i: Int in 0..(arrayString.count()-1)){
            for(j: Int in 0..4){
                infoList1[i] = infoList1[i].plus(arrayString[i][j])
                infoList1[i] = infoList1[i].plus(" | ")
            }
        }

        var infoList0:String = ""
        for(i: Int in 0..(arrayString.count()-1)){
            infoList0 = infoList0.plus(infoList1[i])
            infoList0 = infoList0.plus("\n\n")
        }

        var MotorList = findViewById<TextView>(R.id.MotorList)
        MotorList.text = infoList0;
        println(infoList0)


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

    private fun fetchData(arrayString:Array<Array<String>>,weight:Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://4920-203-255-63-211.ngrok-free.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHOlderApi::class.java)

        val call: Call<List<Post>> = jsonPlaceHolderApi.getPosts()

        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val posts: List<Post> = response.body() ?: emptyList()
                displayPosts(posts,arrayString,weight)
                Add_Info(arrayString)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
            }
        })

    }

    private fun displayPosts(posts: List<Post>, arrayString: Array<Array<String>>,weight:Int) {
        var count:Int = 0

        for (post in posts) {
            if (post.power >= weight) { //45보다 출력이 높다면
                println("triggered!")
                arrayString[count][0] = post.product_name
                arrayString[count][1] = post.voltage
                arrayString[count][2] = post.power.toString()
                arrayString[count][3] = post.purpose
                arrayString[count][4] = post.cost.toString()
                count = count + 1
            }

//            val valuesArray = arrayOf(
//                post.product_name,
//                post.voltage,
//                post.power,
//                post.purpose,
//                post.cost
//            )

            // Join the array elements into a single string
            //val content = valuesArray.joinToString(separator = "\n") { "$it" }

            // Append the content to the textView
            //textView.append("$content\n\n")
        }
    }

}