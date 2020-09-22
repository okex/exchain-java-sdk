package com.okexchain.crypto.io.cosmos.util;

import com.okexchain.crypto.io.cosmos.crypto.encode.Bech32;

import com.okexchain.crypto.io.cosmos.crypto.encode.ConvertBits;
import com.okexchain.crypto.io.cosmos.crypto.hash.Ripemd;
import com.okexchain.crypto.io.cosmos.exception.AddressFormatException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @program: cosmos-java-sdk
 * @description: 地址相关的工具
 * @author: liqiang
 * @create: 2018-12-17 11:31
 **/
public class AddressUtil {

    public static String createNewAddressSecp256k1(String mainPrefix, byte[] publickKey) throws Exception {
        String addressResult = null;
        try {
            byte[] pubKeyHash = sha256Hash(publickKey, 0, publickKey.length);
            byte[] address = Ripemd.ripemd160(pubKeyHash);
            byte[] bytes = encode(0, address);
            addressResult = Bech32.encode(mainPrefix, bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return addressResult;

    }

    public static byte[] getPubkeyValue(byte[] publickKey) throws Exception {
        try {
            byte[] pubKeyHash = sha256Hash(publickKey, 0, publickKey.length);
            byte[] value = Ripemd.ripemd160(pubKeyHash);
            return value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static byte[] decodeAddress(String address){
        byte[] dec = Bech32.decode(address).getData();
        return ConvertBits.convertBits(dec, 0, dec.length, 5, 8, false);
    }

    private static byte[] sha256Hash(byte[] input, int offset, int length) throws NoSuchAlgorithmException {
        byte[] result = new byte[32];
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(input, offset, length);
        return digest.digest();
    }

    private static byte[] encode(int witnessVersion, byte[] witnessProgram) throws AddressFormatException {
        byte[] convertedProgram = ConvertBits.convertBits(witnessProgram, 0, witnessProgram.length, 8, 5, true);
        return convertedProgram;
    }
}