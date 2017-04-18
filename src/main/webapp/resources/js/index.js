/**
 * Created by chock on 2017/4/5.
 */
var userFunction = (function ($) {

    function errCallback() {
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
            $.ajax({
                url: "/user/login",
                contentType: "application/json",
                type: "post",
                data: JSON.stringify(reqdata),
                success: function (resp) {
                    if (resp.status === "success") {
                        var str = JSON.parse(resp.data);
                        window.location.href = '../WEB-INF/main.html';
                        toastr["success"](resp.message, "成功提示");
                    } else {
                        toastr["error"](resp.message, "错误提示");
                    }
                },
                error: errCallback
            });
        }
    };
})($);