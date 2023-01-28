package com.djmakes.rickandmorty

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.ViewModelProvider
import com.djmakes.rickandmorty.viewModel.SharedViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_charactor)

        val nameText = findViewById<TextView>(R.id.nameTextView)
        val headerImage = findViewById<AppCompatImageView>(R.id.headerImageView)
        val aliveText = findViewById<TextView>(R.id.statusTextView)
        val originText = findViewById<TextView>(R.id.originTextView)
        val speciesText = findViewById<TextView>(R.id.speciesTextView)
        val genderImageView = findViewById<ImageView>(R.id.genderImageView)

        viewModel.refreshCharacter(57)
        viewModel.characterByIdLiveData.observe(this){ response ->
           if (response == null) {
               Toast.makeText(
                   this@MainActivity,
                   "Unsuccessful network call!",
                   Toast.LENGTH_SHORT
               ).show()
               return@observe
        }
            nameText.text = response.name
            aliveText.text = response.status
            speciesText.text = response.species
            originText.text = response.origin.name

            Picasso.get().load(response.image).into(headerImage)

            if(response.gender.equals("male", true)){
                genderImageView.setImageResource(R.drawable.ic_baseline_male_24)
            } else{
                genderImageView.setImageResource(R.drawable.ic_baseline_female_24)
            }
        }
    }
}