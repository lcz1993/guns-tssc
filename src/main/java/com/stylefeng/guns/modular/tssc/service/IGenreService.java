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
    void insert(Genre genre);
    void delete(Genre genre);
    void update(Genre genre);
}
