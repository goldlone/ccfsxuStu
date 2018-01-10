package cn.goldlone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by CN on 2018/1/10.
 */
@Controller
public class ViewController {

    @GetMapping("/head")
    public String head() {
        return "/all/index";
    }

}
