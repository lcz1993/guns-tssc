package com.stylefeng.guns.modular.tssc.service;

import com.stylefeng.guns.modular.tssc.entity.News;

import java.util.List;

/**
 * 新闻Service
 *
 * @author fengshuonan
 * @Date 2018-04-23 17:12:55
 */
public interface INewsService {
    List<News> findList(News news);
    void insert(News news);
    int update(News news);
    News get(String id);
    int delete(News news);
}
