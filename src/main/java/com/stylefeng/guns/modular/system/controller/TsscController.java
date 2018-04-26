package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.util.FileUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.util.JsonResult;
import com.stylefeng.guns.modular.tssc.entity.*;
import com.stylefeng.guns.modular.tssc.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 前台登陆控制器
 *
 * @author lcz
 * @Date 2018年3月26日 10：50
 */
@Controller
@RequestMapping(value={"/","/tssc"})
public class TsscController extends BaseController {

    @Resource
    private IBespeakService iBespeakService;
    @Resource
    private IGenreService iGenreService;
    @Resource
    private IFieldService iFieldService;
    @Resource
    private ICompanyService iCompanyService;
    @Resource
    private INewsService iNewsService;
    @Resource
    private IStudioService iStudioService;
    @Resource
    private IPersonnelService iPersonnelService;
    @Resource
    private IRecruitService iRecruitService;
    @Resource
    private IProductService iProductService;
    @Resource
    private GunsProperties gunsProperties;

    //初始化界面
    @RequestMapping(value="/")
    public String tssc(){
        return "/tssc/index.html";
    }

    //关于我们
    @RequestMapping(value="/about")
    public String about(Model model){
        //公司本身
        Company company = iCompanyService.findCompany();
        model.addAttribute("company",company);
        //团队
        Studio studio = new Studio();
        List<Studio> studioList = iStudioService.findList(studio);
        model.addAttribute("studioList",studioList);
        //人员
        Personnel personnel = new Personnel();
        List<Personnel> personnelList = iPersonnelService.findList(personnel);
        for (Personnel p:personnelList) {
            List<String> list = new ArrayList<String>();
            String[] strings = p.getPosition().split(";");
            for (String s:strings) {
                list.add(s);
            }
            p.setPositionList(list);
        }
        model.addAttribute("personnelList",personnelList);
        //服务领域
        Field field = new Field();
        List<Field> fieldList = iFieldService.findList(field);
        model.addAttribute("fieldList",fieldList);
        return "/tssc/about.html";
    }
    //关于我们
    @RequestMapping(value="/career")
    public String career(Model model){
        model.addAttribute("company",iCompanyService.findCompany());
        return "/tssc/career.html";
    }
    //关于我们
    @RequestMapping(value="/careerList")
    public String careerList(){
        return "/tssc/career-list.html";
    }
    //关于我们
    @RequestMapping(value="/contact")
    public String contact(Model model){
        model.addAttribute("genres",iGenreService.findList(new Genre()));
        return "/tssc/contact.html";
    }

    /**
     * 新闻稿页面
     * @param model
     * @return
     */
    @RequestMapping(value="/media")
    public String media(Model model){
        //公司本身
        Company company = iCompanyService.findCompany();
        model.addAttribute("company",company);
        model.addAttribute("newsList",iNewsService.findList(new News()));
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
    public String works(Model model,String genreId,String year,String studioId) {
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
        return "/tssc/works.html";
    }
    //关于我们
    @RequestMapping(value="/worksDetail")
    public String worksDetail(){
        return "/tssc/works-detailed.html";
    }

    /**
     * 返回图片
     *
     * @author stylefeng
     * @Date 2017/5/24 23:00
     */
    @RequestMapping("/{pictureId}")
    public void renderPicture(@PathVariable("pictureId") String pictureId, HttpServletResponse response) {
        String path = gunsProperties.getFileUploadPath() +"tssc\\\\"+ pictureId + ".jpg";
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
    /**
     * 返回图片
     *
     * @author stylefeng
     * @Date 2017/5/24 23:00
     */
    @RequestMapping("/studio/{pictureId}")
    public void renderStudioPicture(@PathVariable("pictureId") String pictureId, HttpServletResponse response) {
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
    /**
     * 返回图片
     *
     * @author stylefeng
     * @Date 2017/5/24 23:00
     */
    @RequestMapping("/product/{pictureId}")
    public void renderProductPicture(@PathVariable("pictureId") String pictureId, HttpServletResponse response) {
        String path = gunsProperties.getFileUploadPath() +"product\\\\"+ pictureId + ".jpg";
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
    /**
     * 返回图片
     *
     * @author stylefeng
     * @Date 2017/5/24 23:00
     */
    @RequestMapping("/personnel/{pictureId}")
    public void renderPersonnelPicture(@PathVariable("pictureId") String pictureId, HttpServletResponse response) {
        String path = gunsProperties.getFileUploadPath() +"personnel\\\\"+ pictureId + ".jpg";
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
    /**
     * 产品详情
     */
    @RequestMapping(value = "/product/detail")
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
     * 新闻详情
     */
    @RequestMapping(value = "/media/detail")
    @ResponseBody
    public JsonResult detail(String id, Model model) {
        return new JsonResult(iNewsService.get(id));
    }
    /**
     * 服务预约提交
     */
    @RequestMapping(value = "/contact/submit")
    public String contactSubmit(HttpServletRequest request,HttpServletResponse response, Model model) {
        Bespeak bespeak = new Bespeak();
        String name = request.getParameter("name");
        bespeak.setName(name);
        String title = request.getParameter("title");
        bespeak.setTitle(title);
        String email = request.getParameter("email");
        bespeak.setEmail(email);
        String tel = request.getParameter("tel");
        bespeak.setTel(tel);
        String companyName = request.getParameter("companyName");
        bespeak.setCompanyName(companyName);
        String companyWeb = request.getParameter("companyWeb");
        bespeak.setCompanyWeb(companyWeb);
        String budget = request.getParameter("budget");
        bespeak.setBudget(budget);
        String brand = request.getParameter("brand");
        bespeak.setBrand(brand);
        String[] service = request.getParameterValues("service");
        bespeak.setService(StringUtils.join(service, ","));
        String remark = request.getParameter("remark");
        bespeak.setRemark(remark);
        String isaccept = "1";
        bespeak.setIsaccept(isaccept);
        iBespeakService.insert(bespeak);
        return REDIRECT + "/tssc/contact/success";
    }
    /**
     * 服务预约成功提交后跳转
     */
    @RequestMapping(value = "/contact/success")
    public String contactSuccess(HttpServletRequest request,HttpServletResponse response, Model model) {
        return "/tssc/contact_success.html";
    }
    /**
     * 新闻详情
     */
    @RequestMapping(value = "/recruit")
    public String recruit(HttpServletRequest request,HttpServletResponse response, Model model) {
        List<Recruit> recruitList = iRecruitService.findList(new Recruit());
        model.addAttribute("recruitList",recruitList);
        return "/tssc/career-list.html";
    }
    /**
     * 新闻详情
     */
    @RequestMapping(value = "/recruit/detail")
    public String recruitDetail(HttpServletRequest request,HttpServletResponse response, Model model,String id) {
        Recruit recruit = new Recruit();
        recruit.setId(id);
        recruit = iRecruitService.get(recruit);
        model.addAttribute("recruit",recruit);
        model.addAttribute("company",iCompanyService.findCompany());
        return "/tssc/career_detail.html";
    }
}
