package com.stylefeng.guns.modular.tssc.controller;

import com.stylefeng.guns.common.constant.tips.Tip;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.tssc.entity.Genre;
import com.stylefeng.guns.modular.tssc.service.IGenreService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 产品类别控制器
 *
 * @author fengshuonan
 * @Date 2018-04-19 17:07:35
 */
@Controller
@RequestMapping("/genre")
public class GenreController extends BaseController {

    private String PREFIX = "/tssc/genre/";

    @Resource
    private IGenreService iGenreService;
    /**
     * 跳转到产品类别首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "genre.html";
    }

    /**
     * 跳转到添加产品类别
     */
    @RequestMapping("/genre_add")
    public String genreAdd() {
        return PREFIX + "genre_add.html";
    }

    /**
     * 跳转到修改产品类别
     */
    @RequestMapping("/genre_update/{genreId}")
    public String genreUpdate(@PathVariable String genreId, Model model) {
        Genre genre = new Genre();
        genre.setId(genreId);
        model.addAttribute("genre",iGenreService.get(genre));
        return PREFIX + "genre_edit.html";
    }

    /**
     * 获取产品类别列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public List<Genre> list(String condition) {
        Genre genre = new Genre();
        genre.setName(condition);
        return iGenreService.findList(genre);
    }

    /**
     * 新增产品类别
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Tip add(@Valid Genre genre, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        if(ToolUtil.isEmpty(genre.getId())){
            genre.setId(ToolUtil.getUid());
        }
        iGenreService.insert(genre);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除产品类别
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Tip delete(String genreId) {
        if (ToolUtil.isEmpty(genreId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        Genre genre = new Genre();
        genre.setId(genreId);
        iGenreService.delete(genre);
        return SUCCESS_TIP;
    }


    /**
     * 修改产品类别
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Tip update(@Valid Genre genre, BindingResult result) {
        iGenreService.update(genre);
        return super.SUCCESS_TIP;
    }

    /**
     * 产品类别详情
     */
    @RequestMapping(value = "/detail/{genreId}")
    @ResponseBody
    public Tip detail(@PathVariable String genreId, Model model) {
        Genre genre = new Genre();
        genre.setId(genreId);
        model.addAttribute("genre",iGenreService.get(genre));
        return super.SUCCESS_TIP;
    }
}
