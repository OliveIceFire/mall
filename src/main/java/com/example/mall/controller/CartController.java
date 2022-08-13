package com.example.mall.controller;

import com.example.mall.common.ApiRestResponse;
import com.example.mall.filter.UserFilter;
import com.example.mall.model.vo.CartVO;
import com.example.mall.service.CartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/list")
    @ApiOperation("购物车列表")
    public ApiRestResponse list() {
        //找到现在的用户
        List<CartVO> cartList = cartService.list(UserFilter.currentUser.getId());
        for (CartVO cartVO : cartList) {
            System.out.println(cartVO);
        }
        return ApiRestResponse.success(cartList);
    }

    @PostMapping("/add")
    @ApiOperation("添加商品到购物车")
    public ApiRestResponse add(@RequestParam Integer productId, @RequestParam Integer count) {
        List<CartVO> cartVOList = cartService.add(UserFilter.currentUser.getId(), productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/update")
    @ApiOperation("添加商品到购物车")
    public ApiRestResponse update(@RequestParam Integer productId, @RequestParam Integer count) {
        List<CartVO> cartVOList = cartService.update(UserFilter.currentUser.getId(), productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/delete")
    @ApiOperation("删除商品到购物车")
    public ApiRestResponse delete(@RequestParam Integer productId) {
        System.out.println(UserFilter.currentUser.getId());
        List<CartVO> cartVOList = cartService.delete(UserFilter.currentUser.getId(), productId);

        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/select")
    @ApiOperation("选择购物车中某商品")
    public ApiRestResponse select(@RequestParam Integer productId, @RequestParam Integer selected) {
        List<CartVO> cartVOList = cartService.selectOrNot(UserFilter.currentUser.getId(), productId, selected);
        return ApiRestResponse.success(cartVOList);
    }

    @GetMapping("/selectAll")
    @ApiOperation("选择购物车中所有商品")
    public ApiRestResponse selectAll(@RequestParam Integer selected) {
        List<CartVO> cartVOList = cartService.selectAllOrNot(UserFilter.currentUser.getId(), selected);
        return ApiRestResponse.success(cartVOList);
    }
}
