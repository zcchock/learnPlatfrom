/**
 * Created by chock on 2017/5/5.
 */
var editor = new Simditor({
    textarea: $('#editor')
});

function errCallback(resp) {
    toastr["error"]("请求失败", "网络异常");
}

var addBlogFunction = (function ($) {
    return {
        addAtc: function () {
            var title = $('#title').val();
            var typeSelect = $('#typeSelect').val();
            var editor = $('#editor').val();
            var reqData = {
                data: {
                    blogAtc: {
                        atcTitle: title,
                        atcType: typeSelect,
                        atcUrl: editor
                    }
                }
            }
            toastr["info"]("正在保存，请稍候。", "提示");
            $.ajax({
                url: "/atc/addAtc",
                contentType: "application/json",
                type: "POST",
                data: JSON.stringify(reqData),
                success: function (resp) {
                    if (resp.status === "success") {
                        toastr["success"](resp.message, "成功提示");
                        window.location.href = "showUser.html";
                    } else {
                        toastr["error"](resp.message, "错误提示");
                    }
                },
                error: errCallback
            });
        }
    }
})($);