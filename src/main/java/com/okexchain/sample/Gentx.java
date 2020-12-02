package com.okexchain.sample;

import com.okexchain.msg.MsgBase;
import com.okexchain.msg.MsgCreateValidator;
import com.okexchain.msg.common.Message;
import com.okexchain.msg.common.Signature;
import com.okexchain.msg.tx.BoardcastValue;
import com.okexchain.msg.tx.UnsignedTx;
import com.okexchain.utils.Utils;
import com.okexchain.utils.crypto.PrivateKey;
import com.sun.deploy.ui.UITextArea;

public class Gentx {
    public static void main(String[] args) {
//        String priKey = Crypto.generatePrivateKeyFromMnemonic("race imitate stay curtain puppy suggest spend toe old bridge sunset pride");
        PrivateKey key = new PrivateKey("b4083733cd8379f1249cb9431a074e495a64ae003273d31a7e58356eaad8a0cf");

        MsgCreateValidator msg = new MsgCreateValidator();
        System.out.println(key.getPubKey());
        msg.init(key.getPubKey(),"0","0");

        Message messages = msg.produceMsg(
                "okexchainvalconspub1zcjduepqtv2yy90ptjegdm34vfhlq2uw9eu39hjrt98sffj7yghl4s47xv7svt56mk",
                "val0","","","", "10000.00000000");

        try {
            UnsignedTx unsignedTx = msg.getUnsignedTx(messages,"", "200000", "");
            Signature signature = MsgBase.signTx(unsignedTx.toString(), key.getPriKey());
            BoardcastValue signedTx = unsignedTx.sign4gentx(signature);

            System.out.println("======= gentx json =========");
            System.out.println(signedTx.toJson());

        } catch (Exception e) {
            System.out.println("serialize transfer msg failed");
        }
    }
}
