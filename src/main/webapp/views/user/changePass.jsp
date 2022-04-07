<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<div class="container card">
	<div class="row offset-1 mt-3">
		<h2 class="form-title text-success mt-3">Đổi mật khẩu</h2>
		<!-- form -->
		<c:if test="${!empty sessionScope.changeSuccess }">
			<div class="alert alert-success">
				${ sessionScope.changeSuccess }
			</div>
		</c:if>
		<c:if test="${!empty sessionScope.changeError }">
			<div class="alert alert-danger">
				${ sessionScope.changeError }
			</div>
		</c:if>
		<form method="POST" class="col-6" name="form_create"
			action="/PH14045_HaVietAnh_Assignment/user/changePassword">
			<div class="form-group mt-3">
				<label>Tài khoản: ${userDMK.email }</label>
			</div>
			<div class="form-group mt-3">
				<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
					type="password" name="passOld" placeholder="Mật khẩu cũ"
					class="form-control" required />
			</div>
			<div class="form-group mt-3">
				<label for="pass"><i class="zmdi zmdi-lock"></i> Mật khẩu mới:</label> <input
					type="password" name="passNew" placeholder="Mật khẩu mới"
					class="form-control" required />
			</div>
			<div class="form-group mt-3">
				<label for="pass"><i class="zmdi zmdi-lock"></i> Xác nhận mật khẩu mới:</label> <input
					type="password" name="checkPass" placeholder="Xác nhận mật khẩu mới"
					class="form-control" required />
			</div>
			<div class="mt-3">
				<button class="btn btn-dark" type="submit">Xác nhận</button>
				<a class="btn btn-primary" href="/PH14045_HaVietAnh_Assignment/home">Back</a>
			</div>

		</form>
		<div class="col-4 container">
			<img src="/PH14045_HaVietAnh_Assignment/img/signup-image.jpg"
				alt="sing up image">
		</div>
	</div>
</div>