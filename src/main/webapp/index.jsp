<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html lang="ch">
<head>
    <title>Bootstrap 模板</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
    <!-- 引入 Bootstrap -->
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/bootstrap-datetime/bootstrap-datetimepicker.min.css" rel="stylesheet" />

    <style type="text/css">
        .xz{
            text-align:center
        }
        #searchInput{
            height: 35px;
            width: 500px;
        }
        #search{
            text-align: center;
        }
        .box{
            position: absolute;
            width: 600px;
            left:45%;
            top:50%;
            margin-left:-200px;
            margin-top:-100px;
            border:1px ;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid dropdown">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">图书管理系统</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-search"></span> 图书检索</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-book"></span> 图书类别</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-th-large"></span> 系统信息</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-list-alt"></span> 借阅历史</a></li>
            <li><a href="/html/login.html"><span class="glyphicon glyphicon-user"></span> 登录/注册</a></li>
        </ul>
    </div>
</nav>

<div class="box">
    <div>
        <div id="search">
            <label>
                <input type="text" id="searchInput">
                <button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-search"></span>查询</button>
            </label>
        </div>

        <div class="xz">
            <label>
                <input type="radio" name="search" value="1" checked>书名</label>
            <label>
                <input type="radio" name="search" value="2">作者</label>
            <label>
                <input type="radio" name="search" value="3">出版社</label>
            <label>
                <input type="radio" name="search" value="4">都行</label>
        </div>
    </div>

</div>

</body>
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/bootstrap-datetime/moment-with-locales.js"></script>
<script src="/bootstrap-datetime/bootstrap-datetimepicker.min.js"></script>
<script src="/bootstrap-datetime/bootstrap-datetimepicker.zh-CN.js"></script>

</html>
