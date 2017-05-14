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
        </script>
        <script type="text/javascript">
            selected_kurzus = -1;
            selected_tartalom = -1;
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
            function jelentkez(id) {
                $.ajax({
                    type: 'POST',
                    url: '../../rest/user/kurzus/entity/jelentkez',
                    data: {  
                        'kurzus_id': id
                    },
                    success: function(msg){
                        result = JSON.parse(JSON.stringify(msg));
                        showMessage(result["success"],result["result"]);
                        if (result["success"]==1) {
                            $("#jelentkez"+id).html("Jelentkezve");
                            $("#jelentkez"+id).attr("disabled", true);
                        }
                    }
                });
            }
            function lejelentkez(id) {
                                $.ajax({
                    type: 'POST',
                    url: '../../rest/user/kurzus/entity/lejelentkez',
                    data: {  
                        'kurzus_id': id
                    },
                    success: function(msg){
                        result = JSON.parse(JSON.stringify(msg));
                        showMessage(result["success"],result["result"]);
                        if (result["success"]==1) {
                            $("#lejelentkez"+id).html("Lejelentkezve");
                            $("#lejelentkez"+id).attr("disabled", true);
                        }
                    }
                });
            }
            function lejelentkez(id) {
              $.ajax({
                    type: 'POST',
                    url: '../../rest/user/kurzus/entity/lejelentkez',
                    data: {  
                        'kurzus_id': id
                    },
                    success: function(msg){
                        result = JSON.parse(JSON.stringify(msg));
                        showMessage(result["success"],result["result"]);
                        if (result["success"]==1) {
                            $("#lejelentkez"+id).html("Lejelentkezve");
                            $("#lejelentkez"+id).attr("disabled", true);
                        }
                    }
                });
            }
            function kirug(kId, uId) {
                $.ajax({
                    type: 'POST',
                    url: '../../rest/admin/kurzus/entity/kirug',
                    data: {  
                        'kurzus_id': kId,
                        'user_id': uId
                    },
                    success: function(msg){
                        result = JSON.parse(JSON.stringify(msg));
                        showMessage(result["success"],result["result"]);
                        if (result["success"]==1) {
                            $("#user"+kId+"_"+uId).remove();
                            KezelKurzusok();
                        }
                    }
                });
            }
            function cancel() {
                        $("#nev").val("");
                        $("#leiras").val("");
                        $("#maxJelentkezok").val("");
                        $("#indulasDatum").val(new Date().toISOString().split('T')[0]);
                        $("#allapot").val(0);

                        $("#createbutton").show();
                        $("#editbuttons").hide();
                        $("#kurzusheader").html("Kurzus létrehozása");

                        $("#jelentkezok_body").html("");
                        $("#jelentkezok").hide();
                        $("#kurzustartalom").hide();
                        $("#kurzustartalommodify").hide();
                        selected_kurzus = -1;
            }
            function create() {
               $.ajax({
                    type: 'POST',
                    url: '../../rest/admin/kurzus/entity/mentes',
                    data: {  
                        'nev': $("#nev").val(),
                        'leiras': $("#leiras").val(),
                        'maxJelentkezok': $("#maxJelentkezok").val(),
                        'allapot': $("#allapot").val(),
                        'indulasDatum': $("#indulasDatum").val()
                    },
                    success: function(msg){
                        result = JSON.parse(JSON.stringify(msg));
                        if (result["success"]==1) {
                            KezelKurzusok();
                        }
                        showMessage(result["success"],result["result"]);
                    }
                }); 
            }
            function torles() {
                $.ajax({
                    type: 'POST',
                    url: '../../rest/admin/kurzus/entity/delete',
                    data: {
                        'kurzus_id': selected_kurzus
                    },
                    success: function(msg){
                        
                        result = JSON.parse(JSON.stringify(msg));
                        showMessage(result["success"],result["result"]);
                        if (result["success"]==1) {
                            cancel();
                            KezelKurzusok();
                        }
                    }
                });                
            }
            function modify() {
               $.ajax({
                    type: 'POST',
                    url: '../../rest/admin/kurzus/entity/mentes',
                    data: {
                        'kurzus_id': selected_kurzus,
                        'nev': $("#nev").val(),
                        'leiras': $("#leiras").val(),
                        'maxJelentkezok': $("#maxJelentkezok").val(),
                        'allapot': $("#allapot").val(),
                        'indulasDatum': $("#indulasDatum").val()
                    },
                    success: function(msg){
                        result = JSON.parse(JSON.stringify(msg));
                        showMessage(result["success"],result["result"]);
                        if (result["success"]==1) {
                            KezelKurzusok();
                        }
                    }
                }); 
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
            function KezelKurzusok() {
                $("#kurzusok_body").html("");
                httpGetAsync('../../rest/user/kurzus/entity/all',function(data) {
                    for (var i = 0; i < data.length; ++i) {
                       var row = data[i];
                       var letszam = row["kurzusJelentkezokCollection"].length;
                       var kezeles = "<button class='btn btn-default' type='default' onclick='kezeles("+row["kurzusId"]+")'>Kezelés</button>";
                       $("#kurzusok_body").append("<tr><td>"+row["nev"]+"</td><td>"+row["leiras"]+"</td><td>"+row["maxJelentkezok"]+"</td><td>"+letszam+"</td><td>"+kurzusAllapotok[row["allapot"]]+"</td><td>"+new Date(row["indulasDatum"]).toISOString().split('T')[0]+"</td><td>"+new Date(row["letrehozvaDatum"]).toISOString().split('T')[0]+"</td><td>"+kezeles+"</td></tr>");
                   }
                });
            }
            function tartalomKivalaszt(id) {
                $.ajax({
                    type: 'GET',
                    url: '../../rest/admin/kurzustartalom/entity/one',
                    data: { 
                        'tartalom_id': id
                    },
                    success: function(msg){
                        selected_tartalom = id;
                        result = JSON.parse(JSON.stringify(msg));
                        $("#cim").val(result["cim"]);
                        $("#tartalom").val(result["tartalom"]);
                        $("#tcreatebutton").hide();
                        $("#teditbuttons").show();
                        $("#tartalomheader").html("Tartalom módosítása");
                    }
                });
                
            }
            function modifyTartalom() {
                $.ajax({
                    type: 'POST',
                    url: '../../rest/admin/kurzustartalom/entity/mentes',
                    data: { 
                        'tartalom_id': selected_tartalom,
                        'cim': $("#cim").val(),
                        'tartalom': $("#tartalom").val()
                    },
                    success: function(msg){
                        result = JSON.parse(JSON.stringify(msg));
                        showMessage(result["success"],result["result"]);
                    }
                });
                
            }
            function torlesTartalom() {
                $.ajax({
                    type: 'POST',
                    url: '../../rest/admin/kurzustartalom/entity/delete',
                    data: { 
                        'tartalom_id': selected_tartalom
                    },
                    success: function(msg){
                        result = JSON.parse(JSON.stringify(msg));
                        showMessage(result["success"],result["result"]);
                        if (result["success"]==1) {
                            kezeles(selected_kurzus);
                        }
                    }
                });
            }
            function createTartalom() {
                $.ajax({
                    type: 'POST',
                    url: '../../rest/admin/kurzustartalom/entity/mentes',
                    data: { 
                        'kurzus_id': selected_kurzus,
                        'cim': $("#cim").val(),
                        'tartalom': $("#tartalom").val()
                    },
                    success: function(msg){
                        result = JSON.parse(JSON.stringify(msg));
                        showMessage(result["success"],result["result"]);
                        if (result["success"]==1) {
                            kezeles(selected_kurzus);
                        }
                    }
                });
                
            }
            function cancelTartalom() {
                selected_tartalom = -1;
                $("#cim").val("");
                $("#tartalom").val("");
                $("#tartalomheader").html("Tartalom létrehozása");
                $("#tcreatebutton").show();
                $("#teditbuttons").hide();
            }
            function kezeles(id) {
                $.ajax({
                    type: 'GET',
                    url: '../../rest/admin/kurzus/entity/one',
                    data: {  
                        'kurzus_id': id
                    },
                    success: function(msg){
                        selected_kurzus = id;
                        cancelTartalom();
                        result = JSON.parse(JSON.stringify(msg));
                        $("#jelentkezok").hide();
                        $("#kurzustartalom").hide();
                        $("#nev").val(result["nev"]);
                        $("#leiras").val(result["leiras"]);
                        $("#maxJelentkezok").val(result["maxJelentkezok"]);
                        $("#indulasDatum").val(new Date().toISOString().split('T')[0]);
                        $("#allapot").val(result["allapot"]);

                        $("#createbutton").hide();
                        $("#editbuttons").show();
                        $("#kurzustartalommodify").show();
                        $("#kurzusheader").html("Kurzus kezelése");
                        // Load users
                        var jelentkezok = result["kurzusJelentkezokCollection"];
                        $("#jelentkezok_body").html("");
                        if (jelentkezok!=undefined && jelentkezok.length>0) {
                            for (var i=0;i<jelentkezok.length;i++) {
                                var user_id = jelentkezok[i]["kurzusJelentkezokPK"]["userId"];
                                var kirug = "<button class='btn btn-default' type='default' onclick='kirug("+id+","+user_id+")'>Kirúgás</button>";
                                $("#jelentkezok_body").append("<tr id='user"+id+"_"+user_id+"'><td>"+jelentkezok[i]["username"]+"</td><td>"+new Date(jelentkezok[i]["jelentkezesDatum"]).toISOString().split('T')[0]+"</td><td>"+kirug+"</td></tr>");
                            }
                            $("#jelentkezok").show();
                        }
                        //Load tartalom
                        var tartalom = result["kurzusTartalmakCollection"];
                        $("#kurzustartalmak_body").html("");
                            if (tartalom!=undefined && tartalom.length>0) {
                            for (var i=0;i<tartalom.length;i++) {
                                var cim = tartalom[i]["cim"];
                               // var tartalom = tartalom[i]["tartalom"];
                                var letrehozva = new Date(tartalom[i]["letrehozva"]).toISOString().split('T')[0];
                                var tartalomId = tartalom[i]["tartalomId"];
                                var kezel = "<button class='btn btn-default' type='default' onclick='tartalomKivalaszt("+tartalomId+")'>Módosít</button>";
                                $("#kurzustartalmak_body").append("<tr id='tartalom"+tartalomId+"'><td>"+cim+"</td><td>"+letrehozva+"</td><td>"+kezel+"</td></tr>");
                            }
                            $("#kurzustartalom").show();
                        }
                    }
                });
                
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