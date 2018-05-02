package com.stylefeng.guns.modular.tssc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.stylefeng.guns.common.persistence.model.Menu;

import java.io.Serializable;
import java.util.List;

/**
 *工作室类
 * @author lcz
 */
public class Studio extends Model<Studio> {

    private static final long serialVersionUID = 1L;
    /**
     * 对应数据库的字段
     */
    /**
     * 唯一主键
     */
    private String id;              //唯一主键
    private String name;            //名称
    private String synopsis;        //简介
    private String introduce;       //介绍
    private String image;           //图片
    private String logo;            //logo
    private String founddate;       //成立日期

    public Studio() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFounddate() {
        return founddate;
    }

    public void setFounddate(String founddate) {
        this.founddate = founddate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
