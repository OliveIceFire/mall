package com.example.mall.service;

import com.example.mall.model.entity.Category;
import com.example.mall.model.request.AddCategoryReq;
import com.example.mall.model.vo.CategoryVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CategoryService {
    void add(AddCategoryReq addCategoryReq);

    void update(Category updateCategoryReq);

    void delete(Integer id);

    PageInfo<Category> listForAdmin(Integer pageNum, Integer pageSize);

    //    @Cacheable(value = "listCategoryForCustomer")
    List<CategoryVO> listCategoryForCustomer(Integer parentId);
}
