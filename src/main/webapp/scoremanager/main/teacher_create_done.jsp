<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTLコアタグライブラリを使用 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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

		<%-- 画面全体の余白設定 --%>
		<section class="me-4">

			<%-- 見出し --%>
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				先生情報登録
			</h2>

			<%-- 登録完了メッセージ表示 --%>
			<div style="text-align:center; background:green; color:white;">
			    <p>登録が完了しました。</p>
			</div>

			<%-- 戻るリンクと学生一覧リンク表示 --%>
			<table style="margin-top:30px">

				<tr style="width:100%;">

					<%-- 先生登録画面へ戻る --%>
					<th style="text-align:center">
						<div class="mx-3">
							<a href="TeacherCreate.action">戻る</a>
						</div>
					</th>

					<%-- 学生一覧画面へ移動 --%>
					<th style="text-align:center">
						<div class="mx-3">
							<a href="StudentList.action">学生一覧</a>
						</div>
					</th>

				</tr>

			</table>

		</section>

	</c:param>

</c:import>