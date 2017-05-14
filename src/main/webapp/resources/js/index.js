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
                "username":inputUser,
                "password":inputPassword,
                "rememberMe":"0"
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
                        window.location.href =  str;
                        toastr["success"](resp.message, "成功提示");
                    } else 
                        toastr["error"](resp.message, "错误提示");
                },
                error: errCallback
            });
        },

        logout: function () {
            var reqdata = {}
            $.ajax({
                url: "/user/logout",
                contentType: "application/json",
                type: "POST",
                data: JSON.stringify(reqdata),
                success: function () {
                    toastr["success"]("注销成功", "成功提示");
                },
                error: errCallback
            });
        },
        
        register: function () {
            var formHtml = $("#user-register-form").html();
            bootbox.dialog({
                message: formHtml,
                title: "注册",
                buttons: {
                    cancel: {
                        label: "取消",
                        className: "btn-default"
                    },
                    save: {
                        label: "确定",
                        className: "btn-primary",
                        callback: function () {
                            var account = $("#register-account").val();
                            var name = $("#register-name").val();
                            var sex = $("#register-sex").val();
                            var password = $("#register-password").val();
                            var email = $("#register-email").val();
                            var phone = $("#register-phone").val();
                            var resData = {
                                data: {
                                    user: {
                                        account: account,
                                        name: name,
                                        sex: sex,
                                        password: password,
                                        email: email,
                                        phone: phone
                                    }
                                }
                            }
                            toastr["info"]("正在保存，请稍候。", "提示");
                            $.ajax({
                                url: "/user/addUser",
                                contentType: "application/json",
                                method: "POST",
                                data: JSON.stringify(resData),
                                success: function (resp) {
                                    if (resp.status === "success") {
                                        toastr["success"](resp.message, "成功提示");
                                        window.location.reload();
                                    } else {
                                        toastr["error"](resp.message, "错误提示");
                                    }
                                },
                                error: errCallback
                            });
                        }
                    }
                }
            });

        }
        
    };
})($);