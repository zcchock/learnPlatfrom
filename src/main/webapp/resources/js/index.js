/**
 * Created by chock on 2017/4/5.
 */
var userFunction = (function ($) {
    $(function () {
        
    })
    function errCallback(){
        toastr["error"]("请求失败", "网络异常");
    }
    return {
        login:function(){
            var inputUser = $('#inputUser').val()
            var inputPassword = $('#inputPassword').val()
            var reqdata = {
                data:{
                    inputUser:inputUser,
                    inputPassword:inputPassword,
                }
            }
            $.ajax({
                url: "/login",
                contentType: "application/json",
                type: "post",
                data: JSON.stringify(reqdata),
                success: function (resp) {
                    if (resp.status === "success") {
                        //if(resp.data != null && resp.data.length>0){
                        var str = JSON.parse(resp.data);
                        window.location.href=''+str;
                        toastr["success"](resp.message, "成功提示");
                    } else {
                        toastr["error"](resp.message, "错误提示");
                    }
                },
                error: errCallback
            });
        },
        loginUser: function () {
            $.ajax({ url: "test.html",
                context: document.body,
                success: function(){
                $(this).addClass("done");
            }});



            bootbox.dialog({
                message: formHtml,
                title: "新增用户",
                buttons: {
                    cancel: {
                        label: "取消",
                        className: "btn-default"
                    },
                    save: {
                        label: "保存",
                        className: "btn-primary",
                        callback: function () {
                            var username = $("#form-username").val();
                            var password = $("#form-password").val();
                            var nickname = $("#form-nickname").val();
                            var email = $("#form-email").val();
                            var phone = $("#form-phone").val();
                            var mobile = $("#form-mobile").val();

                            // validation here

                            var resData = {
                                "data": {
                                    "username": username,
                                    "password": password,
                                    "nickname": nickname,
                                    "email": email,
                                    "phone": phone,
                                    "mobile": mobile
                                }
                            }
                            toastr["info"]("正在保存，请稍候。", "提示");
                            $.ajax({
                                url: apiPath + "/save",
                                contentType: "application/json",
                                method: "POST",
                                data: JSON.stringify(resData),
                                success: function (resp) {
                                    if (resp.success === 'true') {
                                        toastr["success"](resp.message, "成功提示");
                                        userTable.ajax.reload(null, false);
                                    } else {
                                        toastr["error"](resp.message, "错误提示");
                                    }
                                },
                                error: function (resp) {
                                    toastr["error"]("请求失败", "网络异常");
                                }
                            });
                        }
                    }
                }
            });
        }
    };
})(jQuery);