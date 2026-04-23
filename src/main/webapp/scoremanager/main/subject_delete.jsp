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
				科目情報削除
			</h2>

			<%-- 更新フォーム --%>
			<form action="SubjectDeleteExecute.action"
				  method="post">

				<div class="row mx-2 mb-3 py-4">

					<%-- 確認メッセージ --%>
					<div class="col-md-12 mb-3">
						<p>「${subject.name}(${subject.cd})を削除してもよろしいでしょうか」 
						</p>
						<input type="hidden" name="cd" value="${subject.cd}">
						<input type="hidden" name="name" value="${subject.name}" >
					</div>
						
					<%-- 削除ボタン --%>
					<div class="col-12 mt-3">

						<button class="btn btn-danger" value="削除">
							削除
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