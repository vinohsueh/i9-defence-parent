<#include "header.ftl">
<div id="rightContent">
    <a class="toggle-btn" id="nimei">
        <i class="glyphicon glyphicon-align-justify"></i>
    </a>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="stud">
            <div class="check-div form-inline">
                <div class="col-xs-4 col-lg-12 col-md-5">
                    <span style="font-weight: bold; font-size: 15px; margin-left: 10px; margin-right: 10px">${deviceInfo.deviceName}（${deviceInfo.deviceId}）</span>
                    <div style="float:right">
                        <form action="${basePath}/device-datahis-${deviceInfo.id}.shtml" method="post">
                            <input type="hidden" value="${datastream}" name="datastream"/>
                            上传日期：
                            <input type="text" id="sDateInput" name="sDate" style="line-height: 30px;" value="${sDate}">
                            到
                            <input type="text" id="eDateInput" name="eDate" style="line-height: 30px;" value="${eDate}">
                            <input type="submit" style="line-height: 30px; width: 100px !important" value="查询"/>
                        </form>
                    </div>
                </div>
            </div>
            <div class="data-div">
                <div class="row tableHeader">
                    <div class="col-xs-8">属性值</div>
                    <div class="col-xs-2">上传日期</div>
                </div>
                <div class="tablebody">
                    <#list deviceDataHisList as deviceDataHis>
                        <div class="row">
                            <div class="col-xs-8">${deviceDataHis.value}</div>
                            <div class="col-xs-2">${deviceDataHis.createDate}</div>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}/static/laydate/laydate.js"></script>
<script>
laydate.render({
  elem: '#eDateInput'
  ,type: 'datetime'
});
laydate.render({
  elem: '#sDateInput'
  ,type: 'datetime'
});
</script>
<#include "footer.ftl">