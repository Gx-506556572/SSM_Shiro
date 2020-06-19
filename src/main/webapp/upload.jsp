<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>i-admin 后台管理系统 - 文件上传</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${ctx}/static/lib/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/static/lib/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${ctx}/static/lib/dropzone/basic.css" rel="stylesheet">
    <link href="${ctx}/static/lib/dropzone/dropzone.css" rel="stylesheet">
    <link href="${ctx}/static/css/custom.css" rel="stylesheet">
</head>
<body class="content_col">
<div>
    <div class="x_panel">
        <div class="x_title">
            <h2>文件上传</h2>
            <div class="clear"></div>
        </div>
        <div class="x_content">
            <div class="dropzone" id="my-awesome-dropzone"></div>
        </div>
    </div>
</div>
<script src="${ctx}/static/lib/jquery/jquery.js"></script>
<script src="${ctx}/static/lib/bootstrap/js/bootstrap.js"></script>
<script src="${ctx}/static/lib/layer/layer.js"></script>
<script src="${ctx}/static/lib/dropzone/dropzone.js"></script>
<script src="${ctx}/static/lib/nprogress/nprogress.js"></script>
<script src="${ctx}/static/js/custom.js"></script>
<script>
    $("#my-awesome-dropzone").dropzone({
        url: "${ctx}/common/file/upload",   // 服务器后台的路径
        paramName: "uploadFile", // 设置表单名称
        init: function () {
            this.on("success", function (file, data) {
                console.log(data);
            });
        }
    });
</script>
</body>
</html>
