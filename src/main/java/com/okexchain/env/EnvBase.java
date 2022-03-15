package com.okexchain.env;

public class EnvBase {

    protected String restServerUrl;
    protected String mainPrefix;
    protected String denom;
    protected String chainID;
    protected String hdPath;
    protected String validatorAddrPrefix;
    protected String pubPrefix;
    protected String restPathPrefix;
    protected String txUrlPath;
    protected String accountUrlPath;

    private static EnvBase envBase=new EnvBase();

    public EnvBase() {
        this.restServerUrl = "http://127.0.0.1:8545";
        this.mainPrefix = "ex";
        this.denom = "okt";
        //todo
        this.chainID = "okexchain-1";
        this.hdPath = "M/44H/60H/0H/0/0";
        this.validatorAddrPrefix = "exvaloper";
        this.pubPrefix = "expub";
        //todo
        this.restPathPrefix = "/okexchain-test/v1";
        //todo
        this.txUrlPath = "/okexchain/v1/txs";
        this.accountUrlPath = "/auth/accounts/";
    }


    public EnvBase getEnvMainNet(){
        envBase.setRestServerUrl("https://exchainrpc.okex.org");
        envBase.setMainPrefix("ex");
        envBase.setDenom("okt");
        envBase.setChainID("okexchain-66");
        envBase.setHdPath("M/44H/60H/0H/0/0");
        envBase.setValidatorAddrPrefix("exvaloper");
        envBase.setPubPrefix("expub");
        envBase.setRestPathPrefix("/okexchain/v1");
        envBase.setTxUrlPath("/okexchain/v1/txs");
        envBase.setAccountUrlPath("/auth/accounts/");
        return envBase;

    }



    public EnvBase getEnvTestNet(){
        envBase.setRestServerUrl("https://exchaintestrpc.okex.org");
        envBase.setMainPrefix("ex");
        envBase.setDenom("okt");
        envBase.setChainID("okexchain-65");
        envBase.setHdPath("M/44H/60H/0H/0/0");
        envBase.setValidatorAddrPrefix("exvaloper");
        envBase.setPubPrefix("expub");
        envBase.setRestPathPrefix("/okexchain-test/v1");
        envBase.setTxUrlPath("/okexchain-test/v1/txs");
        envBase.setAccountUrlPath("/auth/accounts/");
        return envBase;
    }

    public String GetMainPrefix() {
        return this.mainPrefix;
    }

    public String GetDenom() {
        return this.denom;
    }

    public String GetChainid() {
        return this.chainID;
    }

    public String GetRestServerUrl() {
        return this.restServerUrl;
    }

    public String GetHDPath() {
        return this.hdPath;
    }

    public String GetValidatorAddrPrefix() {
        return this.validatorAddrPrefix;
    }

    public String GetPubPrefix() {
        return this.pubPrefix;
    }

    public String GetRestPathPrefix() {
        return this.restPathPrefix;
    }

    public String GetTxUrlPath() {
        return this.txUrlPath;
    }

    public String GetAccountUrlPath() {
        return this.accountUrlPath;
    }

    public void setRestServerUrl(String restServerUrl) {
        this.restServerUrl = restServerUrl;
    }

    public void setMainPrefix(String mainPrefix) {
        this.mainPrefix = mainPrefix;
    }

    public void setDenom(String denom) {
        this.denom = denom;
    }

    public void setChainID(String chainID) {
        this.chainID = chainID;
    }

    public void setHdPath(String hdPath) {
        this.hdPath = hdPath;
    }

    public void setValidatorAddrPrefix(String validatorAddrPrefix) {
        this.validatorAddrPrefix = validatorAddrPrefix;
    }

    public void setPubPrefix(String pubPrefix) {
        this.pubPrefix = pubPrefix;
    }

    public void setRestPathPrefix(String restPathPrefix) {
        this.restPathPrefix = restPathPrefix;
    }

    public void setTxUrlPath(String txUrlPath) {
        this.txUrlPath = txUrlPath;
    }

    public void setAccountUrlPath(String accountUrlPath) {
        this.accountUrlPath = accountUrlPath;
    }
}
