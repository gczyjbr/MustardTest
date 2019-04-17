<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>

<script>
    $(function () {

        $("#addForm").submit(function () {
            if (!checkEmpty("name", "仓库名称"))
                return false;
            if (!checkEmpty("address", "仓库地址"))
                return false;
            if (!checkInt("tinyStock", "微仓数量"))
                return false;
            if (!checkInt("smallStock", "小仓数量"))
                return false;
            if (!checkInt("middleStock", "中仓数量"))
                return false;
            if (!checkInt("bigStock", "大仓数量"))
                return false;
            if (!checkEmpty("wareHousePic", "仓库图片"))
                return false;

            return true;
        });
    });

</script>

<title>仓库管理</title>

<div class="workingArea">
    <h1 class="label label-info">仓库管理</h1>
    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>图片</th>
                <th>仓库名称</th>
                <th>仓库地址</th>
                <th>库存数量</th>
                <th>属性管理</th>
                <th>产品管理</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ws}" var="w">

                <tr>
                    <td>${w.id}</td>
                    <td><img height="40px" src="img/warehouse/${w.id}.jpg"></td>
                    <td>${w.name}</td>
                    <td>${w.address}</td>
                    <td>
                        微仓:${w.tiny_stock}&nbsp;
                        小仓:${w.small_stock}&nbsp;
                        中仓:${w.middle_stock}&nbsp;
                        大仓:${w.big_stock}
                    </td>

                    <td><a href="admin_property_list?warehouseID=${w.id}"><span
                            class="glyphicon glyphicon-th-list"></span></a>
                    </td>
                    <td><a href="admin_product_list?warehouseID=${w.id}"><span
                            class="glyphicon glyphicon-shopping-cart"></span></a>
                    <td><a href="admin_warehouse_edit?id=${w.id}"><span class="glyphicon glyphicon-edit"></span></a>
                    </td>
                    <td><a deleteLink="true" href="admin_warehouse_delete?id=${w.id}"><span
                            class="   glyphicon glyphicon-trash"></span></a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>

    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增仓库</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="admin_warehouse_add" enctype="multipart/form-data">
                <table class="addTable">
                    <tr>
                        <td>仓库名称</td>
                        <td><input id="name" name="name" type="text" class="form-control"></td>
                    </tr>

                    <tr>
                        <td>微型库存</td>
                        <td><input id="tinyStock" name="tiny_stock" type="text" class="form-control"></td>
                    </tr>

                    <tr>
                        <td>小型库存</td>
                        <td><input id="smallStock" name="small_stock" type="text" class="form-control"></td>
                    </tr>

                    <tr>
                        <td>中型库存</td>
                        <td><input id="middleStock" name="middle_stock" type="text" class="form-control"></td>
                    </tr>

                    <tr>
                        <td>大型库存</td>
                        <td><input id="bigStock" name="big_stock" type="text" class="form-control"></td>
                    </tr>

                    <tr>
                        <td>仓库地址</td>
                        <td><input id="address" name="address" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>仓库图片</td>
                        <td>
                            <input id="wareHousePic" accept="image/*" type="file" name="image"/>
                        </td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<%@include file="../include/admin/adminFooter.jsp" %>