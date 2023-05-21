<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	if (session.getAttribute("acc") != null) {

		response.sendRedirect(request.getContextPath() + "/IndexControl");
	}
%>
<fmt:setLocale value="${sessionScope.LANG}" />
<fmt:setBundle basename="app" var="lang" />
<!DOCTYPE html>
<html lang="vi">
<script src='https://www.google.com/recaptcha/api.js'></script>
<link rel="stylesheet"
	  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<head>
	<title><fmt:message key="log.in.to.your.account"
						bundle="${lang}"></fmt:message></title>
	<link rel="icon" type="image" href="../image/HaLoicon.png" />

	<jsp:include page="./link/Link.jsp"></jsp:include>
</head>

<body>

<!-- Load page -->
<div id="preloder">
	<div class="loader"></div>
</div>
<c:url var="login" value="/Login" />
<div class="page">
	<jsp:include page="./header/Header.jsp"></jsp:include>
	<div id="add_succes" style="display: none;">
		<p>
			<i class="fa fa-check fa-2x"></i>
			<fmt:message key="successfully.added.the.product" bundle="${lang}"></fmt:message>
		</p>
	</div>
	<!-- Main Breadcrumb -->
	<div class="main-breadcrumb">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<ol class="breadcrumb">
					</ol>
				</div>
			</div>
		</div>
	</div>
	<!-- End Main Breadcrumb -->
	<!-- Main Content -->
	<div class="main-content">
		<div class="container">
			<div class="row">
				<div class="col-md-5">
					<div class="form-style form-login">
						<form accept-charset="utf-8"
							  action="${login}?${pageContext.request.queryString}"
							  id="customer_login" method="post">
							<input name="FormType" type="hidden" value="customer_login"
								   required /> <input name="utf8" type="hidden" value="true"
													  required />
							<h3 class="form-heading">
								<fmt:message key="log.in" bundle="${lang}"></fmt:message>
							</h3>
							<p class="form-description">
								<fmt:message key="If.you.have.an.account,.please.login."
											 bundle="${lang}"></fmt:message>
							</p>
							<div class="row">
								<div class="col-md-2">
									<p>
										<fmt:message key="account" bundle="${lang}"></fmt:message>
									</p>
								</div>
								<div class="col-md-10">
									<input type="text" value="" name="email" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-2">
									<p>
										<fmt:message key="password" bundle="${lang}"></fmt:message>
									</p>
								</div>
								<div class="col-md-10">
									<input id="password" type="password" value="" name="password" />
									<i class="fas fa-eye" id="toggle-password"
									   style="position: absolute; width: 20%; height: 33%; float: right; left: 80%; top: 21%;"></i>


								</div>
							</div>
							<script type="text/javascript">
								const passwordField = document
										.querySelector("#password");
								const togglePassword = document
										.querySelector("#toggle-password");

								togglePassword
										.addEventListener(
												"click",
												function() {
													const type = passwordField
															.getAttribute("type") === "password" ? "text"
															: "password";
													passwordField
															.setAttribute(
																	"type",
																	type);
													this.classList
															.toggle("hide-password");
												});
							</script>


							<div class="row">
								<p style="color: red;">${error}</p>
							</div>
							<div class="row">
								<div class="col-md-10">
									<button class="btn-cart" type="submit">
										<fmt:message key="log.in" bundle="${lang}"></fmt:message>
									</button>
								</div>
								<div class="col-md-10">
									<a class="btn-cart"
									   href="${pageContext.request.contextPath}/client/Register.jsp"><fmt:message
											key="register" bundle="${lang}"></fmt:message></a>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="col-md-2"></div>
				<div class="col-md-5">
					<div class="form-style form-login">
						<c:url var="foget" value="/ForgetPasswordController" />
						<form accept-charset="utf-8" action="${foget}"
							  id="recover_customer_password" method="post">
							<input name="FormType" type="hidden"
								   value="recover_customer_password" /> <input name="utf8"
																			   type="hidden" value="true" />
							<h3 class="form-heading">
								<fmt:message key="forgot.password" bundle="${lang}"></fmt:message>
							</h3>
							<p class="form-description">
								<fmt:message
										key="You.already.have.an.account.but.don't.remember.the.password."
										bundle="${lang}"></fmt:message>
							</p>
							<p>
								<fmt:message
										key="Please.enter.your.Email.below.and.receive.information.via.Email.to.be.able.to.retrieve.your.password."
										bundle="${lang}"></fmt:message>
							</p>
							<p>
								<fmt:message key="Email" bundle="${lang}"></fmt:message>
							</p>
							<input type="email" value="${emailNot}" name="Email" />
							<button type="submit" class="btn-cart">
								<fmt:message key="send.information" bundle="${lang}"></fmt:message>
							</button>
						</form>
						<div class="row">
							<p style="color: red;">${errorForget}</p>
						</div>
						<div class="row">
							<p style="color: green;">${sucForget}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Main Content -->
	<jsp:include page="./footer/Footer.jsp"></jsp:include>
</div>

</body>

</html>