package com.rock.geological.res;


public class Constant {


    //无数据
    public static int CODE_WRONG_NO_DATA = 4002;

    //保存失败
    public static int CODE_WRONG_SAVE_FAIL = 4003;

    //更新失败
    public static int CODE_WRONG_UPDATE_FAIL = 4004;

    //删除失败
    public static int CODE_WRONG_DELETE_FAIL = 4005;


    /**
     * 数据请求返回码
     */
    public static final int RESCODE_SUCCESS = 1000;                //成功
    public static final int RESCODE_SUCCESS_DATA = 1001;        //成功(有返回数据)
    public static final int RESCODE_NOEXIST = 1004;                //查询结果为空


    public static final int RESCODE_EXCEPTION = 1002;             //请求抛出异常
    public static final int RESCODE_EXCEPTION_DATA = 1008;        //异常带数据
    public static final int RESCODE_NOLOGIN = 1003;               //未登陆状态
    public static final int RESCODE_NOAUTH = 1005;                //无操作权限
    public static final int RESCODE_LOGINEXPIRE = 1009;           //登录过期


}
