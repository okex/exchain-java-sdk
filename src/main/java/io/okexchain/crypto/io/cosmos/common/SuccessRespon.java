package io.okexchain.crypto.io.cosmos.common;

import com.google.gson.annotations.SerializedName;

public class SuccessRespon<T> {
    public String status;
    @SerializedName("data")
    public T dataObject;
}
