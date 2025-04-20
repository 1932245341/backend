package org.example.server.server.interfa;

import org.example.pojo.entity.Cart;

import java.util.List;

public interface CartService {

    void addCart(Cart cart);
    List<Cart> getCartByUserId();
    void deleteCart(int id );
}
