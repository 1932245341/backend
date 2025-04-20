package org.example.server.controller.user;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.example.common.result.Result;
import org.example.pojo.entity.Cart;
import org.example.server.server.interfa.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;



    @GetMapping("/list")
    public Result<List<Cart>> list() {
        List<Cart> cartList = cartService.getCartByUserId();
        return Result.success(cartList);
    }

    @PostMapping("/add")
    public Result addCart(@RequestBody Cart cart) {
        cartService.addCart(cart);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result deleteCart(int id) {
        cartService.deleteCart(id);
        return Result.success();
    }





}
