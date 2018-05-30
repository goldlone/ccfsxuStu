package cn.goldlone.utils;

import cn.goldlone.model.Result;

/**
 * Created by CN on 2018/1/7.
 */
public class ResultUtil {

    /****  请求成功  ****/
    public static final int CODE_SUCCESS = 1001;
    /****  格式问题，请求失败  ****/
    public static final int CODE_FORMAT_ERROR = 2001;
    /****  信息不存在，请求失败  ****/
    public static final int CODE_NOT_EXIST = 2002;
    /****  库存不足  ****/
    public static final int CODE_NOT_ENOUGH = 2003;
    /****  信息已存在存在，请求失败  ****/
    public static final int CODE_HAD_EXIST = 2004;



    /****  抛出异常  ****/
    public static final int CODE_EXCEPTION = 3001;

    /**
     * 请求成功
     * @param data
     * @return
     */
    public static Result success(Object data, String msg) {
        Result result = new Result();
        result.setCode(ResultUtil.CODE_SUCCESS);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 无数据的请求成功
     * @return
     */
    public static Result success(String msg) {
        return success(null, msg);
    }

    /**
     * 出现错误
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

}
