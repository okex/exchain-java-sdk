package com.okbchain.env;

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

    public EnvBase() {
        this.restServerUrl = "http://127.0.0.1:8545";
        this.mainPrefix = "ex";
        this.denom = "okb";
        //todo
        this.chainID = "okbchain-67";
        this.hdPath = "M/44H/60H/0H/0/0";
        this.validatorAddrPrefix = "exvaloper";
        this.pubPrefix = "expub";
        //todo
        this.restPathPrefix = "/v1";
        //todo
        this.txUrlPath = "/v1/txs";
        this.accountUrlPath = "/auth/accounts/";
    }


    //set main net
    EnvBase configEnvMainNet() {
        this.restServerUrl = "https://exchainrpc.okex.org";
        this.chainID = "okbchain-196";
        this.restPathPrefix = "/v1";
        this.txUrlPath = "/v1/txs";
        return this;
    }


    //set test net
    EnvBase configEnvTestNet() {
        this.restServerUrl = "https://okbtestrpc.okbchain.org";
        this.chainID = "okbchaintest-195";
        this.restPathPrefix = "/v1";
        this.txUrlPath = "/v1/txs";
        return this;
    }

    //set local net
    public EnvBase configEnvLocalNet() {
        this.restServerUrl = "http://127.0.0.1:8545";
        this.chainID = "okbchain-67";
        this.restPathPrefix = "/v1";
        this.txUrlPath = "/v1/txs";
        return this;
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
