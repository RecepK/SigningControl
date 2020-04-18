package com.kurban.signingcontrol.data

import com.kurban.signingcontrol.data.Value.SigningException
import com.kurban.signingcontrol.data.Value.SigningValue

class SigningCallback(
    private val signingValue: SigningValue?,
    val signingException: SigningException?
) {
    fun getSigningValue(): Any? {
        return signingValue?.value
    }

    override fun toString(): String {
        return "SigningCallback{" +
                "signingValue=" + getSigningValue() +
                ", signingException=" + signingException +
                '}'
    }

}