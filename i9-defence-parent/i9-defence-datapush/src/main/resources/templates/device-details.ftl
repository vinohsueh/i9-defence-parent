<#include "header.ftl">
<input type="hidden" name="id" id="id" value="${deviceInfo.id}"/>
<div id="rightContent">
    <a class="toggle-btn" id="nimei">
        <i class="glyphicon glyphicon-align-justify"></i>
    </a>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="stud">
            <div class="check-div form-inline">
                <div class="col-xs-4 col-lg-12 col-md-5">
                    <span style="font-weight: bold; font-size: 15px; margin-left: 10px; margin-right: 10px">${deviceInfo.deviceName}（${deviceInfo.deviceId}）</span>
                    <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#syncDevice">同步数据</button>
                </div>
            </div>
            <div class="data-div">
                <div class="row tableHeader">
                    <div class="col-xs-4">属性名</div>
                    <div class="col-xs-3">值</div>
                    <div class="col-xs-3">上传时间</div>
                    <div class="col-xs-1">操作</div>
                </div>
                <div class="tablebody">
                    <div class="row">
                        <div class="col-xs-4">设备编号</div>
                        <div class="col-xs-3">${deviceInfo.deviceId}</div>
                        <div class="col-xs-3">-</div>
                        <div class="col-xs-1">-</div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4">设备名称</div>
                        <div class="col-xs-3">${deviceInfo.deviceName}</div>
                        <div class="col-xs-3">-</div>
                        <div class="col-xs-1">-</div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4">设备状态</div>
                        <div class="col-xs-3">${deviceInfo.powerStateDesc}</div>
                        <div class="col-xs-3">-</div>
                        <div class="col-xs-1">-</div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4">IMEI</div>
                        <div class="col-xs-3">${deviceInfo.imei}</div>
                        <div class="col-xs-3">-</div>
                        <div class="col-xs-1">-</div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4">创建时间</div>
                        <div class="col-xs-3">${deviceInfo.createDate}</div>
                        <div class="col-xs-3">-</div>
                        <div class="col-xs-1">-</div>
                    </div>
                    <#list values?keys as key>
                    <div class="row">
                        <div class="col-xs-4">${key}</div>
                        <div class="col-xs-3"><#if (values[key].value)??>${values[key].value}<#else>-</#if></div>
                        <div class="col-xs-3"><#if (values[key].updateDate)??>${values[key].updateDate}<#else>-</#if></div>
                        <div class="col-xs-1"><a href="${basePath}/device-datahis-${deviceInfo.id}.shtml?datastream=${values[key].datastream}">历史记录</a></div>
                    </div>
                    </#list>
                </div>
            </div>
        </div>
        <div class="modal fade" id="syncDevice" role="dialog" aria-labelledby="gridSystemModalLabel">
            <input type="hidden" class="deviceId" value=""/>
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="gridSystemModalLabel">提示</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            确认要同步设备数据吗？
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                        <button type="button" class="btn btn-xs btn-danger" id="sync-btn">保 存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}/static/js/details.js"></script>
<#include "footer.ftl">