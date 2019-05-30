package io.okchain.types;

import com.alibaba.fastjson.annotation.JSONField;


public class PostTransaction {
    private StdTransaction tx;

    @JSONField(name = "return")
    private String returnType;

    public PostTransaction() {

    }
    public PostTransaction(StdTransaction tx, String returnType) {
        this.tx = tx;
        this.returnType = returnType;
    }

    public void setTx(StdTransaction tx) {
        this.tx = tx;
    }

    public StdTransaction getTx() {
        return tx;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }


}
