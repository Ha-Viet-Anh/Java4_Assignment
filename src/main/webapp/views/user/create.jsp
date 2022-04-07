<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<div class="container card">
	<div class="row offset-1 mt-3">
		<h2 class="form-title text-success mt-3">Đăng ký</h2>
		<!-- form -->
		<c:if test="${!empty sessionScope.error }">
			<div class="alert alert-danger">
				${ sessionScope.error }
			</div>
		</c:if>
		<c:if test="${!empty sessionScope.message }">
			<div class="alert alert-success">
				${ sessionScope.message }
			</div>
		</c:if>
		<form method="POST" class="col-6" name="form_create"
			action="/PH14045_HaVietAnh_Assignment/user/store">
			<div class="form-group mt-3">
				<label for="name"><i
					class="zmdi zmdi-account material-icons-name"></i></label> <input
					type="text" name="hoTen" placeholder="Họ tên" class="form-control"
					required />

			</div>
			<div class="form-group mt-3">
				<label for="email"><i class="zmdi zmdi-email"></i></label> <input
					type="email" name="email" placeholder="Email" class="form-control"
					required />
			</div>
			<div class="form-group mt-3">
				<label for="re-pass"><i class="zmdi zmdi-phone"></i></label> <input
					type="text" name="sdt" placeholder="Số điện thoại"
					class="form-control" required />
			</div>
			<div class="form-group mt-3">
				<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
					type="password" name="password" placeholder="Mật khẩu" minlength="6"
					class="form-control" required />
			</div>
			<div class="mt-3">
				<button class="btn btn-dark" type="submit">Đăng ký</button>
			</div>
		</form>
		<div class="col-4 container">
			<img src="/PH14045_HaVietAnh_Assignment/img/signup-image.jpg"
				alt="sing up image">
		</div>
		<div class="offset-4">
			<a href="#">I Forget Password?</a>
		</div>
	</div>
</div>