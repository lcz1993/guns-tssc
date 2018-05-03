package com.stylefeng.guns.modular.tssc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lcz
 */
@TableName(value = "news_straw")
public class News extends Model<News>{
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
     *时间
     */
    private String time;
    /**
     *简介
     */
    private String synopsis;
    /**
     *内容
     */
    private String content;
    /**
     *图片
     */
    private String image;

    @TableField(exist = false)
    private List<String> images = new ArrayList<String>();

    public News() {
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> list) {
        this.images = list;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
