package sample;

import com.okexchain.env.EnvInstance;
import com.okexchain.env.LocalEnv;
import com.okexchain.utils.crypto.PrivateKey;
import com.okexchain.msg.MsgBase;
import com.okexchain.msg.MsgSend;
import com.okexchain.msg.tx.BoardcastTx;
import com.okexchain.msg.common.Message;
import com.okexchain.msg.tx.UnsignedTx;
import com.okexchain.msg.common.Signature;

public class ColdSign {

    public static void main(String[] args) {

        EnvInstance.setEnv(new LocalEnv("http://localhost:26659"));

        PrivateKey key = new PrivateKey("8145bfb1d3acc216c54490952c994d5e3bce09dd65ae73d0c79f892284f721e7");

        MsgSend msg = new MsgSend();
        msg.init(key.getAddress(), key.getPubKey());

        Message messages = msg.produceSendMsg(
                "okt",
                "6.00000000",
                "okexchain1v853tq96n9ghvyxlvqyxyj97589clccrufrkz9");

        try {
            UnsignedTx unsignedTx = msg.getUnsignedTx(messages,"0.01000000", "200000", "okexchain transfer!");

            Signature signature = MsgBase.signTx(unsignedTx.toString(), key.getPriKey());

            BoardcastTx signedTx = unsignedTx.signed(signature);

            MsgBase.boardcast(signedTx.toJson(), EnvInstance.getEnv().GetRestServerUrl());

        } catch (Exception e) {
            System.out.println("serialize transfer msg failed");
        }
    }
}
