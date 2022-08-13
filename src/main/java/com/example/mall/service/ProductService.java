package com.example.mall.service;

import com.example.mall.model.entity.Product;
import com.example.mall.model.request.AddProductReq;
import com.example.mall.model.request.ProductListReq;
import com.github.pagehelper.PageInfo;

import java.io.File;

public interface ProductService {

    void add(AddProductReq addProductReq);

    void update(Product updateProduct);

    void delete(Integer id);

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    Product detail(Integer id);

    PageInfo list(ProductListReq productListReq);

    void addProductByExcel(File destFile);
}
