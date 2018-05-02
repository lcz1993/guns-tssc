package com.stylefeng.guns.modular.tssc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

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
    private Date time;
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

    public News() {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
