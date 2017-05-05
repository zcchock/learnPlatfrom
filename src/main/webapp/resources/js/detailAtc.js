/**
 * Created by chock on 2017/5/3.
 */
var atcDetailFunction = (function ($) {

    $(function () {
        var id = window.location.hash;
        var reqData = {
            data: {
                atcId: id.substring(8)
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
    function setContent(obj, body1, body2) {
        var str1 = "";
        var str2 = "";
        str1 += '<h3>' + obj.atcTitle + '</h3>';
        str2 += '<input type="text" value="  '+   obj.atcUrl  +  '></input> ';

        $(body1).empty();
        $(body2).empty();
        $(body1).append(str1);
        $(body2).append(str2);
    }

    function getSuccess(resp) {
        if (resp.status === "success") {
            atcObj = JSON.parse(resp.data);
            if (atcObj != null) {
                setContent(atcObj, '#titleBody', '#contentBody');
                toastr["success"](resp.message, "成功提示");
            } else {
                toastr["success"](resp.message + "但无相关数据", "成功提示");
            }
        } else {
            toastr["error"](resp.message, "错误提示");
        }
    }

    /*ajax请求通用错误返回*/
    function errCallback(resp) {
        toastr["error"]("请求失败", "网络异常");
    }


})($);