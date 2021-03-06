package com.stylefeng.guns.modular.tssc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lcz
 * 产品基础类
 */
@TableName("product")
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;
    /**
     *唯一主键
     */
    private String id;
    /**
     *名称
     */
    private String name;
    /**
     *类别ID
     */
    private String genreId;
    /**
     *年份
     */
    private String year;
    /**
     *团队ID
     */
    private String teamId;
    /**
     *客户
     */
    private String customer;
    /**
     *内容
     */
    private String introduce;
    /**
     *图片
     */
    private String image;
    /**
     *封面图片
     */
    private String boundary;

    //表示排除类中的属性.
    @TableField(exist = false)
    private List<String> images = new ArrayList<String>();
    @TableField(exist = false)
    private List<Genre> genres = new ArrayList<Genre>();
    @TableField(exist = false)
    private Studio studio;

    public Product() {
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getBoundary() {
        return boundary;
    }

    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
