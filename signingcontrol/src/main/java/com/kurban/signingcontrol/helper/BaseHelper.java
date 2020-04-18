package com.kurban.signingcontrol.helper;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.kurban.signingcontrol.Sha;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class BaseHelper {

    private Context context;
    private String packageId;

    public BaseHelper(Context context, String packageId) {
        this.context = context;
        this.packageId = packageId;
    }

    void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    String validateAppSignature() throws PackageManager.NameNotFoundException, NoSuchAlgorithmException, CertificateException {
        return validateAppSignature(Sha.SHA1);
    }

    String validateAppSignature(Sha shaType) throws PackageManager.NameNotFoundException, NoSuchAlgorithmException, CertificateException {
        final PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageId, PackageManager.GET_SIGNATURES);

        android.content.pm.Signature[] signatures = packageInfo.signatures;
        byte[] cert = signatures[0].toByteArray();
        InputStream input = new ByteArrayInputStream(cert);
        CertificateFactory cf = CertificateFactory.getInstance("X509");
        X509Certificate c = (X509Certificate) cf.generateCertificate(input);

        MessageDigest md = getMessageDigest(shaType);
        byte[] publicKey = md.digest(c.getEncoded());
        return hexFormatted(publicKey);
    }

    private String hexFormatted(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(String.format("%02x", bytes[i] & 0xff).toUpperCase());
            if (i < bytes.length - 1) {
                sb.append(":");
            }
        }
        return sb.toString();
    }

    private MessageDigest getMessageDigest(Sha shaType) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(shaType.name());
    }
}
