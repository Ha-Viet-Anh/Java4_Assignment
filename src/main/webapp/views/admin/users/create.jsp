<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<div class="container card">
	<div class="row offset-1 mt-3">
		<h2 class="form-title text-success mt-3">Quản lý tài khoản</h2>
		<!-- form -->
		<form method="POST" class="col-6" name="form_create"
			action="/PH14045_HaVietAnh_Assignment/admin/store">
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
				<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
					type="password" name="password" placeholder="Mật khẩu"
					class="form-control" required />
			</div>
			<div class="form-group mt-3">
				<input type="radio" name="gioiTinh" value="0" class="form-check-input"> Nam
				<input type="radio" name="gioiTinh" value="1" class="form-check-input"> Nữ
			</div>
			<div class="form-group mt-3">
				<label for="re-pass"><i class="zmdi zmdi-phone"></i></label> <input
					type="text" name="sdt" placeholder="Số điện thoại"
					class="form-control" required />
			</div>
			<div class="form-group mt-3">
				<label for="re-pass"><i class="zmdi zmdi-pin"></i></label> <input
					type="text" name="diaChi" placeholder="Địa chỉ"
					class="form-control" required />
			</div>
			<div class="form-group mt-3">
				<input type="radio" name="role" value="0" class="form-check-input"> Người dùng
				<input type="radio" name="role" value="1" class="form-check-input"> Quản lý
			</div>
			<div class="mt-3">
				<button class="btn btn-dark" type="submit">Thêm</button>
			</div>

		</form>
		<div class="col-4 container">
			<img src="/PH14045_HaVietAnh_Assignment/img/signup-image.jpg"
				alt="sing up image">
		</div>
	</div>
	<!-- do data len table -->
	<div class="ms-4 me-4 mt-3">
		<c:if test="${ empty list }">
			<p class="alert alert-warning">Không có dữ liệu</p>
		</c:if>

		<c:if test="${ !empty list }">
			<table class="table table-dark table-striped">
				<thead>
					<th>Họ tên</th>
					<th>Giới tính</th>
					<th>SĐT</th>
					<th>Email</th>
					<th>Vai trò</th>
					<th colspan="2">Thao tác</th>
				</thead>
				<tbody>
					<c:forEach items="${ list }" var="user">
						<tr>
							<td>${ user.hoTen }</td>
							<td><c:choose>
									<c:when test="${ user.gioiTinh == 0 }">Nam</c:when>
									<c:when test="${ user.gioiTinh == 1 }">Nữ</c:when>
									<c:otherwise> - </c:otherwise>
								</c:choose></td>
							<td>${ user.sdt }</td>
							<td>${ user.email }</td>
							<td><c:choose>
									<c:when test="${ user.role == 0 }">Người dùng</c:when>
									<c:when test="${ user.role == 1 }">Quản lý</c:when>
									<c:otherwise> - </c:otherwise>
								</c:choose></td>
							<td><a
								href="/PH14045_HaVietAnh_Assignment/admin/edit?id=${user.id}"
								class="btn btn-primary">Cập nhật</a> <a
								href="/PH14045_HaVietAnh_Assignment/admin/delete?id=${user.id }"
								class="btn btn-danger">Xóa</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</div>