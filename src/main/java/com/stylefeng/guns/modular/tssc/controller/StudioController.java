package com.stylefeng.guns.modular.tssc.controller;

import com.stylefeng.guns.common.annotion.Permission;
import com.stylefeng.guns.common.annotion.log.BussinessLog;
import com.stylefeng.guns.common.constant.Const;
import com.stylefeng.guns.common.constant.Dict;
import com.stylefeng.guns.common.constant.state.ManagerStatus;
import com.stylefeng.guns.common.constant.tips.Tip;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.common.persistence.model.User;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.FileUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.factory.UserFactory;
import com.stylefeng.guns.modular.system.transfer.UserDto;
import com.stylefeng.guns.modular.system.util.JsonResult;
import com.stylefeng.guns.modular.system.warpper.RoleWarpper;
import com.stylefeng.guns.modular.tssc.entity.Studio;
import com.stylefeng.guns.modular.tssc.service.IStudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 工作室控制器
 *
 * @author fengshuonan
 * @Date 2018-04-10 10:57:11
 */
@Controller
@RequestMapping("/studio")
public class StudioController extends BaseController {

    private String PREFIX = "/tssc/studio/";
    @Autowired
    private IStudioService iStudioService;
    @Resource
    private GunsProperties gunsProperties;
    /**
     * 跳转到工作室首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "studio.html";
    }

    /**
     * 跳转到添加工作室
     */
    @RequestMapping("/studio_add")
    public String studioAdd() {
        return PREFIX + "studio_add.html";
    }

    /**
     * 跳转到修改工作室
     */
    @RequestMapping("/studio_update/{studioId}")
    public String studioUpdate(@PathVariable Integer studioId, Model model) {
        return PREFIX + "studio_edit.html";
    }

    /**
     * 获取工作室列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public JsonResult list() {
        Studio studio = new Studio();
        List<Studio> list = iStudioService.findList(studio);
        return new JsonResult(list);
    }

    /**
     * 新增工作室
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Tip add(@Valid Studio studio, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        // 判断名称是否重复
        Studio s = new Studio();
        s.setName(studio.getName());
        List<Studio> list = iStudioService.findList(s);
        if (list.size() != 0) {
            throw new BussinessException(BizExceptionEnum.USER_ALREADY_REG);
        }

        iStudioService.insert(studio);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除工作室
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResult delete(String id) {
        Studio studio = new Studio();
        if(ToolUtil.isEmpty(id)){
            return new JsonResult(1,"ID不能为空！",id);
        }
        studio.setId(id);
        iStudioService.delete(studio);
        return new JsonResult("ok");
    }


    /**
     * 修改工作室
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public JsonResult update(String param) {
        String[] strings = param.split("&|=");
        Studio studio = new Studio();
        for (int i = 0; i<strings.length;i+=2) {
            String str = strings[i];
            if (str.equals("id")){
                studio.setId(strings[i+1]);
            }
            if(str.equals("name")){
                studio.setName(strings[i+1]);
            }
            if(str.equals("synopsis")){
                studio.setSynopsis(strings[i+1]);
            }
            if (str.equals("introduce")){
                studio.setIntroduce(strings[i+1]);
            }
            if (str.equals("image")){
                studio.setImage(strings[i+1]);
            }
            if (str.equals("logo")){
                studio.setLogo(strings[i+1]);
            }
            if (str.equals("founddate")){
                studio.setFounddate(strings[i+1]);
            }
        }
        iStudioService.update(studio);
        return new JsonResult(studio);
    }

    /**
     * 工作室详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }

    /**
     * 上传图片(上传到项目的webapp/static/img)
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture){
        String pictureName = UUID.randomUUID().toString() + ".jpg";
        String fileSavePath = gunsProperties.getFileUploadPath();
        String location = "studio\\\\";
        try {
            picture.transferTo(new File(fileSavePath +location+ pictureName));
        } catch (Exception e) {
            File file = new File(fileSavePath +location);
            file.mkdirs();
            try {
                picture.transferTo(new File(fileSavePath +location+ pictureName));
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
    public void renderPicture(@PathVariable("pictureId") String pictureId, HttpServletResponse response) {
        String path = gunsProperties.getFileUploadPath() +"studio\\\\"+ pictureId + ".jpg";
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
