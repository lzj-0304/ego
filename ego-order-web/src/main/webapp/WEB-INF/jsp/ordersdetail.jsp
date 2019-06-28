<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
   <meta http-equiv="pragma" content="no-cache">
   <meta http-equiv="cache-control" content="no-cache">
   <meta http-equiv="expires" content="0"> 
   <meta name="format-detection" content="telephone=no">  
   <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
   <meta name="format-detection" content="telephone=no">
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link rel="stylesheet" href="/css/base.css">
   <link href="/css/purchase.2012.css?v=201410141639" rel="stylesheet" type="text/css">
   <title>我的购物车 - 易购商城</title>
   <script>
   	var pageConfig  = {};
   </script>
<body> 
<!--shortcut start-->
<jsp:include page="commons/shortcut.jsp" />
<!--shortcut end-->
<div class="w w1 header clearfix">
	<div id="logo"><a href="/"><img clstag="clickcart|keycount|xincart|logo" src="/images/ego-logo.gif" title="返回易购商城首页" alt="返回易购商城首页"></a></div>
    <div class="language"><a href="javascript:void(0);" onclick="toEnCart()"></a></div>
	<div class="progress clearfix">
		
	</div>
</div>
<div class="w cart">
	<div class="cart-hd group">
		<h3>订单明细</h3>
	</div>
	<div id="show">
	
<div class="cart-frame">
    <div class="tl"></div>
    <div class="tr"></div>
</div>
<div class="cart-inner">
    <div class="cart-thead clearfix">
        <div class="column ">明细编号</div>
        <div class="column t-goods">商品</div>
        <div class="column t-price" style="width:17%">易购价</div>
        <div class="column t-quantity" style="width:20%">数量</div>
        <div class="column t-inventory"style="width:10%">小计</div>
    </div>
    <div id="product-list" class="cart-tbody">
        <!-- ************************商品开始********************* -->
        <c:set var="totalPrice" value="0"></c:set>
        <c:forEach items="${list}" var="od">
        	<!-- 计算商品价格总计 -->
	        <div id="product_11345721" data-bind="rowid:1" class="item item_selected ">
		        <div class="item_form clearfix">
		            <div class="cell p-checkbox" style="width:10%">${od.id}</div>
		            <div class="cell p-goods">
		                <div class="p-img">
		                	 
		                	<img clstag="clickcart|keycount|xincart|p-imglistcart" 
		                		src="${od.picPath}" 
		                			alt="${od.title}" width="52" height="52">
		                	 
		                </div>    
		                <div class="p-name">
		                	${od.title}
		                	<span class="promise411 promise411_11345721" id="promise411_11345721"></span>
		                </div>    
		            </div>
		            <div class="cell p-price"><span class="price">¥<fmt:formatNumber groupingUsed="false"
		            	 value="${od.price / 100}"
		             maxFractionDigits="2" minFractionDigits="2"/> </span></div>
		          
		            <div class="cell p-inventory stock-11345721" style="width:20%">${od.num}</div>
		            
		            <div class="cell p-price" style="width:8%"><span class="price">¥
		            <fmt:formatNumber groupingUsed="false"
		            	 value="${od.num*od.price / 100}"
		             maxFractionDigits="2" minFractionDigits="2"/> </span></div>
		         
		        </div>
	        </div> 
        </c:forEach>
        
    </div><!-- product-list结束 -->
          
        <div class="ui-ceilinglamp-1" style="width: 988px; height: 49px;"><div class="cart-dibu ui-ceilinglamp-current" style="width: 988px; height: 49px;">
          
          
      </div></div>
</div><!-- cart-inner结束 -->
</div>
</div>
<!--推荐位html修改处-->


<script type="text/javascript" src="/js/base-v1.js"></script>
<!-- footer start -->
<jsp:include page="commons/footer.jsp" />
<!-- footer end -->

<!-- 购物车相关业务 -->
<script type="text/javascript" src="/js/cart.js"></script>
<script type="text/javascript" src="/js/jquery.price_format.2.0.min.js"></script>

</html>