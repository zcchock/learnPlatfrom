/**
 * Created by chock on 2017/4/25.
 */
var userFunction = (function ($) {

    $(function () {
        var reqdata = {
            data: {}
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
                    '<th><button type="button" class="btn btn-info" id="detail-'+ Number(arr) +'" onclick="userFunction.viewUser(event)">查看详情</button>' +
                    '<button type="button" class="btn btn-danger" id="delUser" onclick="userFunction.deleteUser(arr+1)">删除用户</button></th></tr>';
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
                            var account = $("#add-account").val();
                            var name = $("#add-name").val();
                            var sex = $("#add-sex").val();
                            var password = $("#add-password").val();
                            var email = $("#add-email").val();
                            var phone = $("#add-phone").val();

                            // validation here
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
        },
        viewUser: function (event) {
            var userId = (event.target.id).substring(7, (event.target.id).length);
            var reqData = {
                data: {
                    userId: Number(userId) + 1
                }
            };
            var formHtml = $("#user-detail-form").html();
            //初始化文本框的内容
            function initView(user) {
                $("#detail-account").val(user.account);
                $("#detail-name").val(user.name);
                $("#detail-sex").val(user.sex);
                $("#detail-email").val(user.email);
                $("#detail-phone").val(user.phone);
                $("#detail-lastLogin").val(user.lastLoginTime);
            }
            $.ajax({
                url: "/user/getUser",
                contentType: "application/json",
                type: "POST",
                data: JSON.stringify(reqData),
                success: function (resp) {
                    if (resp.status === "success") {
                        var user = JSON.parse(resp.data);
                        bootbox.dialog({
                            message: formHtml,
                            title: "用户详情",
                            buttons: {
                                cancel: {
                                    label: "取消",
                                    className: "btn-default"
                                }
                            }
                        }).init(function () {
                                initView.call(this, user);
                            });
                    } else {
                        toastr["error"](resp.message, "错误提示");
                    }
                },
                error: errCallback
            })
        },
        deleteUser: function (userId) {
            var resData = {
                data: {
                    userId: userId
                }
            };
            bootbox.confirm({
                title: "请确认",
                message: "确定要删除该用户吗？",
                callback: function (result) {
                    if (result) {
                        $.ajax({
                            url: "/user/delete",
                            contentType: "application/json",
                            type: "POST",
                            data: JSON.stringify(resData),
                            success: function (resp) {
                                if (resp.status === 'success') {
                                    toastr["success"](resp.message, "成功提示");
                                } else {
                                    toastr["error"](resp.message, "错误提示");
                                }
                            },
                            error: errCallback
                        });
                    }
                }
            })
        }
    }

})($);