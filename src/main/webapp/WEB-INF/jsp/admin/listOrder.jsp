<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>

<script>
    $(function () {
        $("button.orderPageCheckOrderItems").click(function () {
            var orderID = $(this).attr("orderID");
            $("tr.orderPageOrderItemTR[orderID=" + orderID + "]").toggle();
        });
    });

</script>

<title>订单管理</title>

<div class="workingArea">
    <h1 class="label label-info">订单管理</h1>
    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover1  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>状态</th>
                <th>金额</th>
                <th>商品数量</th>
                <th>买家名称</th>
                <th>电话</th>
                <th>租用时长</th>
                <th>发货地址</th>
                <th>创建时间</th>
                <th>支付时间</th>
                <th>入仓时间</th>
                <th>结束时间</th>
                <th width="120px">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${os}" var="o">
                <tr>
                    <td>${o.id}</td>
                    <td>${o.statusDesc}</td>
                    <td>￥<fmt:formatNumber type="number" value="${o.total}" minFractionDigits="2"/></td>
                    <td align="center">${o.totalNumber}</td>
                    <td align="center">${o.user.userName}</td>
                    <td align="center">${o.mobile}</td>
                    <td align="center">${o.duration}个月</td>
                    <td align="center">${o.address}</td>

                    <td>${o.createDate}</td>
                    <td>${o.payDate}</td>
                    <td>${o.confirmDate}</td>
                    <td>${o.endDate}</td>

                    <td>
                        <button orderID=${o.id} class="orderPageCheckOrderItems btn btn-primary btn-xs
                        ">查看详情</button>

                        <c:if test="${o.status=='waitConfirm'}">
                            <a href="admin_order_confirm?id=${o.id}">
                                <button class="btn btn-primary btn-xs">入仓</button>
                            </a>
                        </c:if>
                    </td>
                </tr>
                <tr class="orderPageOrderItemTR" orderID=${o.id}>
                    <td colspan="10" align="center">

                        <div class="orderPageOrderItem">
                            <table width="800px" align="center" class="orderPageOrderItemTable">
                                <c:forEach items="${o.orderItems}" var="oi">
                                    <tr>
                                        <td align="left">
                                            <img width="40px" height="40px"
                                                 src="img/productSingle/${oi.product.firstProductImage.id}.jpg">
                                        </td>

                                        <td>
                                            <a href="admin_product_list?warehouseID=${oi.product.wareHouseID}">
                                                <span>${oi.product.productCode}</span>
                                            </a>
                                        </td>
                                        <td align="right">

                                            <span class="text-muted">${o.duration}个月</span>
                                        </td>
                                        <td align="right">

                                            <span class="text-muted">单价：￥${oi.product.promotePrice}</span>
                                        </td>

                                    </tr>
                                </c:forEach>

                            </table>
                        </div>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>

</div>

<%@include file="../include/admin/adminFooter.jsp" %>