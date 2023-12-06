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

/**
 * It calculates and displays the required motor power based on the aircraft weight.
 * It fetches data from a remote server using Retrofit, sorts and displays the motor information.
 */
class SubActivity : AppCompatActivity() {

    var calculator = Calculator()
    var sort = Sorter()
    var arrayString = Array(100,{Array<String>(5,{"0"})})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.required_motor)

        var weight:Int = intent.getIntExtra("aircraftWeight",0)
        var requiredPowerText = findViewById<TextView>(R.id.required_motor_power)

        requiredPowerText.text = calculator.weightToPower(weight).toString() + " (watt)"
        fetchData(arrayString,weight)

        powerSortButtonTriggered(arrayString)
        costSortButtonTriggered(arrayString)
    }
    /**
     * Sets up the click listener for the sort button.
     */
    fun powerSortButtonTriggered(arrayString: Array<Array<String>>){
        val sortingButton = findViewById<Button>(R.id.power_sort_button)
        var isFirstTime = true
        sortingButton.setOnClickListener(){
            if (isFirstTime){
                //오름차순
                sort.bottomToTopPOWER(arrayString)
                Add_Info(arrayString)
                isFirstTime = false
            }
            else{
                //내림차순
                sort.topToBottomPOWER(arrayString)
                Add_Info(arrayString)
                isFirstTime = true
            }
        }
    }

    fun costSortButtonTriggered(arrayString: Array<Array<String>>){
        val sortingButton = findViewById<Button>(R.id.cost_sort_button)
        var isFirstTime = true
        sortingButton.setOnClickListener(){
            if (isFirstTime){
                //오름차순
                sort.bottomToTopCOST(arrayString)
                Add_Info(arrayString)
                isFirstTime = false
            }
            else{
                //내림차순
                sort.topToBottomCOST(arrayString)
                Add_Info(arrayString)
                isFirstTime = true
            }
        }
    }
    /**
     * Adds motor information to the TextView for display.
     */
    fun Add_Info(arrayString: Array<Array<String>>){

        //리스트 입력 파트
        var infoList1 = Array<String>(arrayString.size,{""})

        for(i in 0..(arrayString.count() - 1)){
            for(j: Int in 0..4){
                if (arrayString[i][0] != "0") {
                    infoList1[i] = infoList1[i].plus(arrayString[i][j])
                    infoList1[i] = infoList1[i].plus(" | ")
                }
            }
        }

        var infoList0:String = ""
        for(i: Int in 0..(arrayString.size - 1)){
            if(arrayString[i][0] != "0") {
                infoList0 = infoList0.plus(infoList1[i])
                infoList0 = infoList0.plus("\n\n")
            }
        }

        var MotorList = findViewById<TextView>(R.id.MotorList)
        MotorList.text = infoList0

    }

    /**
     * Fetches data from the server using Retrofit.
     */
    private fun fetchData(arrayString:Array<Array<String>>,weight:Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://1b13-203-255-63-211.ngrok-free.app")
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

    /**
     * Adds motor information from the fetched posts to the arrayString.
     */
    private fun displayPosts(posts: List<Post>, arrayString: Array<Array<String>>,weight:Int) {
        var count:Int = 0

        for (post in posts) {
            if (post.power >= weight) {
                arrayString[count][0] = post.product_name
                //println(arrayString[count][0])
                arrayString[count][1] = post.voltage
                arrayString[count][2] = post.power.toString()
                arrayString[count][3] = post.purpose
                arrayString[count][4] = post.cost.toString()
                count = count + 1
            }
        }
    }

}