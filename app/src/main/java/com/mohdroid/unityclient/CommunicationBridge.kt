package com.mohdroid.unityclient

import android.util.Log
import com.unity3d.player.UnityPlayer


/**
 * We can only call Unity methods with no parameters or with a string parameter.
 */
 object CommunicationBridge {

    init {
        val name = Thread.currentThread().name
        Log.d("aaaa:Communication", name)
    }
    @Volatile
    private var communicationCallback: CommunicationCallback? = null

    @Synchronized
    fun setCallback(communicationCallback: CommunicationCallback) {
        CommunicationBridge.communicationCallback = communicationCallback
    }

    fun callToUnityWithNoMessage(method: String) {
        UnityPlayer.UnitySendMessage("Cube", method, "")
    }


    fun callToUnityWithMessage(method: String,param: String) {
        UnityPlayer.UnitySendMessage("Cube", method, param)
    }

    @Synchronized
    fun callFromUnityWithNoParameters() {
        communicationCallback!!.onNoParamCall()
    }

    @Synchronized
    fun callFromUnityWithOneParameter(param: String) {
        Log.d("aaaa", param)
        communicationCallback!!.onOneParamCall(param)
    }
    @Synchronized
    fun callFromUnityWithTwoParameters(param1: String, param2: Int){
        Log.d("aaaa", "$param1 $param2")
        communicationCallback!!.onTwoParamCall(param1, param2)
    }

    fun callFromUnityWithTwoParameterss(param1: String, param2: String){
        Log.d("aaaa", "$param1 $param2")
    }
    @Synchronized
    fun androidHardwareBackButton() {
        communicationCallback!!.onBackPressed()
    }
    interface CommunicationCallback {
        fun onNoParamCall()
        fun onOneParamCall(param: String)
        fun onTwoParamCall(param1: String, param2: Int)
        fun onBackPressed()
    }
}


