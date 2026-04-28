<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">

		<section class="me-4">

			<!-- 見出し -->
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				成績参照
			</h2>

			<div class="px-4 mt-3">

				<div class="row border mx-3 mb-3 py-3 rounded" id="filter">

					<!-- ===================== -->
					<!-- 科目検索フォーム（追加） -->
					<!-- ===================== -->
					<form action="TestListSubjectExecute.action"
						  method="get">

						<div class="row align-items-end">

							<div class="col-2">
								<p>科目情報</p>
							</div>

							<!-- 入学年度 -->
							<div class="col-2">
								<label class="form-label">入学年度</label>
								<select class="form-select" name="f1">
									<option value="">--------</option>
									<c:forEach var="year" items="${ent_year_set}">
										<option value="${year}"
											<c:if test="${year==f1}">selected</c:if>>
											${year}
										</option>
									</c:forEach>
								</select>
							</div>

							<!-- クラス -->
							<div class="col-2">
								<label class="form-label">クラス</label>
								<select class="form-select" name="f2">
									<option value="">--------</option>
									<c:forEach var="num" items="${class_num_set}">
										<option value="${num}"
											<c:if test="${num==f2}">selected</c:if>>
											${num}
										</option>
									</c:forEach>
								</select>
							</div>

							<!-- 科目 -->
							<div class="col-3">
								<label class="form-label">科目</label>
								<select class="form-select" name="f3">
									<option value="">--------</option>
									<c:forEach var="sub" items="${subject_set}">
										<option value="${sub.cd}"
											<c:if test="${sub.cd==f3}">selected</c:if>>
											${sub.name}
										</option>
									</c:forEach>
								</select>
							</div>

							<!-- 検索 -->
							<div class="col-2 text-center">
								<button class="btn btn-secondary w-100">検索</button>
							</div>

						</div>

					</form>

					<div class="w-100 border-top my-3"></div>

					<!-- ===================== -->
					<!-- 学生検索フォーム（既存） -->
					<!-- ===================== -->
					<form action="TestListStudentExecute.action"
						  method="get">

						<div class="row align-items-end">

							<div class="col-2">
								<p>学生情報</p>
							</div>

							<!-- 学生番号 -->
							<div class="col-4">
								<label class="form-label">学生番号</label>
								<input name="no"
									   type="text"
									   class="form-control border-1"
									   required
									   placeholder="学生番号を入力してください">
							</div>

							<!-- 検索 -->
							<div class="col-2 text-center">
								<button class="btn btn-secondary w-100">検索</button>
							</div>

						</div>

					</form>

				</div>

			</div>

		</section>

		<div class="px-4 mt-3">

			<!-- エラーメッセージ -->
			<c:if test="${not empty error}">
				<div class="alert alert-danger">
					${error}
				</div>
			</c:if>

			<!-- 学生情報と成績一覧 -->
			<c:if test="${empty error and not empty student}">

				<!-- 学生氏名 -->
				<div class="mb-3">
					氏名：${student.name} (${student.no})
				</div>

				<!-- 成績一覧 -->
				<table class="table table-hover mx-3">

					<tr>
						<th>科目名</th>
						<th>科目コード</th>
						<th>回数</th>
						<th>点数</th>
					</tr>

					<c:forEach var="row" items="${testList}">
						<tr>
							<td>${row.subjectName}</td>
							<td>${row.subjectCD}</td>
							<td>${row.num}</td>
							<td>${row.point}</td>
						</tr>
					</c:forEach>

				</table>

			</c:if>

		</div>

	</c:param>

</c:import>