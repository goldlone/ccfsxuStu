package cn.goldlone.model;

/**
 * Created by CN on 2018/1/7.
 */
public class Result<T> {
    /** 返回状态码 */
    private int code;
    /** 提示信息 */
    private String msg;
    /** 返回数据 */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
