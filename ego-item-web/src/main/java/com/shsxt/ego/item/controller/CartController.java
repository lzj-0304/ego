package com.shsxt.ego.item.controller;

import com.shsxt.ego.item.service.ICartService;
import com.shsxt.ego.rpc.pojo.TbUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CartController {
    @Resource
    private ICartService cartService;

    /**
     * 添加商品到购物车
     * @param itemId
     * @return
     */
    @RequestMapping("cart/add/{itemId}")
    public String addCard(@PathVariable Long itemId, HttpServletRequest request){
        TbUser user= (TbUser) request.getAttribute("user");
        cartService.addItemToCart(user.getId(),itemId);
        return "cartSuccess";
    }


    /**
     *  购物车列表展示页
     *    根据用户id 查询购物车商品信息
     * @return
     */
    @RequestMapping("cart/cart")
    public String cart(HttpServletRequest request){
        TbUser user = (TbUser) request.getAttribute("user");
        request.setAttribute("carMap",cartService.queryCartsByUserId(user.getId()));
        return "cart";
    }


    /**
     * 更新购物车商品数量信息
     * @param itemId
     * @param num
     * @param request
     * @return
     */
    @RequestMapping("cart/update/num/{itemId}/{num}")
    @ResponseBody
    public String updateCard(@PathVariable Long itemId,@PathVariable Integer num, HttpServletRequest request){
        TbUser user= (TbUser) request.getAttribute("user");
        cartService.updateCard(user.getId(),itemId,num);
        return "ok";
    }

    @RequestMapping("cart/delete/{itemId}")
    public String deleteCardItem(@PathVariable Long itemId, HttpServletRequest request){
        TbUser user= (TbUser) request.getAttribute("user");
        cartService.deleteCardItem(user.getId(),itemId);
        return "redirect:/cart/cart.html";
    }

    @RequestMapping("delete/cart/all")
    public String deleteCardAll(HttpServletRequest request){
        TbUser user= (TbUser) request.getAttribute("user");
        cartService.deleteCardAll(user.getId());
        return "redirect:/cart/cart.html";
    }

}
