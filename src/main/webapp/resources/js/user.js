/**
 * Created by chock on 2017/4/25.
 */
var userFunction = (function ($) {

    $(function () {
        var reqdata = {
            data:{
            }
        }
        $.ajax({
            url: "/user/list",
            contentType: "application/json",
            type: "POST",
            data: JSON.stringify(reqdata),
            success: getSuccess,
            error: errCallback
        });
    })

    function errCallback(resp) {
        toastr["error"]("请求失败", "网络异常");
    }

    function setCollectionTable(tables, id) {
        var str = "";
        for (arr in tables) {
            if (arr < tables.length) {
                str += '<tr>';
                str += '<th>' + tables[arr].userId + '</th>' +
                    '<th>' + tables[arr].account + '</th>' +
                    '<th>' + tables[arr].name + '</th>' +
                    '<th>' + tables[arr].password + '</th>' +
                    '<th><button type="button" class="btn btn-default" id="detail">查看详情</button></th></tr>';
            }
        }
        $(id).empty();
        $(id).append(str);
    }

    function getSuccess(resp) {
        if (resp.status === "success") {
            userListTable = JSON.parse(resp.data);
            if (userListTable != null && userListTable.length > 0) {
                setCollectionTable(userListTable, '#userListTable')
            } else {
                toastr["success"](resp.message + "无相关的记录存在", "成功提示");
            }
            toastr["success"](resp.message, "成功提示");
        } else {
            toastr["error"](resp.message, "错误提示");
        }
    }

    //Functions
    return {
        addUser: function () {
            var formHtml = $("#user-add-form").html();
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
                            var account = $("#form-account").val();
                            var name = $("#form-name").val();
                            var sex = $("#form-sex").val();
                            var password = $("#form-password").val();
                            var email = $("#form-email").val();
                            var phone = $("#form-phone").val();

                            // validation here
                            var resData = {
                                data: {
                                    account: account,
                                    name: name,
                                    sex: sex,
                                    password: password,
                                    email: email,
                                    phone: phone
                                }
                            }
                            toastr["info"]("正在保存，请稍候。", "提示");
                            $.ajax({
                                url: "/user/addUser",
                                contentType: "application/json",
                                method: "POST",
                                data: JSON.stringify(resData),
                                success: function (resp) {
                                    if (resp.success === 'true') {
                                        toastr["success"](resp.message, "成功提示");
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
    }

})($);