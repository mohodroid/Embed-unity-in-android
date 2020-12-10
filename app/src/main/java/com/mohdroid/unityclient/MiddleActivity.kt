package com.mohdroid.unityclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MiddleActivity : AppCompatActivity() {
    private var activityWasJustCreated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_middle)
        activityWasJustCreated = true

        Intent(this, UnityActivity::class.java).apply {
            startActivity(this)
        }
    }

    override fun onResume() {
        super.onResume()
        if (!activityWasJustCreated) {
            finish()
        }
        activityWasJustCreated = false
    }
}