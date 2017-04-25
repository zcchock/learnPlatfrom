/**
 * Created by chock on 2017/4/5.
 */
var loginFunction = (function ($) {

    function errCallback(resp) {
        toastr["error"]("请求失败", "网络异常");
    }

    return {
        login: function () {
            var inputUser = $('#inputUser').val();
            var inputPassword = $('#inputPassword').val();
            var reqdata = {
                data: {
                    inputUser: inputUser,
                    inputPassword: inputPassword
                }
            }
            toastr["info"]("正在登陆，请稍候。", "提示");
            $.ajax({
                url: "/user/login",
                contentType: "application/json",
                type: "POST",
                data: JSON.stringify(reqdata),
                success: function (resp) {
                    if (resp.status === "success") {
                         var str = JSON.parse(resp.data);
                        window.location.href = str + '.html';
                        toastr["success"](resp.message, "成功提示");
                    } else {
                        toastr["error"](resp.message, "错误提示");
                    }
                },
                // error: errCallback
                error: errCallback
            });
        }
    };
})($);