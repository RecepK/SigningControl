package com.kurban.signingcontrol.data

object Value {

    abstract class SigningValue {
        abstract val value: Any
    }

    class BooleanValue(override val value: Boolean) : SigningValue()

    class StringValue(override val value: String) : SigningValue()

    class SigningException(val message: String, val exception: Exception)
}