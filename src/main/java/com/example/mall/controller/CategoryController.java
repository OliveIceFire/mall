package com.example.mall.controller;

import com.example.mall.common.ApiRestResponse;
import com.example.mall.common.Constant;
import com.example.mall.exception.MallExceptionEnum;
import com.example.mall.model.entity.Category;
import com.example.mall.model.entity.User;
import com.example.mall.model.request.AddCategoryReq;
import com.example.mall.model.request.UpdateCategoryReq;
import com.example.mall.model.vo.CategoryVO;
import com.example.mall.service.CategoryService;
import com.example.mall.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@RestController
public class CategoryController {

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;


    @ApiOperation("添加分类")
    @PostMapping("admin/category/add")
    public ApiRestResponse addCategory(HttpSession session, @Valid @RequestBody AddCategoryReq req) {
        User currentUser = (User) session.getAttribute(Constant.MALL_USER);
//        User currentUser = UserFilter.currentUser;
        if (currentUser == null) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        boolean adminRole = userService.checkAdminRole(currentUser);
        if (!adminRole) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);

        }else {
            categoryService.add(req);
            return ApiRestResponse.success();
        }
    }

    @ApiOperation("更新分类")
    @PostMapping("admin/category/update")
    public ApiRestResponse updateCategory(@Valid @RequestBody UpdateCategoryReq updateCategoryReq, HttpSession session) {
        User currentUser = (User) session.getAttribute(Constant.MALL_USER);
//        User currentUser = UserFilter.currentUser;
        if (currentUser == null) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        boolean adminRole = userService.checkAdminRole(currentUser);
        if (!adminRole) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);

        }else {
            Category category = new Category();
            BeanUtils.copyProperties(updateCategoryReq, category);
            categoryService.update(category);
            return ApiRestResponse.success();
        }
    }
    @ApiOperation("后台删除目录")
    @PostMapping("admin/category/delete")
    public ApiRestResponse deleteCategory(@RequestParam("id") Integer id) {
        categoryService.delete(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台获取目录列表")
    @PostMapping("admin/category/list")
    public ApiRestResponse listCategoryForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo pageInfo = categoryService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation("前台获取目录列表")
    @GetMapping("category/list")
    public ApiRestResponse listCategoryForCustomer() {
        List<CategoryVO> categoryVOS = categoryService.listCategoryForCustomer(0);
        return ApiRestResponse.success(categoryVOS);
    }


}
