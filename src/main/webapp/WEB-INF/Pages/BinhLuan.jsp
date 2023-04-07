<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="node" items="${node.getRepList()}">
    <script> showrepComment('${node.rootBinhLuan.getMaBinhLuan()}', '${node.getNguoiGuiString()}', '${node.getNoiDungString()}', '${node.getNgayDangTimestamp()}', '${node.getMaBinhLuan()}') </script>
    <c:set var="node" value="${node}" scope="request"/>
    <jsp:include page="BinhLuan.jsp"/>
</c:forEach>

</body>
</html>