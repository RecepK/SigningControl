package com.kurban.signingcontrol.helper

import android.content.Context
import android.content.pm.PackageManager
import com.kurban.signingcontrol.Sha
import com.kurban.signingcontrol.data.SigningCallback
import com.kurban.signingcontrol.data.Value
import com.kurban.signingcontrol.data.Value.SigningException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException

class SigningHelper(context: Context, packageId: String) : BaseHelper(context, packageId), ContractHelper {

    override fun appSignature(): SigningCallback {
        return appSignatureSHA(Sha.SHA1)
    }

    override fun signatureWithPackageId(packageId: String): SigningCallback {
        super.setPackageId(packageId)
        val callback: SigningCallback
        callback = try {
            SigningCallback(
                Value.StringValue(validateAppSignature()), null
            )
        } catch (e: PackageManager.NameNotFoundException) {
            //e.printStackTrace();
            SigningCallback(null, SigningException("NameNotFoundException", e))
        } catch (e: NoSuchAlgorithmException) {
            //e.printStackTrace();
            SigningCallback(null, SigningException("NoSuchAlgorithmException", e))
        } catch (e: CertificateException) {
            //e.printStackTrace();
            SigningCallback(null, SigningException("CertificateException", e))
        }
        return callback
    }

    override fun equalsSignatureSHA(
        packageId: String,
        sha1: String
    ): SigningCallback {
        super.setPackageId(packageId)
        val callback: SigningCallback
        callback = try {
            SigningCallback(
                Value.BooleanValue(validateAppSignature() == sha1),
                null
            )
        } catch (e: PackageManager.NameNotFoundException) {
            //e.printStackTrace();
            SigningCallback(null, SigningException("NameNotFoundException", e))
        } catch (e: NoSuchAlgorithmException) {
            //e.printStackTrace();
            SigningCallback(null, SigningException("NoSuchAlgorithmException", e))
        } catch (e: CertificateException) {
            //e.printStackTrace();
            SigningCallback(null, SigningException("CertificateException", e))
        }
        return callback
    }

    override fun appSignatureSHA(shaType: Sha): SigningCallback {
        val callback: SigningCallback
        callback = try {
            SigningCallback(
                Value.StringValue(validateAppSignature(shaType)),
                null
            )
        } catch (e: PackageManager.NameNotFoundException) {
            //e.printStackTrace();
            SigningCallback(null, SigningException("NameNotFoundException", e))
        } catch (e: NoSuchAlgorithmException) {
            //e.printStackTrace();
            SigningCallback(null, SigningException("NoSuchAlgorithmException", e))
        } catch (e: CertificateException) {
            //e.printStackTrace();
            SigningCallback(null, SigningException("CertificateException", e))
        }
        return callback
    }
}