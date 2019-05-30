package io.okchain.crypto;

import com.google.crypto.tink.subtle.Random;
import io.okchain.common.ConstantIF;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Utils;
import org.spongycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @program: coin-parent-sdk
 * @description:
 * @author: liqiang
 * @create: 2019-03-20 19:29
 **/
public class Crypto {

    public static String generatePrivateKey() {
        Random random=new Random();
        return Hex.toHexString(random.randBytes(32));
    }

    public static String generateMnemonic() {
        return "";
    }

    public static byte[] sign(byte[] msg, String privateKey) throws NoSuchAlgorithmException {
        ECKey k = ECKey.fromPrivate(new BigInteger(privateKey, 16));

        return sign(msg, k);
    }

    public static byte[] sign(byte[] msg, ECKey k) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] msgHash = digest.digest(msg);

        ECKey.ECDSASignature signature = k.sign(Sha256Hash.wrap(msgHash));

        byte[] result = new byte[64];
        System.arraycopy(Utils.bigIntegerToBytes(signature.r, 32), 0, result, 0, 32);
        System.arraycopy(Utils.bigIntegerToBytes(signature.s, 32), 0, result, 32, 32);
        return result;
    }

    public static String generatePubKeyHexFromPriv(String privateKey) {
        ECKey k = ECKey.fromPrivate(new BigInteger(privateKey, 16));
        return k.getPublicKeyAsHex();
    }

    public static byte[] generatePubKeyFromPriv(String privateKey) {
        ECKey k = ECKey.fromPrivate(new BigInteger(privateKey, 16));
        return k.getPubKey();
    }

    public String generateAddressFromPriv(String privateKey) {
        String pub = generatePubKeyHexFromPriv(privateKey);
        return generateAddressFromPub(pub);
    }

    public String generateAddressFromPub(String pubKey) {
        try {
            String addr = AddressUtil.createNewAddressSecp256k1(ConstantIF.ADDRESS_PREFIX, pubKey.getBytes());
            return addr;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
