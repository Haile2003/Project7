package com.example.project6


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var pokeList: MutableList<TodoAdapter.Pokemon>
    private lateinit var rvPokemon: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokeList = mutableListOf()
        rvPokemon = findViewById<RecyclerView>(R.id.rvDisplay)

        for (k in 0..20){
            getPokeDetails()
        }


        findViewById<Button>(R.id.pokeButton).setOnClickListener(){
            pokeList.clear()
            for (k in 0..20){
                getPokeDetails()
            }
        }
    }

    private fun getPokeDetails(){
        val client = AsyncHttpClient()
        var baseImageUrl = "https://pokeapi.co/api/v2/pokemon/"
        var randInt = Random.nextInt(1,900)
        var pokeUrl = baseImageUrl + randInt.toString()

        client[pokeUrl, object : JsonHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JsonHttpResponseHandler.JSON) {
                val pokeImageUrl = json.jsonObject.getJSONObject("sprites").getString("front_default")
                Log.d("PokeImage", "response successful$json")
                Log.d("PokeImageUrl", "poke image URL set")
                val pokeNameUrl = json.jsonObject.getString("name").replaceFirstChar { it.titlecase() }
                Log.d("PokeName", "response successful$json")
                Log.d("PokeNameUrl", "poke name url set")
                val pokeWeightUrl = json.jsonObject.getString("weight")
                Log.d("PokeName", "response successful$json")
                Log.d("PokeNameUrl", "poke name url set")


                var pokemon = TodoAdapter.Pokemon(pokeImageUrl, pokeNameUrl, pokeWeightUrl)

                pokeList.add(pokemon)


                val adapter = TodoAdapter(pokeList)

                rvPokemon.adapter = adapter
                rvPokemon.layoutManager = LinearLayoutManager(this@MainActivity)
                rvPokemon.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d ("poke Image Error", errorResponse)
                Log.d("Poke Name Error", errorResponse)
                Log.d("Poke Weight Error", errorResponse)
            }
        }]
    }

}