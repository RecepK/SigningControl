package com.kurban.signingcontrol

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kurban.signingcontrol.data.SigningCallback
import com.kurban.signingcontrol.helper.SigningHelper

class MainActivity : AppCompatActivity() {

    fun mLog(s: Any) = Log.d("RKRK", "${javaClass.simpleName} --> $s")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signingHelper = SigningHelper(applicationContext, BuildConfig.APPLICATION_ID)


        val callback0 = signingHelper.appSignature()
        // C8:C7:49:8C:B4:57:AF:EE:D3:B6:E2:5A:0F:C4:A6:76:C1:D8:25:17

        val callback1 = signingHelper.appSignatureSHA(Sha.SHA256)
        // FD:60:8F:0A:90:9E:7D:A9:4C:47:67:6C:F3:59:7F:74:BA:55:BE:C4:C4:6E:98:82:E2:B1:57:D9:B7:38:09:2E

        val packageId = "com.kurban.signingcontrol"
        val sha1 = "C8:C7:49:8C:B4:57:AF:EE:D3:B6:E2:5A:0F:C4:A6:76:C1:D8:25:17"

        val callback2 = signingHelper.equalsSignatureSHA(packageId, sha1)
        // true - false

        callbackControl(callback0)
    }

    private fun callbackControl(callback: SigningCallback) {
        callback.let {
            when {
                it.getSigningValue() != null -> {
                    mLog(it.getSigningValue().toString())
                }
                else -> {
                    mLog(it.signingException?.message.toString())
                }
            }
        }
    }
}
