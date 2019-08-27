package com.okchain.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.okchain.client.impl.OKChainRPCClientImpl;
import com.okchain.transaction.BuildTransaction;
import com.okchain.types.*;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class OKChainRPCClientImplTest {
    private static String privateKey = "de0e9d9e7bac1366f7d8719a450dab03c9b704172ba43e0a25a7be1d51c69a87";
    private static String mnemo = "sustain hole urban away boy core lazy brick wait drive tiger tell";
    private static String addr = "okchain1mm43akh88a3qendlmlzjldf8lkeynq68r8l6ts";
    // rpc
    private static String url_rpc = "http://localhost:20157";

    @Test
    public void testCreateAccount() {
        OKChainRPCClientImpl okc = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        AccountInfo accountInfo = okc.createAccount();
        Assert.assertNotNull(accountInfo);
        System.out.println(accountInfo);
    }

    @Test
    public void testGetAccountInfo() {
        OKChainRPCClientImpl okc = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        AccountInfo accountInfo = okc.getAccountInfo(this.privateKey);
        Assert.assertNotNull(accountInfo.getPrivateKey());
        Assert.assertNotNull(accountInfo.getSequenceNumber());
        Assert.assertNotNull(accountInfo.getAccountNumber());
        System.out.println(accountInfo);
    }

    @Test
    public void testGetAccountInfoFromMnemonic(){
        OKChainRPCClientImpl okc = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        AccountInfo accountInfo = okc.getAccountInfoFromMnemonic(this.mnemo);
        Assert.assertNotNull(accountInfo.getPrivateKey());
        Assert.assertNotNull(accountInfo.getSequenceNumber());
        Assert.assertNotNull(accountInfo.getAccountNumber());
        System.out.println(accountInfo);
    }

    @Test
    public void testGenerateMnemonic() {
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        String Mnemonic = client.generateMnemonic();
        Assert.assertNotNull(Mnemonic);
        System.out.println(Mnemonic);
    }

    // transact
    // get readable order-ID from the response
    public String GetOrderID(JSONObject jo) {
        String encodedId = jo.getJSONObject("deliver_tx").getJSONArray("tags").getJSONObject(1).getString("value");
        return new String(java.util.Base64.getDecoder().decode(encodedId));
    }

    @Test
    public void testSendSendTransaction() throws NullPointerException, IOException {
        BuildTransaction.setMode("block");
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        AccountInfo account = client.getAccountInfo(this.privateKey);
        String to = "okchain1t2cvfv58764q4wdly7qjx5d2z89lewvwq2448n";
        String memo = "send memo";
        List<Token> amountList = new ArrayList<>();
        Token amount = new Token("10.00000000", "okb");
        amountList.add(amount);
        JSONObject ret = client.sendSendTransaction(account, to, amountList, memo);
        System.out.println(ret);
    }

    @Test
    public void testSendPlaceOrderTransaction() throws IOException {
        BuildTransaction.setMode("block");
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        AccountInfo account = client.getAccountInfo(this.privateKey);
        String side = "BUY";
        String product = "xxb_okb";
        String price = "1.10000000";
        String quantity = "1.22000000";
        String memo = "new order memo";
        RequestPlaceOrderParams param = new RequestPlaceOrderParams(price, product, quantity, side);
        JSONObject ret = client.sendPlaceOrderTransaction(account, param, memo);
        System.out.println(ret);

        String orderID = GetOrderID(ret);
        System.out.println("orderID:" + orderID);
    }

    @Test
    public void testSendCancelOrderTransaction() throws IOException {
        BuildTransaction.setMode("block");
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        AccountInfo account = client.getAccountInfo(this.privateKey);
        // u can get order-ID by placing a new order
        String orderId = "ID0000045067-1";
        String memo = "cancel order memo";
        JSONObject ret = client.sendCancelOrderTransaction(account, orderId, memo);
        System.out.println(ret);
    }

    // query

    @Test
    public void testGetAccountALLTokens() {
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        BaseModel bm = client.getAccountALLTokens(this.addr, "all");
        System.out.println(bm);
        Assert.assertNotNull(bm.getData());
    }

    @Test
    public void testGetAccountToken() {
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        BaseModel bm = client.getAccountToken(this.addr, "gyc-3b3");
        System.out.println(bm);
        Assert.assertEquals(bm.getCode(), "0");
    }

    @Test
    public void testGetTokens() {
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        BaseModel bm = client.getTokens();
        System.out.println(bm);
        Assert.assertEquals(bm.getCode(), "0");
    }

    @Test
    public void testGetToken() {
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        BaseModel bm = client.getToken("gyc-3b3");
        System.out.println(bm);
        Assert.assertEquals(bm.getCode(), "0");
    }

    @Test
    public void testGetProducts() {
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        BaseModel bm = client.getProducts();
        System.out.println(bm);
        Assert.assertEquals(bm.getCode(), "0");
    }

    @Test
    public void testGetDepthBook() {
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        BaseModel bm = client.getDepthBook("xxb_okb");
        System.out.println(bm);
        Assert.assertEquals(bm.getCode(), "0");
    }

    @Test
    public void testGetCandles() {
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        BaseModel bm = client.getCandles("60", "xxb_okb", "100");
        System.out.println(bm);
        Assert.assertEquals(bm.getCode(), "0");
    }

    @Test
    public void testGetTickers() {
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        BaseModel bm = client.getTickers("10");
        System.out.println(bm);
        Assert.assertEquals(bm.getCode(), "0");
    }

    @Test
    public void testGetMatches() {
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        // get the system time right now and convert it to String
        String nowTimeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        BaseModel bm = client.getMatches("xxb_okb", "0", nowTimeStamp, "0", "10");
        System.out.println(bm);
        Assert.assertEquals(bm.getCode(), "0");
    }

    @Test
    public void testGetOrderListOpen() {
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        String product = "xxb_okb";
        String side = "BUY";
        String start = "1";
        // get the system time right now and convert it to String
        String end = String.valueOf(System.currentTimeMillis() / 1000);
        String page = "0";
        String perPage = "10";
        RequestOrderListOpenParams olop = new RequestOrderListOpenParams(product, this.addr, start, end, side, page, perPage);
        BaseModel bm = client.getOrderListOpen(olop);
        System.out.println(bm);
        Assert.assertEquals(bm.getCode(), "0");
    }

    @Test
    public void testGetOrderListClosed() {
        // cancel a order first by okchaincli
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        String product = "xxb_okb";
        String side = "BUY";
        String start = "1";
        // get the system time right now and convert it to String
        String end = String.valueOf(System.currentTimeMillis() / 1000);
        String page = "0";
        String perPage = "10";
        // if input hideNofill is not "true", we always treat it as "false"
        String hideNoFill = "false";
        RequestOrderListClosedParams olcp = new RequestOrderListClosedParams(product, this.addr, start, end, side, page, perPage, hideNoFill);
        BaseModel bm = client.getOrderListClosed(olcp);
        System.out.println(bm);
        Assert.assertEquals(bm.getCode(), "0");
    }

    @Test
    public void testGetDeals() {
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        String product = "xxb_okb";
        String side = "BUY";
        String start = "1";
        // get the system time right now and convert it to String
        String end = String.valueOf(System.currentTimeMillis() / 1000);
        String page = "0";
        String perPage = "10";
        RequestDealsParams rdp = new RequestDealsParams(product, this.addr, start, end, side, page, perPage);
        BaseModel bm = client.getDeals(rdp);
        System.out.println(bm);
        Assert.assertEquals(bm.getCode(), "0");
    }

    @Test
    public void testGetTransactions() {
        OKChainRPCClientImpl client = OKChainRPCClientImpl.getOKChainClient(this.url_rpc);
        String type = "1";
        String start = "1";
        // get the system time right now and convert it to String
        String end = String.valueOf(System.currentTimeMillis() / 1000);
        String page = "0";
        String perPage = "10";
        RequestTransactionsParams rtp = new RequestTransactionsParams(this.addr, type, start, end, page, perPage);
        BaseModel bm = client.getTransactions(rtp);
        System.out.println(bm);
        Assert.assertEquals(bm.getCode(), "0");
    }

}