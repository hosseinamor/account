package com.accountmanager.account.dto;

import com.google.gson.annotations.SerializedName;

public class BaseDto <T> {

    @SerializedName("Response")
    private T res;
    @SerializedName("ResponseCode")
    private Integer resCode =1;

    public T getResponse() {
        return res;
    }

    public void setResponse(T response) {
        this.res = response;
    }

    public Integer getResponseCode() {
        return resCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.resCode = responseCode;
    }
}
