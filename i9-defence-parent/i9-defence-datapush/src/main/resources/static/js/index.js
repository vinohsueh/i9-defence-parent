$("#save-btn").click(function() {
    var basePath = $("#basePath").val();
    var data = $("#deviceId").val();
    $.ajax({
        url : basePath + "/baseAPI/addDevice.sapi",
        type : "post",
        data : data,
        contentType : "application/json;charset=utf-8",
        success : function(data) {
            alert(data.message);
            if (data.code == 0) {
                window.location.reload()
            }
        }
    });
});

$(".delete-btn").click(function() {
    $("#deleteObey .deviceId").val($(this).attr("data-set"));
    $("#deleteObey").modal("show");
});

$("#delete-btn").click(function() {
    var basePath = $("#basePath").val();
    var data = $("#deleteObey .deviceId").val();
    $.ajax({
        url : basePath + "/baseAPI/deleteDevice.sapi",
        type : "post",
        data : data,
        contentType : "application/json;charset=utf-8",
        success : function(data) {
            alert(data.message);
            if (data.code == 0) {
                window.location.reload()
            }
        }
    });
});