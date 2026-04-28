<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
	uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				成績参照
			</h2>

			<div class="px-4 mt-3">

				<!-- ========================= -->
				<!-- FILTER BOX -->
				<!-- ========================= -->
				<div class="row border mx-3 mb-3 py-3 rounded" id="filter">

					<!-- ========================= -->
					<!-- 科目検索フォーム -->
					<!-- ========================= -->
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

							<!-- 検索ボタン -->
							<div class="col-2 text-center">

								<button class="btn btn-secondary w-100">
									検索
								</button>

							</div>

						</div>

					</form>

					<!-- divider -->
					<div class="w-100 border-top my-3"></div>

					<!-- ========================= -->
					<!-- 学生検索フォーム -->
					<!-- ========================= -->
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
									   value="${student.no}"
									   class="form-control border-1"
									   required
									   placeholder="学生番号を入力してください">

							</div>

							<!-- 検索ボタン -->
							<div class="col-2 text-center">

								<button class="btn btn-secondary w-100">
									検索
								</button>

							</div>

						</div>

					</form>

				</div>

				<!-- help text -->
				<p style="color: #00a1e9;">
					科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
				</p>

			</div>

			<!-- エラーメッセージ -->
			<c:forEach var="error" items="${errors}">
				<p class="text-danger px-4">
					${error.value}
				</p>
			</c:forEach>

			<!-- 科目名 -->
			<div class="px-4 mt-3 mb-2">
				科目：${f3.name}
			</div>

			<!-- 成績テーブル -->
			<table class="table table-hover mx-3">

				<tr>
					<th>入学年度</th>
					<th>クラス</th>
					<th>学生番号</th>
					<th>氏名</th>

					<c:forEach var="i" begin="1" end="${maxNo}">
						<th>${i}回</th>
					</c:forEach>

				</tr>

				<c:forEach var="row" items="${testList}">

					<tr>
						<td>${row.entYear}</td>
						<td>${row.classNum}</td>
						<td>${row.studentNo}</td>
						<td>${row.studentName}</td>

						<c:forEach var="i" begin="1" end="${maxNo}">
							<td>
								${empty row.points[i] ? "" : row.points[i]}
							</td>
						</c:forEach>

					</tr>
					
				</c:forEach>

			</table>

		</section>

	</c:param>

</c:import>