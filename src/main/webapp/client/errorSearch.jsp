<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.LANG}"/>
<fmt:setBundle basename="app" var="lang"/>
<html lang="vi">
<head>
    <title>Error Search</title>
    <link rel="icon" type="image" href="../images/HaLoicon.png"/>
    <jsp:include page="./link/Link.jsp"></jsp:include>
</head>
<body>
<!-- Load page -->
<div id="preloder">
    <div class="loader"></div>
</div>
<jsp:include page="./header/Header.jsp"></jsp:include>
<section id="aa-error">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-error-area">
                    <%--8.12 : Hệ thống hiển thị thông báo “ Từ khóa tìm kiếm không được chứa các kí tự đặc biệt như @#$%^&*():;|}{~!. Vui lòng nhập lại từ khóa để tìm kiếm.”.--%>
                    <h2>Từ khóa không được chứa các ký tự đặc biệt như @#$%^&*():;|}{~!. Vui lòng nhập lại từ khóa để
                        tìm kiếm.</h2>
                    <c:url value="IndexControl" var="indexP"></c:url>
                    <a href="${pageContext.request.contextPath}/${indexP}"><fmt:message
                            key="Back.to.HOME" bundle="${lang}"></fmt:message></a>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="./footer/Footer.jsp"></jsp:include>
</body>
</html>
