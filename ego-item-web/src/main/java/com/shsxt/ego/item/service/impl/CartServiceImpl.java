package com.shsxt.ego.item.service.impl;


import com.shsxt.ego.item.model.CartItem;
import com.shsxt.ego.item.service.ICartService;
import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.service.IItemService;
import com.shsxt.ego.utils.JsonUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * 购物车service
 * 添加商品到购物车
 * 查询当前用户购物车列表
 * 更新购物车商品信息
 * 清空购物车
 */
@Service
public class CartServiceImpl implements ICartService {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> hashOperations;

    @Resource
    private IItemService itemServiceProxy;

    @Override
    public void addItemToCart(Long userId, Long itemId) {
        String key = String.valueOf(userId);
        String field= String.valueOf(itemId);
        /**
         * 判断商品是否添加过
         *   如果购物车存在该商品  商品数量加1 反之 数量为1
         */
        CartItem cartItem = null;
        if (hashOperations.hasKey(key, field)) {
            cartItem = JsonUtils.jsonToPojo(hashOperations.get(key,field),CartItem.class);
            cartItem.setNum(cartItem.getNum()+1);
        } else {
            TbItem item = itemServiceProxy.loadItemService(itemId);
            cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setNum(1);
        }


        hashOperations.put(String.valueOf(userId), String.valueOf(itemId), JsonUtils.objectToJson(cartItem));
    }


    /**
     * 查询购物车商品列表信息
     * @param userId
     * @return
     */
    public Map<Long,CartItem> queryCartsByUserId(Long userId){
        Map<Long,CartItem> carts =null;
        Map<String,String> map =  hashOperations.entries(String.valueOf(userId));
        if(null !=map && map.size()>0){
            carts =new HashMap<Long,CartItem>();
            Map<Long, CartItem> finalCarts = carts;
            map.forEach((k, v)->{
                finalCarts.put(Long.parseLong(k),JsonUtils.jsonToPojo(v,CartItem.class));
            });
        }
        return carts;
    }

    /**
     * 更新购物车
     * @param itemId
     * @param num
     */
    @Override
    public void updateCard(Long userId, Long itemId, Integer num) {
        String key = String.valueOf(userId);
        String field= String.valueOf(itemId);
       CartItem  cartItem = JsonUtils.jsonToPojo(hashOperations.get(key,field),CartItem.class);
        //修改购物车数量
        cartItem.setNum(num);
       // 更新商品信息到redis
       hashOperations.put(key,field,JsonUtils.objectToJson(cartItem));
    }

    @Override
    public void deleteCardItem(Long userId, Long itemId) {
        hashOperations.delete(String.valueOf(userId),String.valueOf(itemId));
    }

    @Override
    public void deleteCardAll(Long id) {
        redisTemplate.delete(String.valueOf(id));
    }
}
