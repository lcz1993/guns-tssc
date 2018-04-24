package com.stylefeng.guns.modular.tssc.controller;

import com.stylefeng.guns.common.constant.tips.Tip;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.util.FileUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.util.JsonResult;
import com.stylefeng.guns.modular.tssc.entity.Genre;
import com.stylefeng.guns.modular.tssc.entity.Product;
import com.stylefeng.guns.modular.tssc.entity.Studio;
import com.stylefeng.guns.modular.tssc.service.IGenreService;
import com.stylefeng.guns.modular.tssc.service.IProductService;
import com.stylefeng.guns.modular.tssc.service.IStudioService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 产品控制器
 *
 * @author fengshuonan
 * @Date 2018-04-19 16:34:26
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    private String PREFIX = "/tssc/product/";

    @Resource
    private IGenreService iGenreService;
    @Resource
    private IProductService iProductService;
    @Resource
    private IStudioService iStudioService;
    @Resource
    private GunsProperties gunsProperties;

    /**
     * 跳转到产品首页
     */
    @RequestMapping("")
    public String index(Model model,String genreId,String year,String studioId) {
        List<Genre> genres = iGenreService.findList(new Genre());
        model.addAttribute("genres",genres);
        List<Studio> studios = iStudioService.findList(new Studio());
        model.addAttribute("studios",studios);
        Product product = new Product();
        if(!ToolUtil.isEmpty(genreId)){
            product.setGenreId(genreId);
        }
        if(!ToolUtil.isEmpty(year)){
            product.setYear(year);
        }
        if(!ToolUtil.isEmpty(studioId)){
            product.setTeamId(studioId);
        }
        List<Product> products = iProductService.findList(product);
        for (Product p:products) {
            List<Genre> genreList = new ArrayList<Genre>();
            String[] ids = p.getTeamId().split(";");
            for (int i = 0;i < ids.length;i++){
                Genre genre = new Genre();
                genre.setId(ids[i]);
                genreList.add(iGenreService.get(genre));
            }
            p.setGenres(genres);

            p.setStudio(iStudioService.get(p.getTeamId()));

            List<String> strings = new ArrayList<String>();
            String[] images = p.getImage().split(";");
            for (int i = 0;i< images.length; i++){
                strings.add(images[i]);
            }
            p.setImages(strings);
        }
        List<List<Product>> lists = new ArrayList<List<Product>>();
        for (int i = 0;i < products.size()/12+1;i++ ) {
            int j = (i+1)*12;
            if((i+1)*12 > products.size()){
                j = products.size();
            }
            List<Product> l = products.subList(i*12,j);
            lists.add(l);
        }
        model.addAttribute("lists",lists);
        model.addAttribute("products",products);
        return PREFIX + "product.html";
    }

    /**
     * 跳转到添加产品
     */
    @RequestMapping("/product_add")
    public String productAdd(Model model) {
        List<Genre> genres = iGenreService.findList(new Genre());
        model.addAttribute("genres",genres);
        List<Studio> studios = iStudioService.findList(new Studio());
        model.addAttribute("studios",studios);
        return PREFIX + "product_add.html";
    }

    /**
     * 跳转到修改产品
     */
    @RequestMapping("/product_update/{productId}")
    public String productUpdate(@PathVariable Integer productId, Model model) {
        return PREFIX + "product_edit.html";
    }

    /**
     * 获取产品列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return null;
    }

    /**
     * 新增产品
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Tip add(@Valid Product product, BindingResult result) {


        return super.SUCCESS_TIP;
    }

    /**
     * 删除产品
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }


    /**
     * 修改产品
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
        return super.SUCCESS_TIP;
    }

    /**
     * 产品详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public JsonResult detail(String id) {
        Product product = new Product();
        product.setId(id);
        product = iProductService.get(product);
        product.setStudio(iStudioService.get(product.getTeamId()));
        String[] strings = product.getGenreId().split(";");
        List<Genre> list = new ArrayList<Genre>();
        for(int i = 0;i<strings.length;i++){
            Genre genre = new Genre();
            genre.setId(strings[i]);
            list.add(iGenreService.get(genre));
        }
        product.setGenres(list);
        return new JsonResult(product);
    }

    /**
     * 上传图片(上传到项目的webapp/static/img)
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture){
        String pictureName = UUID.randomUUID().toString() + ".jpg";
        String fileSavePath = gunsProperties.getFileUploadPath();
        String location = "product\\\\";
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
    public void renderPicture(@PathVariable("pictureId") String image, HttpServletResponse response) {
        String path = gunsProperties.getFileUploadPath() +"product\\\\"+ image + ".jpg";
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
