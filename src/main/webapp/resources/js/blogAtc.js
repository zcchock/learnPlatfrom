/**
 * Created by chock on 2017/4/30.
 */
var atcFunction = (function ($) {

    $(function () {
    })

    /*ajax请求通用错误返回*/
    function errCallback(resp) {
        toastr["error"]("请求失败", "网络异常");
    }

    /*数据填充表格*/
    function setCollectionTable(tables, id) {
        var str = "";
        for (arr in tables) {
            if (arr < tables.length) {
                str += '<tr>';
                str += '<th>' + tables[arr].atcId + '</th>' +
                    '<th>' + tables[arr].atcTitle + '</th>' +
                    '<th>' + tables[arr].atcUrl + '</th>' +
                    '<th>' + tables[arr].atcView + '</th>' +
                    '<th><button type="button" class="btn btn-info" id="detail-' + tables[arr].atcId + '" onclick="">查看文章</button>' +
                    '<button type="button" class="btn btn-danger" id="delAtc-' + tables[arr].atcId + '" onclick="">删除文章</button></th></tr>';
            }
        }
        // $(id).empty();
        // $(id).append(str);
    }

    function getSuccess(resp) {
        if (resp.status === "success") {
            console.log(resp.data);
            window.location.href = 'showAtc.html';
            atcListTable = JSON.parse(resp.data);
            if (atcListTable != null && atcListTable.length > 0) {
                setCollectionTable(atcListTable, '#atcListTable')
            } else {
                toastr["success"](resp.message + "无相关的记录存在", "成功提示");
            }
            toastr["success"](resp.message, "成功提示");
        } else {
            toastr["error"](resp.message, "错误提示");
        }
    }

    return {
        getAtcs: function () {
            var userId = (event.target.id).substring(8, (event.target.id).length);
            var reqData = {
                data: {
                    userId: Number(userId)
                }
            };
            $.ajax({
                url: "/atc/getAtcs",
                contentType: "application/json",
                type: "POST",
                data: JSON.stringify(reqData),
                success: getSuccess,
                error: errCallback
            })
        }
    }

})($);