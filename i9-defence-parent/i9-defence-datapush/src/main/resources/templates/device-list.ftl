<#include "header.ftl">
<div id="rightContent">
    <a class="toggle-btn" id="nimei">
        <i class="glyphicon glyphicon-align-justify"></i>
    </a>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="stud">
            <div class="check-div form-inline">
                <div class="col-xs-4 col-lg-12 col-md-5">
                    <span style="font-weight: bold; font-size: 15px; margin-left: 10px; margin-right: 10px">设备列表</span>
                    <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addSource">添加设备</button>
                </div>
            </div>
            <div class="data-div">
                <div class="row tableHeader">
                    <div class="col-xs-2">设备编号</div>
                    <div class="col-xs-2">设备名称</div>
                    <div class="col-xs-2">设备名称</div>
                    <div class="col-xs-1">设备状态</div>
                    <div class="col-xs-2">IMEI</div>
                    <div class="col-xs-2">创建时间</div>
                    <div class="col-xs-1">操作</div>
                </div>
                <div class="tablebody">
                    <#list deviceInfos as deviceInfo>
                        <div class="row">
                            <div class="col-xs-2"><a href="${basePath}/device-details-${deviceInfo.id}.shtml">${deviceInfo.deviceId}</a>
                            </div>
                            <div class="col-xs-2">${deviceInfo.deviceName}</div>
                            <div class="col-xs-2">${deviceGroupResult[deviceInfo.deviceGroupId].groupName}</div>
                            <div class="col-xs-1">${deviceInfo.powerStateDesc}</div>
                            <div class="col-xs-2">${deviceInfo.imei}</div>
                            <div class="col-xs-2">${deviceInfo.createDate}</div>
                            <div class="col-xs-1">
                                <button class="delete-btn btn btn-danger btn-xs" data-set="${deviceInfo.id}"
                                        data-toggle="modal">删除
                                </button>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
            <div class="modal fade" id="deleteObey" role="dialog" aria-labelledby="gridSystemModalLabel">
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
                                确定要删除该设备记录？删除后不可恢复！
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                            <button type="button" class="btn btn-xs btn-danger" id="delete-btn">保 存</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="addSource" role="dialog" aria-labelledby="gridSystemModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="gridSystemModalLabel">添加资源</h4>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <form class="form-horizontal" id="save-device-form">
                                    <div class="form-group ">
                                        <label for="deviceId" class="col-xs-3 control-label">设备编号：</label>
                                        <div class="col-xs-8 ">
                                            <input type="text" name="deviceId" class="form-control input-sm duiqi"
                                                   id="deviceId" placeholder="">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-xs btn-xs btn-white" data-dismiss="modal">取 消</button>
                            <button type="button" class="btn btn-xs btn-xs btn-green" id="save-btn">保 存</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}/static/js/index.js"></script>
<#include "footer.ftl">