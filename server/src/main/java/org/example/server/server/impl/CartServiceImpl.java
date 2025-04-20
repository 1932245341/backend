package org.example.server.server.impl;

import org.example.common.context.BaseContext;
import org.example.pojo.entity.Address;
import org.example.pojo.entity.Cart;
import org.example.server.mapper.CartMapper;
import org.example.server.server.interfa.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void addCart(Cart cart) {
        cartMapper.addCart(cart);
    }

    @Override
    public List<Cart> getCartByUserId() {
        long userId = BaseContext.getCurrentId();
        Cart cart = Cart.builder().userId((int)userId).build();
        return cartMapper.getCartByUserId(cart);
    }

    public void deleteCart(int id ) {
        cartMapper.deleteCart(id);
    }


}
