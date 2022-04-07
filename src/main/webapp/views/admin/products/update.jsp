<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="container card">
	<div class="row offset-1 mt-3">
		<h2 class="form-title text-success mt-3">Quản lý sản phẩm</h2>
		<!-- form -->
		<form method="POST" class="row"
			action="/PH14045_HaVietAnh_Assignment/admin/product/update?id=${product.id}">
			<div class="col-5">
				<div class="mt-3">
				<label>Thể loại</label>
					<select name="categoryId" class="form-select">
						<c:forEach items="${ cate }" var="cate">
							<option value="${ cate.id }">${ cate.ten }</option>
						</c:forEach>
					</select>
				</div>
				<div class="mt-3">
					<label for="name"><i
						class="zmdi zmdi-apps"></i> Tên sản phẩm</label> <input value="${product.ten}"
						type="text" name="ten" placeholder="Tên sản phẩm" class="form-control"
						required />
				</div>
				<div class="mt-3">
					<label for="name"><i
						class="zmdi zmdi-filter-list"></i> Số lượng</label> <input value="${product.soLuong}"
						type="number" name="soLuong" placeholder="Số lượng" class="form-control"
						required />
				</div>	
				<div class="mt-3">
					<label for="name"><i
						class="zmdi zmdi-card"></i> Đơn giá</label> <input value="${product.donGia}"
						type="number" name="donGia" placeholder="Đơn giá" class="form-control"
						required />
				</div>	
				<div class="mt-3">
					<label for="name"><i
						class="zmdi zmdi-flower"></i> Màu sắc</label> <input value="${product.mauSac}"
						type="text" name="mauSac" placeholder="Màu sắc" class="form-control"
						required />
				</div>
			</div>
			<div class="col-5 ms-5">
				<div class="mt-3">
					<label for="name"><i
						class="zmdi zmdi-grid"></i> Kích thước</label> <input value="${product.kichThuoc}"
						type="text" name="kichThuoc" placeholder="Kích thước" class="form-control"
						required />
				</div>
				<div class="mt-3 ms-0">
					<label>Sản phẩm hiện tại:</label>
					<br>
					<div class="row col-6">
						<img src="/PH14045_HaVietAnh_Assignment/img/${product.img }"
					alt="sing up image" style="">
					</div>
					<br>
					<label class="mt-3">Sản phẩm mới:</label>
					<input type="file" name="img" placeholder="File" class="form-control" value="${product.img}"
						required />
				</div>
			</div>
			<div class="mt-3 mb-3">
				<button class="btn btn-dark" type="submit">Update</button>
				<a class="btn btn-primary" href="/PH14045_HaVietAnh_Assignment/admin/product/create">Back</a>
			</div>
		</form>
		
	</div>
</div>