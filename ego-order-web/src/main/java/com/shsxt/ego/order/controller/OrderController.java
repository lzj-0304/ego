package com.shsxt.ego.order.controller;

import com.shsxt.ego.order.model.CartItem;
import com.shsxt.ego.order.service.IEgoOrderService;
import com.shsxt.ego.rpc.pojo.TbOrder;
import com.shsxt.ego.rpc.pojo.TbOrderShipping;
import com.shsxt.ego.rpc.pojo.TbUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    @Resource
    private IEgoOrderService orderService;

    /****
     * 处理跳转到订单确认页面的请求
     */
    @RequestMapping("/order/cart")
    public String orderCart(HttpServletRequest request){
        //获得当前登录用户对象
        TbUser user=(TbUser) request.getAttribute("user");
        Long id=user.getId();
        Map<Long, CartItem> carMap = orderService.loadCarItemMapService(id);
        request.setAttribute("carMap",carMap);
        return "ordercart";
    }

    /**
     * 生成订单
     * @param tbOrder
     * @param orderShipping
     * @param request
     * @return
     */
    @RequestMapping("/order/save")
    public String orderSave(TbOrder tbOrder,
                            TbOrderShipping orderShipping, HttpServletRequest request){
        //获得当前登录用户对象
        TbUser user=(TbUser) request.getAttribute("user");

        Long userId=user.getId();

        Map<String, String> map = orderService.saveOrderService(tbOrder, userId, orderShipping);
        request.setAttribute("itemid",map.get("itemid"));
        request.setAttribute("total", map.get("total"));
        return "success";

    }

    /****
     * 处理加载用户订单列表的请求
     */
    @RequestMapping("/order/list")
    public String orderList(HttpServletRequest request){
        //获得当前登录用户对象
        TbUser user=(TbUser) request.getAttribute("user");

        Long id=user.getId();

        List<TbOrder> list = orderService.loadOrderListService(id);

        request.setAttribute("orderList", list);

        return "orders";
    }
}
