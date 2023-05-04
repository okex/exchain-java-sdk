package com.feesplit;


import com.alibaba.fastjson.JSONObject;
import com.okbchain.env.EnvInstance;
import com.okbchain.msg.common.Message;
import com.okbchain.msg.gov.MsgParameterChangeProposal;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MsgFeesplitParameterChangeProposalTest {


    //test result https://www.oklink.com/zh-cn/okc-test/tx/0x8F7FDCCE8CD604D3B353FAB2B7548FD4D2A5DE8EABA3D63B7355B082CA5E9422
    //test result https://www.oklink.com/zh-cn/okc-test/tx/0x01F36CA31FF9D6CBCCDDB1360854B1300F4A10BA156E9C81E6833D0244F794D4
    //test result https://www.oklink.com/zh-cn/okc-test/tx/0x29DCB78763BBF48E55BF0241E6C644DB7C8973FA43FB5F3E84FFD19B6B071E77
    @Test
    public void testMsgFeesplitParameterChangeProposal() throws Exception {
        EnvInstance.getEnvLocalNet();
        MsgParameterChangeProposal msg = new MsgParameterChangeProposal();
        msg.initMnemonic("puzzle glide follow cruel say burst deliver wild tragic galaxy lumber offer");
        Message messages = msg.produceParameterChangeProposalMsg(
                "test",
                "test",
                "feesplit",
                "DeveloperShares",
                "",
                "\"0.5\"",
                "1000",
                "10.00000000"
        );

        JSONObject res = msg.submit(messages, "0.05000000", "500000", "okbchain change parameter proposal!");
        try {
            boolean succeed = msg.isTxSucceed(res);
            System.out.println("tx " + (succeed ? "succeed" : "failed"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
