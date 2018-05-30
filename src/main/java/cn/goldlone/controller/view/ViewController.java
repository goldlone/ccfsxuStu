package cn.goldlone.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by CN on 2018/1/10.
 */
@Controller
public class ViewController {


    public String index() {
        return "/index";
    }

//    @GetMapping("/login")
//    public String login() {
//        return "/login";
//    }
//
//
//    @GetMapping("/head")
//    public String head() {
//        return "/all/index";
//    }

}
