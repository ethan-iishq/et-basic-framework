<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title>公安部消防局系统运维管理平台 - 登录</title>
    <meta name="keywords" content="公安部，消防局，运维，系统">
    <meta name="description" content="公安部消防局系统运维管理平台">
    <link href="${request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${request.contextPath}/static/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${request.contextPath}/static/css/animate.min.css" rel="stylesheet">
    <link href="${request.contextPath}/static/css/style.min.css" rel="stylesheet">
    <link href="${request.contextPath}/static/css/login.min.css" rel="stylesheet">
    <script>
        if(window.top!==window.self){window.top.location=window.location};
    </script>

</head>

<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>运维管理系统</h1>
                    </div>
                    <div class="m-b"></div>
                    <h4>团结、拼搏、务实、高效</h4>
                   <!--  <ul class="m-b">
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i></li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i></li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i></li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i></li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i></li>
                    </ul> -->
                    <!--  <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong> -->
                </div>
            </div>
            <div class="col-sm-5">
                <form method="post" onSubmit="return false;" id="loginFormId">
                    <h4 class="no-margins">登录</h4>
                    <!-- <p class="m-t-md">登录到公安部消防局系统运维管理平台</p> -->
                    <input id = "login" name="login" type="text" class="form-control uname" placeholder="账号" />
                    <input id = "password" name="password" type="password" class="form-control pword m-b" placeholder="密码" />
                    <!-- <a href="#">忘记密码了？</a> -->
                    <input value="1" id="rememberMeChbox" name="rememberMeCode" type="checkbox"><span class="text-info">记住我一周</span>
                    <button type = "submit" class="btn btn-success btn-block" id="loginBtnId">登录</button>
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2016 All Rights Reserved. 公安部沈阳消防研究所
            </div>
        </div>
    </div>
    <script src="${request.contextPath}/static/js/jquery.min.js"></script>
    <script src="${request.contextPath}/static/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${request.contextPath}/static/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${request.contextPath}/static/js/demo/form-validate-demo-login-ethan.js"></script>
    
    <script>
    $(document).ready(function(){
    	
    	$("#loginBtnId").on("click", function(){
    		
    		if(!$("#loginFormId").valid()){
    			return;
    		}
    		var loginInfo = $("#loginFormId").serialize();
    		var username = $("#login").val();
    		var password = $("#password").val();

    		$.post("/handle_login", loginInfo, function(data){
    			console.log(data);
    			console.log(data.status);
    			if(data.status){
    				window.location.href = data.link
    			}else{
    				alert("用户帐号或密码错误！")
    			}
    		}).error(function(){
    			alert("无法登陆，请联系系统管理员");
    		});
    	});
    	/*
    		if(this.username.length == 0 || this.password.length==0){
    			return false
    		}
    		this.$http.post("/handle_login", {'login':this.username, 'password':this.password}).then(function(response){
                console.log(response.data.status)
                if(response.data.status == true){
                	window.location.href = response.data.link
                }else{
                	alert("用户帐号或密码错误！")
                }
                
            }).catch(function(){
                alert("获取服务器端数据错误")
            })
    	*/
    });
    
    </script>
</body>
</html>