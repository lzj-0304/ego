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
   <title>订单列表 - 易购商城</title>
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
		<h3>购物车列表</h3>
	</div>
	<div id="show">
	
<div class="cart-frame">
    <div class="tl"></div>
    <div class="tr"></div>
</div>
<div class="cart-inner">
     	<div class="cart-thead clearfix" style="width: 100%">
		      
		        <div class="column t-price"  style="width: 15 %">订单号</div>
		        <div class="column t-price"  style="width: 15%">订单金额</div>
		        <div class="column t-promotion"  style="width: 18%">订单状态</div>
		        <div class="column t-inventory"  style="width: 15%">订单日期</div>
		  
		        <div class="column t-quantity" style="width: 15%">
		        	<a href="">查看</a>
		        </div>
	    	</div>
    <div id="product-list" class="cart-tbody">
        <!-- ************************商品开始********************* -->
 
        <c:forEach items="${orderList}" var="o">
	       	<div class="cart-thead clearfix" style="width: 100%">
		      
		        <div class="column t-price"  style="width: 15%">${o.orderId }</div>
		        <div class="column t-price"  style="width: 15%">${o.payment }</div>
		        <!-- 1、未付款，2、已付款，3、未发货，4、已发货 -->
		        <div class="column t-promotion"  style="width: 18%">
		        	<c:if test="${o.status ==1}">未付款</c:if>
		        	<c:if test="${o.status ==2}">已付款</c:if>
		        	<c:if test="${o.status ==3}">未发货</c:if>
		        	<c:if test="${o.status ==4}">已发货</c:if>
		        	
		        </div>
		        <div class="column t-inventory"  style="width: 15%">${o.createTime }</div>
		     
		        <div class="column t-quantity" style="width: 15%">
		        	<a href="/order/detail/list/${o.orderId }">查看</a>
		        </div>
	    	</div>
        </c:forEach>
        
    </div>
    <!-- product-list结束 -->
      
        
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
