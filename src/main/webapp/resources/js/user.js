/**
 * Created by chock on 2017/4/25.
 */
var userFunction = (function ($) {

    $(function () {
        var reqdata = {}
        $.ajax({
            url: "/user/list",
            contentType: "application/json",
            type: "POST",
            data: JSON.stringify(reqdata),
            success: getSuccess,
            error: function () {
                
            }
        });
    })

    /*ajax请求通用错误返回*/
    function errCallback(resp) {
        toastr["error"]("请求失败", "网络异常");
    }


    /*用户数据填充表格*/
    function setCollectionTable(tables, id) {
        var str = "";
        for (arr in tables) {
            if (arr < tables.length) {
                str += '<tr>';
                str += '<th>' + tables[arr].userId + '</th>' +
                    '<th>' + tables[arr].account + '</th>' +
                    '<th>' + tables[arr].name + '</th>' +
                    '<th>' + tables[arr].password + '</th>' +
                    '<th><button type="button" class="btn btn-info" id="detail-' + tables[arr].userId + '" onclick="userFunction.viewUser(event)">查看详情</button>' +
                    '<button type="button" class="btn btn-primary" id="update-' + tables[arr].userId + '" onclick="userFunction.updateUser(event)">修改用户</button>' +
                    '<button type="button" class="btn btn-info" id="article-' + tables[arr].userId + '"><a href="./showAtc.html#article-' + tables[arr].userId + '">用户文章</a></button>' +
                    '<button type="button" class="btn btn-danger" id="delUser-' + tables[arr].userId + '" onclick="userFunction.deleteUser(event)">删除用户</button></th></tr>';
            }
        }
        $(id).empty();
        $(id).append(str);
    }


    /*初始化页面时的数据展示*/
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

        /*新增用户*/
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

        /*查看用户详情*/
        viewUser: function (event) {
            var userId = (event.target.id).substring(7, (event.target.id).length);
            var reqData = {
                data: {
                    userId: Number(userId)
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
        
        


        /*查看当前用户详情*/
        viewOwnUser: function () {

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
            var reqdata = {}
            $.ajax({
                url: "/user/getId",
                contentType: "application/json",
                type: "POST",
                data: JSON.stringify(reqdata),
                success: function (resp) {
                    var reqData = {
                        data: {
                            userId: Number(resp.data)
                        }
                    };
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
                }
            })
        },


        /*删除用户*/
        deleteUser: function (event) {
            var userId = (event.target.id).substring(8, (event.target.id).length);
            var reqData = {
                data: {
                    userId: Number(userId)
                }
            };
            bootbox.confirm({
                title: "请确认",
                message: "确定要删除该用户吗？",
                callback: function () {
                    $.ajax({
                        url: "/user/delete",
                        contentType: "application/json",
                        type: "POST",
                        data: JSON.stringify(reqData),
                        success: function (resp) {
                            if (resp.status === 'success') {
                                toastr["success"](resp.message, "成功提示");
                                location.reload(false);
                            } else {
                                toastr["error"](resp.message, "错误提示");
                            }
                        },
                        error: errCallback
                    });
                }
            })
        },

        /*更新用户信息*/
        updateUser: function (event) {
            //初始化文本框的内容
            function initView(user) {
                $("#update-userId").val(user.userId);
                $("#update-name").val(user.name);
                $("#update-password").val(user.password);
                $("#update-sex").val(user.sex);
                $("#update-email").val(user.email);
                $("#update-phone").val(user.phone);
            }

            var userId = (event.target.id).substring(7, (event.target.id).length);
            var reqData = {
                data: {
                    userId: Number(userId)
                }
            };
            var formHtml = $("#user-update-form").html();
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
                            title: "修改用户",
                            buttons: {
                                cancel: {
                                    label: "取消",
                                    className: "btn-default"
                                },
                                save: {
                                    label: "保存",
                                    className: "btn-primary",
                                    callback: function () {
                                        var userId = $("#update-userId").val();
                                        var name = $("#update-name").val();
                                        var password = $("#update-password").val();
                                        var sex = $("#update-sex").val();
                                        var email = $("#update-email").val();
                                        var phone = $("#update-phone").val();
                                        var resData = {
                                            data: {
                                                user: {
                                                    userId: userId,
                                                    name: name,
                                                    password: password,
                                                    sex: sex,
                                                    email: email,
                                                    phone: phone
                                                }
                                            }
                                        }
                                        toastr["info"]("正在保存，请稍候。", "提示");
                                        $.ajax({
                                            url: "/user/update",
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

        /*更新当前用户信息*/
        updateOwnUser: function () {
            
            var formHtml = $("#user-update-form").html();
            //初始化文本框的内容
            function initView(user) {
                $("#update-userId").val(user.userId);
                $("#update-name").val(user.name);
                $("#update-password").val(user.password);
                $("#update-sex").val(user.sex);
                $("#update-email").val(user.email);
                $("#update-phone").val(user.phone);
            }
            var reqdata = {}
            $.ajax({
                url: "/user/getId",
                contentType: "application/json",
                type: "POST",
                data: JSON.stringify(reqdata),
                success: function (resp) {
                    var reqData = {
                        data: {
                            userId: Number(resp.data)
                        }
                    };
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
                                    title: "修改用户",
                                    buttons: {
                                        cancel: {
                                            label: "取消",
                                            className: "btn-default"
                                        },
                                        save: {
                                            label: "保存",
                                            className: "btn-primary",
                                            callback: function () {
                                                var userId = $("#update-userId").val();
                                                var name = $("#update-name").val();
                                                var password = $("#update-password").val();
                                                var sex = $("#update-sex").val();
                                                var email = $("#update-email").val();
                                                var phone = $("#update-phone").val();
                                                var resData = {
                                                    data: {
                                                        user: {
                                                            userId: userId,
                                                            name: name,
                                                            password: password,
                                                            sex: sex,
                                                            email: email,
                                                            phone: phone
                                                        }
                                                    }
                                                }
                                                toastr["info"]("正在保存，请稍候。", "提示");
                                                $.ajax({
                                                    url: "/user/update",
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
                                }).init(function () {
                                    initView.call(this, user);
                                });
                            } else {
                                toastr["error"](resp.message, "错误提示");
                            }
                        },
                        error: errCallback
                    })
                }
            })
        }

    }
})($);