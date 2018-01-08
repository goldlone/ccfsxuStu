package cn.goldlone.utils;

import cn.goldlone.model.Result;

/**
 * Created by CN on 2018/1/7.
 */
public class ResultUtils {

    /**
     * 请求成功
     * @param data
     * @return
     */
    public static Result success(Object data, String msg) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 无数据的请求成功
     * @return
     */
    public static Result success() {
        return success(null, "请求成功");
    }

    /**
     * 出现啊错误
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
