package cn.goldlone.controller;

import cn.goldlone.model.Result;
import cn.goldlone.utils.ResultUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by CN on 2018/2/27.
 */
public class BaseController {

    @ExceptionHandler
    public Result handleException(Exception e) {
        e.printStackTrace();
        return ResultUtil.error(ResultUtil.CODE_EXCEPTION, "【异常】："+e.getMessage());
    }


}
