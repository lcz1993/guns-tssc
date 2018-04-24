package com.stylefeng.guns.modular.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前台登陆控制器
 *
 * @author lcz
 * @Date 2018年3月26日 10：50
 */
@Controller
@RequestMapping(value={"/","/tssc"})
public class TsscController {

    //初始化界面
    @RequestMapping(value="/")
    public String tssc(){
        return "/tssc/index.html";
    }

    //关于我们
    @RequestMapping(value="/about")
    public String about(){
        return "/tssc/about.html";
    }
    //关于我们
    @RequestMapping(value="/career")
    public String career(){
        return "/tssc/career.html";
    }
    //关于我们
    @RequestMapping(value="/careerList")
    public String careerList(){
        return "/tssc/career-list.html";
    }
    //关于我们
    @RequestMapping(value="/contact")
    public String contact(){
        return "/tssc/contact.html";
    }
    //关于我们
    @RequestMapping(value="/media")
    public String media(){
        return "/tssc/media.html";
    }
    //关于我们
    @RequestMapping(value="/privacy")
    public String privacy(){
        return "/tssc/privacy.html";
    }
    //关于我们
    @RequestMapping(value="/viewpoints")
    public String viewpoints(){
        return "/tssc/viewpoints.html";
    }
    //关于我们
    @RequestMapping(value="/works")
    public String works(){
        return "/tssc/works.html";
    }
    //关于我们
    @RequestMapping(value="/worksDetail")
    public String worksDetail(){
        return "/tssc/works-detailed.html";
    }

}
