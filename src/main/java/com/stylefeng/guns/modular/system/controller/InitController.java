package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class InitController extends BaseController {
    //初始化界面
    @RequestMapping(value="")
    public String index(){
        return super.REDIRECT + "/tssc";
    }
}
