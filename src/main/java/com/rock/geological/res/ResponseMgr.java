package com.rock.geological.res;

import com.alibaba.fastjson.JSONObject;

public class ResponseMgr {
    public static JSONObject generalErrorBody(String msg, int code) {
        CommonResultResponse commonResult = new CommonResultResponse(msg, code);
        return commonResult.general();
    }


    /**
     * 成功请求不带数据
     *
     * @return
     */
    public static JSONObject success() {
        CommonResultResponse commonResult = new CommonResultResponse("success", Constant.RESCODE_SUCCESS);
        return commonResult.general();
    }

    /**
     * 成功请求返回失败
     *
     * @return
     */
    public static JSONObject failed() {
        CommonResultResponse commonResult = new CommonResultResponse("failed", Constant.RESCODE_SUCCESS);
        return commonResult.general();
    }


    /**
     * 成功请求带消息不带数据
     *
     * @return
     */
    public static JSONObject success(String message) {
        CommonResultResponse commonResult = new CommonResultResponse(message, Constant.RESCODE_SUCCESS);
        return commonResult.general();
    }

    /**
     * 成功请求带返回数据
     *
     * @param data
     * @return
     */
    public static JSONObject successWithData(Object data) {
        CommonResultResponse commonResult = new CommonResultResponse("success", Constant.RESCODE_SUCCESS_DATA, data);
        return commonResult.general();
    }

    /**
     * 成功请求带消息和返回数据
     *
     * @param data
     * @return
     */
    public static JSONObject successWithData(String message, Object data) {
        CommonResultResponse commonResult = new CommonResultResponse(message, Constant.RESCODE_SUCCESS_DATA, data);
        return commonResult.general();
    }

    /**
     * 服务器异常不带数据
     *
     * @return
     */
    public static JSONObject err() {
        CommonResultResponse commonResult = new CommonResultResponse("请稍后再试", Constant.RESCODE_EXCEPTION);
        return commonResult.general();
    }

    /**
     * 服务器异常带数据
     *
     * @param data
     * @return
     */
    public static JSONObject errWhitData(Object data) {
        CommonResultResponse commonResult = new CommonResultResponse("请稍后再试", Constant.RESCODE_EXCEPTION_DATA, data);
        return commonResult.general();
    }

    /**
     * 服务器异常带数据和消息
     *
     * @param data
     * @return
     */
    public static JSONObject errWhitData(String msg, Throwable data) {
        CommonResultResponse commonResult = new CommonResultResponse(msg, Constant.RESCODE_EXCEPTION_DATA, data);
        return commonResult.general();
    }

    /**
     * 用户未登录
     *
     * @return
     */
    public static JSONObject noLogin() {
        CommonResultResponse commonResult = new CommonResultResponse("用户未登录", Constant.RESCODE_NOLOGIN);
        return commonResult.general();
    }

    /**
     * 结果为空
     *
     * @return
     */
    public static JSONObject noExist() {
        CommonResultResponse commonResult = new CommonResultResponse("结果为空", Constant.RESCODE_NOEXIST);
        return commonResult.general();
    }

    /**
     * 无操作权限
     *
     * @return
     */
    public static JSONObject noAuth() {
        CommonResultResponse commonResult = new CommonResultResponse("拒绝授权", Constant.RESCODE_NOAUTH);
        return commonResult.general();
    }

    /**
     * 登录过期
     *
     * @return
     */
    public static JSONObject loginExpire() {
        CommonResultResponse commonResult = new CommonResultResponse("登录过期", Constant.RESCODE_LOGINEXPIRE);
        return commonResult.general();
    }


}
