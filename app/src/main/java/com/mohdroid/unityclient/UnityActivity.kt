package com.mohdroid.unityclient

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import com.unity3d.player.UnityPlayerActivity


class UnityActivity : UnityPlayerActivity() {

    private val o = Object()

    companion object {
        private val TAG: String = "AAAA:${UnityActivity::class.java}"
    }

    private val callback = object : CommunicationBridge.CommunicationCallback {
        override fun onNoParamCall() {
            showToast("Callback with no parameter")
        }

        override fun onOneParamCall(param: String) {
            showToast(param)
        }

        override fun onTwoParamCall(param1: String, param2: Int) {
            showToast("Callback with two parameters: $param1 , $param2")
        }

        override fun onBackPressed() {
            showMainActivity()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = Thread.currentThread().name
        synchronized(o) {
            CommunicationBridge.setCallback(callback)
        }
        addControlsToUnityFrame()
    }

    private fun showMainActivity() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun addControlsToUnityFrame() {
        val layout: FrameLayout = mUnityPlayer

        val showMainBtn = Button(this)
        showMainBtn.text = "Show Main"
        showMainBtn.x = 10f
        showMainBtn.y = 500f
        showMainBtn.setOnClickListener { showMainActivity() }
        layout.addView(showMainBtn, 300, 200)


        val redBtn = Button(this)
        redBtn.text = "Red"
        redBtn.x = 320f
        redBtn.y = 500f
        redBtn.setOnClickListener {
            CommunicationBridge.callToUnityWithMessage("changeColor", "red")
        }
        layout.addView(redBtn, 300, 200)

        val blueBtn = Button(this)
        blueBtn.text = "blue"
        blueBtn.x = 630f
        blueBtn.y = 500f
        blueBtn.setOnClickListener {
            CommunicationBridge.callToUnityWithMessage("changeColor", "blue")
        }
        layout.addView(blueBtn, 300, 200)

        val incrementBtn = Button(this)
        incrementBtn.text = "Increment"
        incrementBtn.x = 630f
        incrementBtn.y = 800f
        incrementBtn.setOnClickListener {
            CommunicationBridge.callToUnityWithMessage("changeSpeed", "inc")
        }
        layout.addView(incrementBtn, 300, 200)

        val decrementBtn = Button(this)
        decrementBtn.text = "Decrement"
        decrementBtn.x = 320f
        decrementBtn.y = 800f
        decrementBtn.setOnClickListener {
            CommunicationBridge.callToUnityWithMessage("changeSpeed", "dec")
        }
        layout.addView(decrementBtn, 300, 200)

        val stopBtn = Button(this)
        stopBtn.text = "Stop"
        stopBtn.x = 10f
        stopBtn.y = 800f
        stopBtn.setOnClickListener {
            CommunicationBridge.callToUnityWithNoMessage("CallFromAndroidWithNoMessage")
        }
        layout.addView(stopBtn, 300, 200)

    }

}