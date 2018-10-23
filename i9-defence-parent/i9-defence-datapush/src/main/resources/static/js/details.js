$("#sync-btn").click(function() {
    var basePath = $("#basePath").val();
    var data = $("#id").val();
    $.ajax({
        url : basePath + "/baseAPI/refreshDevice.sapi",
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