<#include "header.ftl">
<style>
.data-item-ul {
    list-style: none;
}

.data-item-ul .data-item-li1 {
    float: left;
    border: 1px solid #808080;
    margin-right: 5px;
    width: 160px;
    text-align: center;
    height: 50px;
    line-height: 50px;
    font-weight: bold;
    font-size: 15px;
    border-radius: 5px;
}

.data-item-ul .data-item-li1 a {
    color: #ffffff;
}

.data-item-ul .data-item-li0 {
    float: left;
    border: 1px solid #808080;
    border-radius: 5px;
    font-size: 10px;
    width: 75px;
    height: 30px;
    line-height: 30px;
    margin-left:5px;
    margin-top:20px;
    text-align: center;
}
</style>
<div id="rightContent">
    <a class="toggle-btn" id="nimei">
        <i class="glyphicon glyphicon-align-justify"></i>
    </a>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="stud">
            <div class="check-div form-inline">
                <div class="col-xs-4 col-lg-12 col-md-5">
                    <span style="font-weight: bold; font-size: 15px; margin-left: 10px; margin-right: 10px; float: left;">项目名称：中新生态城XX</span>
                    <ul class="data-item-ul" style="float:right">
                    <#list powerStates as powerState>
                        <li class="data-item-li0" style="background-color:${powerState.color}">${powerState.desc}</li>
                    </#list>
                    </ul>
                </div>
            </div>
            <div class="data-div">
                <ul class="data-item-ul">
                    <#list deviceInfos as deviceInfo>
                        <li class="data-item-li1" style="background-color:${deviceInfo.powerState0.color}">
                            <a href="${basePath}/device-details-${deviceInfo.id}.shtml">${deviceInfo.deviceName}</a>
                        </li>
                    </#list>
                </ul>
            </div>
            <div style="clear: both"></div>
            <div class="form-inline" style="margin-top: 10px;">
                <div class="col-xs-4 col-lg-12 col-md-5">
                    <ul class="data-item-ul" style="float:right">
                    <#list powerStateResult?keys as itemKey>
                        <li class="data-item-li0">${itemKey}（${powerStateResult[itemKey]}）</li>
                    </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}/static/js/index.js"></script>
<#include "footer.ftl">