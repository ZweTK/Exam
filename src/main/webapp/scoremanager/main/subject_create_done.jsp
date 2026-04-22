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
				科目情報登録
			</h2>

			<%-- 登録完了メッセージ --%>
			<div style="text-align:center;
						background:green;
						color:white;">

				<p>登録が完了しました。</p>
			</div>

			<%-- 画面遷移リンク --%>
			<table style="margin-top:30px">

				<tr style="width:100%;">

					<%-- 登録画面へ戻る --%>
					<th style="text-align:center">

						<div class="mx-3">
							<a href="SubjectCreate.action">
								戻る
							</a>
						</div>

					</th>

					<%-- 学生一覧へ移動 --%>
					<th style="text-align:center">

						<div class="mx-3">
							<a href="SubjectList.action">
								科目一覧
							</a>
						</div>

					</th>

				</tr>

			</table>

		</section>

	</c:param>

</c:import>