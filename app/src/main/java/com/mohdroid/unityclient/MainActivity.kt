package com.mohdroid.unityclient

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGame.setOnClickListener {
             Intent(this, MiddleActivity::class.java).apply {
                 startActivity(this)
             }
        }

        btnFrg.setOnClickListener {
            Intent(this, UnityActivityFragment::class.java).apply {
                startActivity(this)
            }
        }
    }
}