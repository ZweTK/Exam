<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- JSTLコアタグライブラリを使用 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通レイアウト(base.jsp)を読み込み --%>
<c:import url="/common/base.jsp">

	<%-- ページタイトル設定 --%>
	<c:param name="title">
		得点管理システム
	</c:param>

	<%-- JavaScript用領域（今回は未使用） --%>
	<c:param name="scripts"></c:param>

	<%-- メインコンテンツ開始 --%>
	<c:param name="content">

		<section class="me-4">

			<%-- 画面タイトル --%>
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				先生情報変更
			</h2>

			<%-- 更新フォーム開始 --%>
			<form action="TeacherUpdateExecute.action" method="post">

				<div class="row mx-2 mb-3 py-4">

					<%-- 教員ID（変更不可） --%>
					<div class="col-md-12 mb-3">
						<label class="form-label">ID</label>

						<input name="id"
							   type="text"
							   readonly
							   value="${id}"
							   class="form-control border-0">
					</div>

					<%-- パスワード入力欄 --%>
					<div class="col-md-12 mb-3">
						<label class="form-label">パスワード</label>

						<input type="text"
							   name="password"
							   value="${password}"
							   class="form-control"
							   placeholder="パスワードを入力してください"
							   required>
					</div>

					<%-- 氏名入力欄 --%>
					<div class="col-md-12 mb-3">
						<label class="form-label">氏名</label>

						<input type="text"
							   name="name"
							   value="${name}"
							   class="form-control"
							   placeholder="氏名を入力してください"
							   required>
					</div>

					<%-- 学校コード選択欄 --%>
					<div class="col-md-12 mb-3">
						<label class="form-label">学校コード</label>

						<select name="school_num" class="form-select">

							<%-- 学校コード一覧を表示 --%>
							<c:forEach var="num" items="${school_num_set}">
								<option value="${num}"
									<c:if test="${num == school.cd}">selected</c:if>>
									${num}
								</option>
							</c:forEach>

						</select>
					</div>

					<%-- 管理者権限チェックボックス --%>
					<div class="mb-3 form-check">
						<input type="checkbox"
							   name="manage"
							   value="t"
							   class="form-check-input"
							   id="manage"
							   <c:if test="${manage}">checked</c:if> />

						<label class="form-check-label" for="manage">
							管理者
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

			<%-- 一覧画面へ戻るリンク --%>
			<div class="mx-4">
				<a href="TeacherList.action">戻る</a>
			</div>

		</section>

	</c:param>

</c:import>