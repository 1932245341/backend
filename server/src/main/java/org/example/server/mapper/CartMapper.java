package org.example.server.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.entity.Cart;

import java.util.List;

@Mapper
public interface CartMapper {

    // 插入购物车
    void addCart(Cart cart);

    // 获取某个用户的所有购物车商品
    List<Cart> getCartByUserId(Cart cart);

    // 删除购物车中的商品
    void deleteCart(int id );



}
