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
    /**
     * 根据条件查询所有实体
     * @param news
     * @return
     */
    List<News> findList(News news);

    /**
     * 新增新闻
     * @param news
     */
    void insert(News news);

    /**
     * 修改新闻
     * @param news
     * @return
     */
    int update(News news);

    /**
     * 根据条件ID获取实体
     * @param id
     * @return
     */
    News get(String id);
    News get(News news);

    /**
     * 删除新闻
     * @param news
     * @return
     */
    int delete(News news);
}
