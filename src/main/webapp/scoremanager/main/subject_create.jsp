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

			<%-- 画面タイトル --%>
			<h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				科目情報登録
			</h2>

			<%-- 入力フォーム --%>
			<form action="SubjectCreateExecute.action"
				  method="post">

				<div class="row mb-3">

					<%-- 科目コード --%>
					<div class="col-6">

						<label class="form-label"
							   for="subject-cd">

							科目コード
						</label>

						<input type="text"
							   class="form-control"
							   id="subject-cd"
							   name="cd"
							   maxlength="10"
							   value="${cd}" />

						<div class="text-warning mt-1">
							${errors.get("cd")}
						</div>

					</div>

				</div>

				<div class="row mb-4">

					<%-- 科目名 --%>
					<div class="col-6">

						<label class="form-label"
							   for="subject-name">

							科目名
						</label>

						<input type="text"
							   class="form-control"
							   id="subject-name"
							   name="name"
							   maxlength="30"
							   value="${name}" />

						<div class="text-warning mt-1">
							${errors.get("name")}
						</div>

					</div>

				</div>

				<%-- ボタン --%>
				<div>

					<button class="btn btn-primary me-2"
							type="submit">

						登録
					</button>

					<a href="SubjectList.action"
					   class="btn btn-secondary">

						戻る
					</a>

				</div>

			</form>

		</section>

	</c:param>

</c:import>