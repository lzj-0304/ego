package com.shsxt.ego.item.service;

import com.shsxt.ego.item.model.CartItem;

import java.util.Map;

public interface ICartService {
    public void addItemToCart(Long userId,Long itemId);

    public Map<Long, CartItem> queryCartsByUserId(Long userId);

    void updateCard(Long id, Long itemId, Integer num);

    void deleteCardItem(Long id, Long itemId);

    void deleteCardAll(Long id);
}
