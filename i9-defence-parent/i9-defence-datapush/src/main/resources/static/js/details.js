$("#sync-btn").click(function () {
    var basePath = $("#basePath").val();
    var deviceId = $("#id").val();
    $.post(basePath + "/baseAPI/refreshDevice.sapi", {"deviceId": deviceId}, function (data) {
        alert(data.message);
        if (data.code == 0) {
            window.location.reload()
        }
    });
});