/**
 * Created by chock on 2017/5/3.
 */
var atcDetailFunction = (function ($) {

    var id = window.location.hash;
    id = id.substring(8);
    
    $(function () {
        // var id = window.location.hash;
        var reqData = {
            data: {
                atcId: id
            }
        }
        $.ajax({
            url: "/atc/getAtc",
            contentType: "application/json",
            type: "POST",
            data: JSON.stringify(reqData),
            success: getSuccess,
            error: errCallback
        })
    })

    /*数据填充表格*/
    function setContent(obj, body1, body2, body3) {
        var str1 = "";
        var str2 = "";
        var str3 = "";
        str1 += '<h3>' + obj.atcTitle + '</h3>';
        str2 += obj.atcUrl;
        str3 += obj.atcBackup;
        $(body1).append(str1);
        $(body2).append(str2);
        $(body3).append(str3);
    }

    function getSuccess(resp) {
        if (resp.status === "success") {
            atcObj = JSON.parse(resp.data);
            if (atcObj != null) {
                setContent(atcObj, '#title', '#content', '#author');
                toastr["success"](resp.message, "成功提示");
            } else {
                toastr["success"](resp.message + "但无相关数据", "成功提示");
            }
        } else {
            toastr["error"](resp.message, "错误提示");
        }

       /* var editor1 = new Simditor({
            textarea: $('#editor1')
        });*/

    }

    /*ajax请求通用错误返回*/
    function errCallback(resp) {
        toastr["error"]("请求失败", "网络异常");
    }
    
    return{
        addFri:function () {
            var reqData = {
                data: {
                        atcId: id
                }
            }
            toastr["info"]("正在添加，请稍候。", "提示");
            $.ajax({
                url: "/fri/addFri",
                contentType: "application/json",
                type: "POST",
                data: JSON.stringify(reqData),
                success: function (resp) {
                    if (resp.status === "success") {
                        toastr["success"](resp.message, "成功提示");
                    } else {
                        toastr["error"](resp.message, "错误提示");
                    }
                },
                error: errCallback
            });
        },
        addMsg:function () {
            alert();
            var content =  $("#msg").val();
            var reqData = {
                data: {
                    atcId: id,
                    mshContent: content
                }
            }
            toastr["info"]("正在添加，请稍候。", "提示");
            $.ajax({
                url: "/msg/addMsg",
                contentType: "application/json",
                type: "POST",
                data: JSON.stringify(reqData),
                success: function (resp) {
                    if (resp.status === "success") {
                        toastr["success"](resp.message, "成功提示");
                    } else {
                        toastr["error"](resp.message, "错误提示");
                    }
                },
                error: errCallback
            });
        }
        
    }


})($);