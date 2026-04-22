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

			<%-- 更新完了メッセージ --%>
			<div style="text-align:center;
						background:green;
						color:white;">

				<p>
					変更が完了しました。
				</p>

			</div>

			<%-- 学生一覧へ戻るリンク --%>
			<div class="mx-3">

				<a href="StudentList.action">
					学生一覧
				</a>

			</div>

		</section>

	</c:param>

</c:import>