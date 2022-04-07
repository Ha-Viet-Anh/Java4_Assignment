<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<div class="container card">
	<div class="row offset-2 mt-3">
		<h2 class="form-title text-success">Quản lý danh mục</h2>
		<!-- form -->
		<form method="POST" class="col-6" name="form_create"
			action="/PH14045_HaVietAnh_Assignment/admin/category/store">
			<div class="form-group mt-3">
				<label for="name"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-lines-fill" viewBox="0 0 16 16">
			  <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5 6s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zM11 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm.5 2.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1h-4zm2 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2zm0 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2z"/>
			</svg> ${user.hoTen}</label>
			</div>
			<div class="form-group mt-3">
				<label for="name"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-card-checklist" viewBox="0 0 16 16">
			  <path d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h13zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
			  <path d="M7 5.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0zM7 9.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 0 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0z"/>
			</svg> Tên danh mục</label> <input type="text"
					name="ten" placeholder="Name" class="form-control" required />
			</div>
			<div class="mt-3">
				<button class="btn btn-dark" type="submit">Thêm</button>
			</div>
		</form>
		<div class="col-4 container">
			<img src="/PH14045_HaVietAnh_Assignment/img/signup-image.jpg"
				alt="sing up image">
		</div>
		<div class="col-9 mt-3">
			<c:if test="${ !empty listCate }">
				<table class="table table-striped">
					<thead>
						<th>STT</th>
						<th>Tên</th>
						<th>Thao tác</th>
					</thead>
					<tbody>
						<c:forEach items="${ listCate }" var="cate">
							<tr>
								<td>${cate.id}</td>
								<td>${cate.ten}</td>
								<td><a 
									href="/PH14045_HaVietAnh_Assignment/admin/category/edit?id=${cate.id}"
									class="btn btn-primary">Cập nhật</a> 
									<button type="button" data-bs-target="#cate${cate.id }" class="btn btn-danger" 
									data-bs-toggle="modal" data-bs-target="#exampleModal">
								  Xóa
								</button></td>
									
									
									<!-- Button trigger modal -->
								
								
								<!-- Modal -->
								<div class="modal fade" id="cate${cate.id }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLabel">Xóa danh mục</h5>
								        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								      </div>
								      <div class="modal-body">
								        Xóa không thể khôi phục lại!
								      </div>
								      <div class="modal-footer">
								        <a href="/PH14045_HaVietAnh_Assignment/admin/category/delete?id=${cate.id }"
											class="btn btn-primary">Xác nhận</a>
								        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
								      </div>
								    </div>
								  </div>
								</div>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</div>