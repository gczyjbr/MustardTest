<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>

<title>编辑仓库</title>

<script>
    $(function () {

        $("#editForm").submit(function () {
            if (!checkEmpty("name", "仓库名称"))
                return false;
            if (!checkEmpty("tiny_stock", "微仓数量"))
                return false;
            if (!checkEmpty("small_stock", "小仓数量"))
                return false;
            if (!checkEmpty("middle_stock", "中仓数量"))
                return false;
            if (!checkEmpty("big_stock", "大仓数量"));
                return false;
            return true;
        });
    });

</script>

<div class="workingArea">

    <ol class="breadcrumb">
        <li><a href="admin_warehouse_list">所有仓库</a></li>
        <li class="active">编辑仓库</li>
    </ol>

    <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑仓库</div>
        <div class="panel-body">
            <form method="post" id="editForm" action="admin_warehouse_update" enctype="multipart/form-data">
                <table class="editTable">
                    <%--<tr>
                        <td>仓库名称</td>
                        <td><input id="name" name="name" value="${w.name}" type="text" class="form-control"></td>
                    </tr>--%>
                    <tr>
                        <td>微仓数量</td>
                        <td><input id="tiny_stock" name="tiny_stock" value="${w.tiny_stock}" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>小仓数量</td>
                        <td><input id="small_stock" name="small_stock" value="${w.small_stock}" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>中仓数量</td>
                        <td><input id="middle_stock" name="middle_stock" value="${w.middle_stock}" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>大仓数量</td>
                        <td><input id="big_stock" name="big_stock" value="${w.big_stock}" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>仓库图片</td>
                        <td>
                            <input id="wareHousePic" accept="image/*" type="file" name="image"/>
                        </td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${w.id}">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>