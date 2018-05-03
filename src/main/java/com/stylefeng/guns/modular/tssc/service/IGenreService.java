package com.stylefeng.guns.modular.tssc.service;

import com.stylefeng.guns.modular.tssc.entity.Genre;

import java.util.List;

/**
 * 产品类别Service
 *
 * @author fengshuonan
 * @Date 2018-04-19 17:07:35
 */
public interface IGenreService {
    List<Genre> findList(Genre genre);
    Genre get(Genre genre);
    Genre get(String id);
    void insert(Genre genre);
    int delete(Genre genre);
    int update(Genre genre);
}
