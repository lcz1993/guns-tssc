package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.tssc.entity.*;
import com.stylefeng.guns.modular.tssc.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
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
public class TsscController {

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

}
