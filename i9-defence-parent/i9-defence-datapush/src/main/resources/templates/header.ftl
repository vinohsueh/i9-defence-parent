<!doctype html>
<html lang="ch">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <title>烟感接入平台</title>
    <script src="${basePath}/static/js/jquery.min.js"></script>
    <script src="${basePath}/static/js/bootstrap.min.js"></script>
    <!--[if lt IE 9]>
    <script src="${basePath}/static/js/html5shiv.min.js"></script>
    <script src="${basePath}/static/js/respond.min.js"></script>
    <![endif]-->
    <link href="${basePath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/slide.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/flat-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/jquery.nouislider.css">
</head>

<body>
<input type="hidden" name="basePath" id="basePath" value="${basePath}"/>
<div id="wrap">
    <div class="leftMeun" id="leftMeun">
        <div id="logoDiv">
            <p id="logoP"><img id="logo" src="${basePath}/static/images/logo.png"><span>烟感接入平台</span></p>
        </div>
        <div class="meun-item meun-item-active"><img
                src="${basePath}/static/images/icon_source.png"><a href="${basePath}/index.shtml">启动器</a>
        </div>
        <div class="meun-item meun-item-active"><img
                src="${basePath}/static/images/icon_source.png"><a href="${basePath}/device-list.shtml">设备管理</a>
        </div>
    </div>