<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--获取应用上下文的路径--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>i-admin 后台管理系统 - 新增文章</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${ctx}/static/lib/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/static/lib/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${ctx}/static/lib/dropzone/dropzone.css" rel="stylesheet">
    <link href="${ctx}/static/lib/dropzone/basic.css" rel="stylesheet">
    <link href="${ctx}/static/css/custom.css" rel="stylesheet">
</head>

<body class="content_col">
<div class="x_panel">
    <div class="x_title">
        <h2>新增文章</h2>
        <div class="clear"></div>
    </div>
    <div class="x_content">
        <form class="form-horizontal" id="data-form" onsubmit="return false" data-parsley-validate>
            <div class="form-group">
                <label class="control-label col-md-1 col-sm-1 col-xs-12">
                    标题 <span class="required">*</span>
                </label>
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <input type="text" name="title" class="form-control" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-1 col-sm-1 col-xs-12">
                    封面图片 <span class="required">*</span>
                </label>

                <div class="col-md-10 col-sm-10 col-xs-12">
                    <div class="dropzone" id="div-upload"></div>
                    <input type="text" name="img" id="img">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-1 col-sm-1 col-xs-12">
                    内容 <span class="required">*</span>
                </label>
                <div class="col-md-11 col-sm-11 col-xs-12">
                    <textarea id="content" name="content" style="width: 100%;height: 100px"></textarea>
                    <br><br>
                    <div id="editor"></div>
                </div>
            </div>

            <div class="ln_solid"></div>
            <div class="form-group">
                <div class="col-md-offset-1">
                    <button type="submit" class="btn btn-success">保存</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="${ctx}/static/lib/jquery/jquery.js"></script>
<script src="${ctx}/static/lib/bootstrap/js/bootstrap.js"></script>
<script src="${ctx}/static/lib/layer/layer.js"></script>

<!--表单校验-->
<script src="${ctx}/static/lib/parsleyjs/parsley.js"></script>
<script src="${ctx}/static/lib/parsleyjs/zh_cn.js"></script>

<script src="${ctx}/static/lib/nprogress/nprogress.js"></script>
<!-- 拖拽插件 -->
<script src="${ctx}/static/lib/dropzone/dropzone.js"></script>

<!--js模板插件-->
<script src="${ctx}/static/lib/art-template/template-web.js"></script>
<script src="${ctx}/static/js/custom.js"></script>
<!-- 富文本编辑器 -->
<script src="${ctx}/static/lib/wangeditor/wangEditor.js"></script>

<script type="text/javascript">
    var E = window.wangEditor
    var editor = new E('#editor');
    // 同步更新到 textarea
    editor.customConfig.onchange = function (html) {
        $("#content").val(html);
    };
    editor.customConfig.uploadImgServer = '${ctx}/common/file/uplaodToQiniu';  // 上传图片到服务器
    editor.customConfig.uploadFileName = 'uploadFile'; // 上传文件表单的名称
    editor.customConfig.uploadImgHooks = {
        // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
        customInsert: function (insertImg, data) {
            // 将上传成功的图片插入到富文本编辑器中
            insertImg(data.url);
            if (data.code == 0) {
                layer.msg(data.msg, {icon: 1, time: 1000, offset: '0px'});
            } else {
                layer.alert(data.msg, {icon: 5, offset: '0px'});
            }
        }
    };
    editor.customConfig.customAlert = function (info) {
        // info 是需要提示的内容
        layer.alert(info, {icon: 5, offset: '0px'});
    };
    editor.create();
</script>

<script>
    $("#div-upload").dropzone({
        url: "${ctx}/common/file/uplaodToQiniu",   // 服务器后台的路径
        paramName: "uploadFile", // 设置表单名称
        init: function () {
            this.on("success", function (file, data) {
                console.log(data.url);
                $('#img').val(data.url);
            });
        }
    });
</script>

<script>
    $('#data-form').parsley().on('form:submit', function () {
        $.ajax({
            url: '${ctx}/cms/article/add',
            type: 'post',
            data: $("#data-form").serialize(),
            dataType: 'json',
            success: function (response) {
                if (response.code == 0) {
                    layer.msg(response.msg, {icon: 1, time: 1000, offset: '0px'}, function (index) {
                        layer.alert(response.msg, {icon: 3, offset: '0px'});
                    });
                } else {
                    layer.alert(response.msg, {icon: 5, offset: '0px'});
                }
            }
        })
    });
</script>
</body>
</html>
