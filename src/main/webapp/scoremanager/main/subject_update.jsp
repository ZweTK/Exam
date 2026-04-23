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
				科目変更
			</h2>

			<%-- 更新フォーム --%>
			<form action="SubjectUpdateExecute.action"
				  method="post">

				<div class="row mx-2 mb-3 py-4">

					<%-- 科目コード（変更不可） --%>
					<div class="col-md-12 mb-3">

						<label class="form-label">
							科目コード
						</label>

						<input name="cd"
							   type="text"
							   readonly
							   value="${subject.cd}"
							   class="form-control border-0">

					</div>

					<%-- 科目名 --%>
					<div class="col-md-12 mb-3">

						<label class="form-label">
							科目名
						</label>

						<input type="text"
							   name="name"
							   value="${subject.name}"
							   class="form-control"
							   maxlength="20"
							   placeholder="科目名を入力してください"
							   required>

						<div class="text-warning">
							${errors.name}
						</div>

					</div>

					<%-- 変更ボタン --%>
					<div class="col-12 mt-3">

						<button class="btn btn-primary">
							変更
						</button>

					</div>

				</div>

			</form>

			<%-- 戻るリンク --%>
			<div class="mx-4">

				<a href="SubjectList.action">
					戻る
				</a>

			</div>

		</section>

	</c:param>

</c:import>