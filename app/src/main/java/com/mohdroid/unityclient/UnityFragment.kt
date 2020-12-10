package com.mohdroid.unityclient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.unity3d.player.UnityPlayer

class UnityFragment  : Fragment() {


    private lateinit var unityPlayer: UnityPlayer

    private lateinit var frameLayout: FrameLayout
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

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        unityPlayer = UnityPlayer(activity)
        val view = inflater.inflate(R.layout.fragment_unity, container, false)
        frameLayout = view.findViewById(R.id.frameLayoutForUnity)
        frameLayout.addView(unityPlayer.view, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        unityPlayer.requestFocus()
        unityPlayer.windowFocusChanged(true)
        synchronized(o) {
            CommunicationBridge.setCallback(callback)
        }
        addControlsToUnityFrame()
        return view
    }

    override fun onDestroy() {
        unityPlayer.destroy()
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        unityPlayer.pause()
    }

    override fun onResume() {
        super.onResume()
        unityPlayer.resume()
    }

    fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun addControlsToUnityFrame() {
        val layout: FrameLayout =unityPlayer

        val showMainBtn = Button(activity)
        showMainBtn.text = "Show Main"
        showMainBtn.x = 10f
        showMainBtn.y = 500f
        showMainBtn.setOnClickListener { }
        layout.addView(showMainBtn, 300, 200)


        val redBtn = Button(activity)
        redBtn.text = "Red"
        redBtn.x = 320f
        redBtn.y = 500f
        redBtn.setOnClickListener {
            CommunicationBridge.callToUnityWithMessage("changeColor", "red")
        }
        layout.addView(redBtn, 300, 200)

        val blueBtn = Button(activity)
        blueBtn.text = "blue"
        blueBtn.x = 630f
        blueBtn.y = 500f
        blueBtn.setOnClickListener {
            CommunicationBridge.callToUnityWithMessage("changeColor", "blue")
        }
        layout.addView(blueBtn, 300, 200)

        val incrementBtn = Button(activity)
        incrementBtn.text = "Increment"
        incrementBtn.x = 630f
        incrementBtn.y = 800f
        incrementBtn.setOnClickListener {
            CommunicationBridge.callToUnityWithMessage("changeSpeed", "inc")
        }
        layout.addView(incrementBtn, 300, 200)

        val decrementBtn = Button(activity)
        decrementBtn.text = "Decrement"
        decrementBtn.x = 320f
        decrementBtn.y = 800f
        decrementBtn.setOnClickListener {
            CommunicationBridge.callToUnityWithMessage("changeSpeed", "dec")
        }
        layout.addView(decrementBtn, 300, 200)

        val stopBtn = Button(activity)
        stopBtn.text = "Stop"
        stopBtn.x = 10f
        stopBtn.y = 800f
        stopBtn.setOnClickListener {
            CommunicationBridge.callToUnityWithNoMessage("CallFromAndroidWithNoMessage")
        }
        layout.addView(stopBtn, 300, 200)

    }


}