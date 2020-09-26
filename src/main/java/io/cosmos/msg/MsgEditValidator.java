package io.cosmos.msg;

import io.cosmos.common.EnvInstance;
import io.cosmos.msg.utils.Message;
import io.cosmos.msg.utils.type.MsgEditValidatorValue;
import io.cosmos.types.Description;

public class MsgEditValidator extends MsgBase {

    public static void main(String[] args) {
        EnvInstance.setEnv("okq");

        MsgEditValidator msg = new MsgEditValidator();
        msg.setMsgType("okexchain/staking/MsgEditValidator");
        msg.initMnemonic(EnvInstance.getEnv().GetNode0Mnmonic());

        Message messages = msg.produceMsg();

        msg.submit(messages, "6.00000000", "200000", "");
    }

    public Message produceMsg() {

        Description d = new Description();
        d.setDetails("1");
        d.setIdentity("1");
        d.setMoniker("m1");
        d.setWebsite("1");

        MsgEditValidatorValue value = new MsgEditValidatorValue();

        System.out.println("this.operAddress:");
        System.out.println(this.operAddress);

        value.setAddress(this.operAddress);

        value.setDescription(d);

        Message<MsgEditValidatorValue> msg = new Message<>();
        msg.setType(msgType);
        msg.setValue(value);
        return msg;
    }
}
