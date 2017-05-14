<%-- 
    Document   : menu
    Created on : 22-Feb-2017, 10:57:50
    Author     : hallgato
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="webclasses.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="../../css/bootstrap.css">
        <link rel="stylesheet" href="../../css/main.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <title>Kurzusjelentkező rendszer</title>
        <script type="text/javascript">
            var kurzusAllapotok = ["Nyitva","Folyamatban","Zárva"];
            var curr_username = "${username}";
            var curr_user_id = ${user_id};
            function httpGetAsync(theUrl, callback) { //theURL or a path to file
                var httpRequest = new XMLHttpRequest();
                httpRequest.onreadystatechange = function() {
                    if (httpRequest.readyState == 4 && httpRequest.status == 200) {
                        var data = JSON.parse(httpRequest.responseText);  //if you fetch a file you can JSON.parse(httpRequest.responseText)
                        if (callback) {
                            callback(data);
                        }                   
                    }
                };

                httpRequest.open('GET', theUrl, true); 
                httpRequest.send(null);
            }
            function kijelentkezes() {
                $.ajax({
                    type: 'GET',
                    url: '../../pages/user/kijelentkezes.html',
                    success: function(){
                        window.location = "../../pages/user/";
                    }
                    });    
            }
            function showMessage(success,msg) {
                    $( ".dynamicboxes" ).hide( "medium", function() {});
                    if (success==1) {
                            $( "#success-box" ).text(msg);
                            $( "#success-box" ).show( "medium", function() {});
                    } else {
                            $("#alertnot").show().delay(5000).fadeOut();
                            $( "#warning-box" ).text(msg);
                            $( "#warning-box" ).show( "medium", function() {});
                    }
            }
        </script>
    </head>
    <body>
        
<div id="custom-bootstrap-menu" style="z-index:50" class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
              <a class="navbar-brand" href="index.html" style="padding-top:12px;">
                <img alt="Logo" src="../../images/logo.png" width="40" height="40">
              </a>
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                    </button>
        </div>
        <div class="navbar-collapse collapse navbar-menubuilder">
                    <ul class="nav navbar-nav navbar-left">
                        <c:forEach var="menuItem" items="${menus}">
                            <li role="presentation"><a href="${menuItem.getResource()}">${menuItem.getName()}</a></li>
                        </c:forEach>
                    </ul>
                    <ul class="nav navbar-nav navbar-right" style="padding-top:10px;padding-right:5px;">
                        <li>Bejelentkezve mint: ${username} <button class='btn-default btn' type='button' onclick='kijelentkezes()'>Kijelentkezés</button></li>
                    </ul>
        </div>
    </div>
</div>
<div class='content'>
    <div class='row'>
             <div id="alertnot" class="alert alert-danger" style="position:fixed;z-index:100;display:none;cursor:hand;left:50px;" onclick="$('html,body').animate({scrollTop: 0},'slow');$(this).hide();" role="alert">
              <span>Hiba!</span>
            </div>
            <div class='col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2'>
                    <div id="warning-box" class="alert alert-danger dynamicboxes text-center" role="alert" style="display:none;"></div>
                    <div id="success-box" class="alert alert-success dynamicboxes text-center" role="alert" style="display:none;"></div>
            </div>
    </div>
</div>