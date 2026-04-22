<%-- class_create.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts"></c:param>

	<c:param name="content">

	<section class="me-4">

		<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
			クラス新規登録
		</h2>

		<form action="ClassCreateExecute.action" method="post">

			<div class="mb-3 px-4">

				<label class="form-label">
					クラス番号
				</label>

				<input type="text"
					   name="class_num"
					   class="form-control"
					   required>

			</div>

			<div class="px-4">

				<button type="submit" class="btn btn-primary">
					登録
				</button>

				<a href="ClassList.action" class="btn btn-secondary ms-2">
					戻る
				</a>

			</div>

		</form>

	</section>

	</c:param>

</c:import>