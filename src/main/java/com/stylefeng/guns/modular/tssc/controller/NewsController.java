package com.stylefeng.guns.modular.tssc.controller;

import com.stylefeng.guns.common.constant.tips.ErrorTip;
import com.stylefeng.guns.common.constant.tips.Tip;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.util.FileUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.util.JsonResult;
import com.stylefeng.guns.modular.tssc.entity.News;
import com.stylefeng.guns.modular.tssc.service.INewsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @Resource
    private GunsProperties gunsProperties;
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
        News news = iNewsService.get(newsId);
        String[] imageArr = news.getImage().split(",");
        List<String> images = new ArrayList<String>();
        for (String image: imageArr) {
            images.add(image);
        }
        while (images.size() < 3){
            images.add("");
        }
        news.setImages(images);
        model.addAttribute("news",news);
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
        if(ToolUtil.isEmpty(news.getId())){
            news.setId(ToolUtil.getUid());
        }
        String content = news.getContent();
        if(!ToolUtil.isEmpty(content)){
            content = ToolUtil.getEncodeHtml(content);
            news.setContent(content);
        }
        iNewsService.insert(news);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除新闻
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String newsId) {
        if(ToolUtil.isEmpty(newsId)){
            return new ErrorTip(404,"id不能为空");
        }
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
        if(ToolUtil.isEmpty(news.getId())){
            return new ErrorTip(404,"id不能为空");
        }
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

    /**
     * 上传图片(上传到项目的webapp/static/img)
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture){
        String pictureName = UUID.randomUUID().toString() + ".jpg";
        String fileSavePath = gunsProperties.getFileUploadPath() + "news/";
        try {
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            File file = new File(fileSavePath );
            file.mkdirs();
            try {
                picture.transferTo(new File(fileSavePath + pictureName));
            } catch (IOException e1) {
                throw new BussinessException(BizExceptionEnum.UPLOAD_ERROR);
            }
        }
        return pictureName;
    }
    /**
     * 返回图片
     *
     * @author stylefeng
     * @Date 2017/5/24 23:00
     */
    @RequestMapping("/{pictureId}")
    public void renderPicture(@PathVariable("pictureId") String image, HttpServletResponse response) {
        String path = gunsProperties.getFileUploadPath() +"news/"+ image + ".jpg";
        try {
            byte[] bytes = FileUtil.toByteArray(path);
            response.getOutputStream().write(bytes);
        }catch (Exception e){
            //如果找不到图片就返回一个默认图片
            try {
                response.sendRedirect("/static/img/noLogo.png");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
