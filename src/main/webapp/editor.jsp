<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>i-admin 后台管理系统 - 富文本编辑器</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${ctx}/static/lib/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/static/lib/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${ctx}/static/css/custom.css" rel="stylesheet">
</head>

<body class="content_col">
<div class="x_panel">
    <div class="x_title">
        <h2>富文本编辑器</h2>
        <div class="clear"></div>
    </div>
    <div class="x_content">
        <form class="form-horizontal" id="data-form" onsubmit="return false" data-parsley-validate>
            <div class="form-group">
                <textarea id="content" name="content" style="width: 100%;height: 100px"></textarea>
                <br><br>
                <div id="editor"></div>
            </div>
            <div class="ln_solid"></div>
            <div class="form-group">
                <button type="submit" class="btn btn-success">保存</button>
            </div>
        </form>
    </div>
</div>

<script src="${ctx}/static/lib/jquery/jquery.js"></script>
<script src="${ctx}/static/lib/bootstrap/js/bootstrap.js"></script>
<script src="${ctx}/static/lib/layer/layer.js"></script>
<!-- 富文本编辑器 -->
<script src="${ctx}/static/lib/wangeditor/wangEditor.js"></script>
<script type="text/javascript">

    var E = window.wangEditor
    var editor = new E('#editor');

    // 同步更新到 textarea
    editor.customConfig.onchange = function (html) {
        $("#content").val(html);
    };

    // 插入网络图片的回调
    editor.customConfig.linkImgCallback = function (url) {
        console.log(url) // url 即插入图片的地址
    };

    // 下面两个配置，使用其中一个即可显示“上传图片”的tab。但是两者不要同时使用！！！

    //editor.customConfig.uploadImgShowBase64 = true;   // 使用 base64 保存图片
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
    //上传图片的错误提示默认使用`alert`弹出，你也可以自定义用户体验更好的提示方式
    editor.customConfig.customAlert = function (info) {
        // info 是需要提示的内容
        layer.alert(info, {icon: 5, offset: '0px'});
    };
    editor.create();
    // 读取 html
    editor.txt.html();
    // 读取 text
    editor.txt.text();
</script>
</body>
</html>
