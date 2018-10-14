function init() {

}

$("#save-btn").click(function () {
    var basePath = $("#basePath").val();
    var deviceId = $("#deviceId").val();
    $.post(basePath + "/baseAPI/addDevice.sapi", {"deviceId": deviceId}, function (data) {
        alert(data.message);
        if (data.code == 0) {
            window.location.reload()
        }
    });
});

$(".delete-btn").click(function () {
    $("#deleteObey .deviceId").val($(this).attr("data-set"));
    $("#deleteObey").modal("show");
});

$("#delete-btn").click(function () {
    var basePath = $("#basePath").val();
    var deviceId = $("#deleteObey .deviceId").val();
    $.post(basePath + "/baseAPI/deleteDevice.sapi", {"deviceId": deviceId}, function (data) {
        alert(data.message);
        if (data.code == 0) {
            window.location.reload()
        }
    });
});