package com.example.apiplusrecycler

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<DataModelItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)

        supportActionBar?.hide()
        list = ArrayList()
        val layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(list, this)
        recyclerView.layoutManager = layoutManager


        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api: ApiInterface = retrofit.create(ApiInterface::class.java)

        val call: Call<DataModel> = api.getData()


        call.enqueue(object : Callback<DataModel?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<DataModel?>, response: Response<DataModel?>) {

                if (response.isSuccessful) {

                    list.clear()
                    for (myData in response.body()!!) {
                        list.add(myData)
                    }
                    adapter.notifyDataSetChanged()
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<DataModel?>, t: Throwable) {
                Log.d("Error", "${t.message}")
            }
        })
    }
}