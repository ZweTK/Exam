<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
	uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

	<%-- タイトル設定 --%>
	<c:param name="title">
		得点管理システム
	</c:param>

	<%-- JavaScript領域（未使用） --%>
	<c:param name="scripts"></c:param>

	<%-- メイン画面内容 --%>
	<c:param name="content">

		<section class="me-4">

			<%-- 見出し --%>
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				学生情報変更
			</h2>

			<%-- 更新フォーム --%>
			<form action="StudentUpdatExecute.action"
				  method="post">

				<div class="row mx-2 mb-3 py-4">

					<%-- 入学年度（変更不可） --%>
					<div class="col-md-12 mb-3">

						<label class="form-label">
							入学年度
						</label>

						<input name="ent_year"
							   type="text"
							   readonly
							   value="${ent_year}"
							   class="form-control border-0">

						<div class="text-warning">
							${entYearError}
						</div>

					</div>

					<%-- 学生番号（変更不可） --%>
					<div class="col-md-12 mb-3">

						<label class="form-label">
							学生番号
						</label>

						<input name="no"
							   type="text"
							   readonly
							   value="${no}"
							   class="form-control border-0">

					</div>

					<%-- 氏名 --%>
					<div class="col-md-12 mb-3">

						<label class="form-label">
							氏名
						</label>

						<input type="text"
							   name="name"
							   value="${name}"
							   class="form-control"
							   placeholder="氏名を入力してください"
							   required>

					</div>

					<%-- クラス選択 --%>
					<div class="col-md-12 mb-3">

						<label class="form-label">
							クラス
						</label>

						<select name="class_num"
								class="form-select">

							<c:forEach var="num"
								items="${class_num_set}">

								<option value="${num}"
									<c:if test="${num == class_num}">
										selected
									</c:if>>

									${num}

								</option>

							</c:forEach>

						</select>

					</div>

					<%-- 在学中チェック --%>
					<div class="mb-3 form-check">

						<input type="checkbox"
							   name="isAttend"
							   value="t"
							   class="form-check-input"
							   id="isAttend"

							   <c:if test="${student.isAttend()}">
								   checked
							   </c:if> />

						<label class="form-check-label"
							   for="isAttend">

							在学中
						</label>

					</div>

					<%-- 更新ボタン --%>
					<div class="col-12 mt-3">

						<button class="btn btn-primary">
							変更
						</button>

					</div>

				</div>

			</form>

			<%-- 戻るリンク --%>
			<div class="mx-4">

				<a href="StudentList.action">
					戻る
				</a>

			</div>

		</section>

	</c:param>

</c:import>