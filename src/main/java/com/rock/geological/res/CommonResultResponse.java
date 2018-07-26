package com.rock.geological.res;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 统一数据返回RES
 * @param <T>
 */
public class CommonResultResponse<T> {
    String message;
    int code;
    T data;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public CommonResultResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public CommonResultResponse(String message, int code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    /**
     * 请求返回数据处理
     * @return
     */
    public JSONObject general() {
        JSONObject json = (JSONObject) JSON.toJSON(this);
        return json;
    }
}
