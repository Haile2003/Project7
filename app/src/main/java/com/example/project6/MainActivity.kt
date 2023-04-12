package com.example.project6


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var pokeImageURL = ""
    private lateinit var pokeList22: MutableList<String>
    private lateinit var rvDisplay: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokeList22 = mutableListOf()
        getPokeImageURL()

        val pokeList = mutableListOf<String>()

        var todoList = mutableListOf(Todo("Follow my cat", "50"), Todo("Follow my cat", "50"))

        val adapter = TodoAdapter(todoList)
        rvDisplay.adapter = adapter
        rvDisplay.layoutManager = LinearLayoutManager(this)




    }


    private fun getPokeImageURL() {
        val client = AsyncHttpClient()
        client["https://pokeapi.co/api/v2/pokemon/", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Poke", "response successful$json")

                val pokeImageArray = json.jsonObject.getJSONArray("results")
                Log.d("PokeResults", "result success")

                val pokeList = mutableListOf<String>()
                for (i in 0 until pokeImageArray.length()) {
                    val pokeObj = pokeImageArray.getJSONObject(i)
                    val pokeUrl = pokeObj.getString("url")
                    pokeList.add(pokeUrl)
                }
                Log.d("PokeResults2", "result success")

                val pokeList1 = mutableListOf<String>()
                for (k in 0 until pokeList.size) {
                    client["https://pokeapi.co/api/v2/pokemon/$k", object : JsonHttpResponseHandler() {
                        override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                            pokeImageURL = json.jsonObject.getJSONObject("sprites").getString("front_default")

                            pokeList22.add(pokeImageURL)


                        }

                        override fun onFailure(
                            statusCode: Int,
                            headers: Headers?,
                            errorResponse: String,
                            throwable: Throwable?
                        ) {
                            Log.d("Dog ErrorZ", errorResponse)
                        }
                    }]
                }

                Log.d("PokeResults3", "result success")


                val pokeImageView = findViewById<ImageView>(R.id.PokeImage)
                Glide.with(this@MainActivity)
                    . load(pokeList22[0])
                    .fitCenter()
                    .into(pokeImageView)

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]

    }
    private fun getImageURL(){
        val client = AsyncHttpClient()
        client["https://pokeapi.co/api/v2/pokemon/", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                pokeImageURL = json.jsonObject.getJSONObject("sprites").getString("front_default")

                pokeList22.add(pokeImageURL)



            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }

        }

    }

}