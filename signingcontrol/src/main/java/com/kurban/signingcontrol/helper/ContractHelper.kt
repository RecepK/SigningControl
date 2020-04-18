package com.kurban.signingcontrol.helper

import androidx.annotation.NonNull
import com.kurban.signingcontrol.Sha
import com.kurban.signingcontrol.data.SigningCallback

internal interface ContractHelper {

    @NonNull
    fun appSignature(): SigningCallback

    @NonNull
    fun signatureWithPackageId(packageId: String): SigningCallback

    @NonNull
    fun equalsSignatureSHA(packageId: String, sha1: String): SigningCallback

    @NonNull
    fun appSignatureSHA(shaType: Sha): SigningCallback
}