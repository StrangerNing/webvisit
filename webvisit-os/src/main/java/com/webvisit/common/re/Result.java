package com.webvisit.common.re;

import java.io.Serializable;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/2
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -2310002475443677643L;
    private static final Integer STATUS_SUCCESS =1;
    private static final Integer STATUS_FAILURE = -1;
    private static final Integer STATUS_ERROR = -2;
    public static final String MSG_SUCCESS = "success";
    public static final String MSG_FAILURE = "failure";
    public static final String MSG_ERROR = "error";

    /**
     * 正常返回
     */
    public static final String CODE_SUCCESS = "1";
    /**
     * 状态改变
     */
    public static final String CODE_STATUS_ERROR = "2";
    /**
     * 数据版本异常
     */
    public static final String CODE_VERSION_ERROR = "3";
    /**
     * 参数校验异常
     */
    public static final String CODE_PARAM_ERROR = "4";
    /**
     * 其他
     */
    public static final String CODE_OTHER = "99";


    /**
     * 状态
     */
    private int status;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 错误码
     */
    private String code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private T re;

    public Result(){

    }

    public Result(int status, boolean success, String msg) {
        this.status = status;
        this.success = success;
        this.msg = msg;
    }

    public Result(int status, boolean success, String code, String msg) {
        this.status = status;
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public Result(int status, boolean success, String msg, T re) {
        this.status = status;
        this.success = success;
        this.msg = msg;
        this.re = re;
    }

    public Result(int status, boolean success, String code, String msg, T re) {
        this.status = status;
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.re = re;
    }

    /**
     * 请求成功
     *
     * @param
     * @return
     */
    public static Result success() {
        return new Result(STATUS_SUCCESS, Boolean.TRUE, Result.CODE_SUCCESS, MSG_SUCCESS);
    }

    /**
     * 请求成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(STATUS_SUCCESS, Boolean.TRUE, Result.CODE_SUCCESS, MSG_SUCCESS, data);
    }

    /**
     * 请求成功
     * @param msg
     * @param code
     * @return
     */
    public static Result success(String msg, String code){
        return new Result(STATUS_SUCCESS, Boolean.TRUE, code, msg);
    }

    /**
     * 失败
     *
     * @param
     * @return
     */
    public static Result failure() {
        return new Result(STATUS_FAILURE, Boolean.FALSE, Result.CODE_OTHER, MSG_FAILURE);
    }

    /**
     * 失败，带消息
     *
     * @param msg
     * @param
     * @return
     */
    public static Result failure(String msg) {
        return new Result(STATUS_FAILURE, Boolean.FALSE, Result.CODE_OTHER, msg);
    }

    /**
     * 失败，带消息，带数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> failure(String msg, T data) {
        return new Result<T>(STATUS_FAILURE, Boolean.FALSE, Result.CODE_OTHER, msg, data);
    }

    /**
     * 失败，带消息，带错误码
     *
     * @param msg
     * @param code
     * @return
     */
    public static <T> Result<T> failure(String msg, String code) {
        return new Result<T>(STATUS_FAILURE, Boolean.FALSE, code, msg);
    }

    /**
     * 失败，带消息，带数据，带错误码
     *
     * @param data
     * @param <T>
     * @param code
     * @return
     */
    public static <T> Result<T> failure(String msg, T data, String code) {
        return new Result<T>(STATUS_FAILURE, Boolean.FALSE, code, msg, data);
    }

    /**
     * 错误
     *
     * @param
     * @return
     */
    public static Result error() {
        return new Result(STATUS_ERROR, Boolean.FALSE, Result.CODE_OTHER, MSG_ERROR);
    }

    /**
     * 错误，带消息
     *
     * @param msg
     * @return
     */
    public static Result error(String msg) {
        return new Result(STATUS_ERROR, Boolean.FALSE, Result.CODE_OTHER, msg);
    }

    /**
     * 错误，带消息 ,带code
     *
     * @param msg
     * @return
     */
    public static Result error(String msg, String code) {
        return new Result(STATUS_ERROR, Boolean.FALSE, code, msg);
    }

    /**
     * 错误，带消息，带数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String msg, T data) {
        return new Result<T>(STATUS_ERROR, Boolean.FALSE, Result.CODE_OTHER,msg, data);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getRe() {
        return re;
    }

    public void setRe(T re) {
        this.re = re;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", success=" + success +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", re=" + re +
                '}';
    }
}
