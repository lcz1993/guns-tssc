package com.stylefeng.guns.modular.tssc.controller;

import com.stylefeng.guns.common.constant.tips.Tip;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.modular.system.util.JsonResult;
import com.stylefeng.guns.modular.tssc.entity.News;
import com.stylefeng.guns.modular.tssc.service.INewsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * 新闻控制器
 *
 * @author fengshuonan
 * @Date 2018-04-23 17:12:55
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

    private String PREFIX = "/tssc/news/";

    @Resource
    private INewsService iNewsService;
    /**
     * 跳转到新闻首页
     */
    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("newsList",iNewsService.findList(new News()));
        return PREFIX + "news.html";
    }

    /**
     * 跳转到添加新闻
     */
    @RequestMapping("/news_add")
    public String newsAdd(Model model) {
        return PREFIX + "news_add.html";
    }

    /**
     * 跳转到修改新闻
     */
    @RequestMapping("/news_update/{newsId}")
    public String newsUpdate(@PathVariable String newsId, Model model) {
        model.addAttribute("news",iNewsService.get(newsId));
        return PREFIX + "news_edit.html";
    }

    /**
     * 获取新闻列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return null;
    }

    /**
     * 新增新闻
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Tip add(News news) {
        iNewsService.insert(news);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除新闻
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String newsId) {
        News news = new News();
        news.setId(newsId);
        iNewsService.delete(news);
        return SUCCESS_TIP;
    }


    /**
     * 修改新闻
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(News news,Model model) {
        iNewsService.update(news);
        return super.SUCCESS_TIP;
    }

    /**
     * 新闻详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public JsonResult detail(String id, Model model) {
        return new JsonResult(iNewsService.get(id));
    }
}
