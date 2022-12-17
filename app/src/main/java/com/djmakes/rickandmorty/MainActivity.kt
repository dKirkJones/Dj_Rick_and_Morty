package com.djmakes.rickandmorty

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.djmakes.rickandmorty.data.CharacterById
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_charactor)

        val nameText = findViewById<TextView>(R.id.nameTextView)
        val headerText = findViewById<AppCompatImageView>(R.id.headerImageView)
        val aliveText = findViewById<TextView>(R.id.statusTextView)
        val originText = findViewById<TextView>(R.id.originTextView)
        val speciesText = findViewById<TextView>(R.id.speciesTextView)


        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val rickAndMortyService: RickAndMortyService =
            retrofit.create(RickAndMortyService::class.java)

        rickAndMortyService.getCharacterById(54).enqueue(object : Callback<CharacterById> {
            override fun onResponse(call: Call<CharacterById>, response: Response<CharacterById>) {
                Log.i(TAG, response.toString())

                if (!response.isSuccessful) {
                    Toast.makeText(
                        this@MainActivity,
                        "Unsuccessful network call!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                val body = response.body()!!
                val name = body.name

                nameText.text = name
            }

            override fun onFailure(call: Call<CharacterById>, t: Throwable) {
                Log.i(TAG, t.message ?: "Null message")
            }
        })
    }
}