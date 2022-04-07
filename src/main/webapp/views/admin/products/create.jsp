<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<div class="container card">
	<div class="row offset-1 mt-3">
		<h2 class="form-title text-success mt-3">Quản lý sản phẩm</h2>
		<!-- form -->
		<form method="POST" class="row"
			action="/PH14045_HaVietAnh_Assignment/admin/product/store">
			<div class="col-5">
				<div class="form-group mt-3">
				<label>Thể loại</label>
					<select name="categoryId" class="form-select">
						<c:forEach items="${ cate }" var="cate">
							<option value="${ cate.id }">${ cate.ten }</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group mt-3">
					<label for="name"><i
						class="zmdi zmdi-apps"></i></label> <input
						type="text" name="ten" placeholder="Tên sản phẩm" class="form-control"
						required />
				</div>
				<div class="form-group mt-3">
					<label for="name"><i
						class="zmdi zmdi-filter-list"></i></label> <input
						type="number" name="soLuong" placeholder="Số lượng" class="form-control"
						required />
				</div>	
				<div class="form-group mt-3">
					<label for="name"><i
						class="zmdi zmdi-card"></i></label> <input
						type="number" name="donGia" placeholder="Đơn giá" class="form-control"
						required />
				</div>	
			</div>
			<div class="col-5 ms-5">
				<div class="form-group mt-3">
					<label for="name"><i
						class="zmdi zmdi-flower"></i></label> <input
						type="text" name="mauSac" placeholder="Màu sắc" class="form-control"
						required />
				</div>	
				<div class="form-group mt-3">
					<label for="name"><i
						class="zmdi zmdi-grid"></i></label> <input
						type="text" name="kichThuoc" placeholder="Kích thước" class="form-control"
						required />
				</div>
				<div class="form-group mt-3">
				<label>Hình ảnh</label>
					<input type="file" name="img" placeholder="File" class="form-control"
						required />
				</div>
				<div class="mt-3">
					<button class="btn btn-dark" type="submit">Thêm</button>
				</div>
			</div>
		</form>
	</div>
	<!-- do data len table -->
	<div class="ms-4 me-4 mt-3">
		<label>Thể loại</label>
			<select name="categoryId" class="form-select">
					<c:forEach items="${ cate }" var="cate">
						<option value="${ cate.id }">${ cate.ten }</option>
					</c:forEach>
			</select>
		<c:if test="${ !empty listSP }">
			<table class="table table-dark table-striped">
				<thead>
					<th>Tên</th>
					<th>Số lượng</th>
					<th>Đơn giá</th>
					<th>Màu sắc</th>
					<th>Kích thước</th>
					<th>Hình ảnh</th>
					<th colspan="2">Thao tác</th>
				</thead>
				<tbody>
					<c:forEach items="${ listSP }" var="pr">
						<tr>
							<td>${pr.ten}</td>
							<td>${pr.soLuong}</td>
							<td>${pr.donGia}</td>
							<td>${pr.mauSac}</td>
							<td>${pr.kichThuoc}</td>
							<td><img src="/PH14045_HaVietAnh_Assignment/img/${pr.img}" style="width: 120px"></td>
							<td><a
								href="/PH14045_HaVietAnh_Assignment/admin/product/edit?id=${pr.id}"
								class="btn btn-primary">Cập nhật</a> <a
								href="/PH14045_HaVietAnh_Assignment/admin/product/delete?id=${pr.id }"
								class="btn btn-danger">Xóa</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</div>