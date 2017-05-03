/**
 * Created by chock on 2017/4/30.
 */
var atcFunction = (function ($) {

    $(function () {
        var id = window.location.hash;
        var reqData = {
            data: {
                userId: id.substring(9)
            }
        }
        $.ajax({
            url: "/atc/getAtcs",
            contentType: "application/json",
            type: "POST",
            data: JSON.stringify(reqData),
            success: getSuccess,
            error: errCallback
        })

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
                    '<button type="button" class="btn btn-danger" id="delAtc-' + tables[arr].atcId + '" onclick="atcFunction.deleteAtc(event)">删除文章</button></th></tr>';
            }
        }
         $(id).empty();
         $(id).append(str);
    }

    function getSuccess(resp) {
        if (resp.status === "success") {
            atcListTable = JSON.parse(resp.data);
            if (atcListTable != null && atcListTable.length > 0) {
                setCollectionTable(atcListTable, '#atcListTable');
                toastr["success"](resp.message, "成功提示");
            } else {
                toastr["success"](resp.message + "但无相关数据", "成功提示");
            }
        } else {
            toastr["error"](resp.message, "错误提示");
        }
    }

    return {
        /*删除文章*/
        deleteAtc: function (event) {
            var atcId = (event.target.id).substring(7, (event.target.id).length);
            var reqData = {
                data: {
                    atcId: Number(atcId)
                }
            };
            bootbox.confirm({
                title: "请确认",
                message: "确定要删除该文章吗？",
                callback: function () {
                    $.ajax({
                        url: "/atc/delete",
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
        }

    }

})($);