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
				先生一覧
			</h2>

			<%-- 新規登録画面へのリンク --%>
			<div class="my-2 text-end px-4">
				<a href="TeacherCreate.action">新規登録</a>
			</div>

			<%-- 教員データの有無で表示切替 --%>
			<c:choose>

				<%-- 教員情報が1件以上ある場合 --%>
				<c:when test="${teachers.size() > 0}">

					<%-- 件数表示 --%>
					<div class="px-3">
						検索結果：${teachers.size()}件
					</div>

					<%-- 教員一覧テーブル --%>
					<table class="table table-hover">

						<tr>
							<th>教員ID</th>
							<th>氏名</th>
							<th>学校コード</th>
							<th class="text-center">管理者</th>
							<th></th>
						</tr>

						<%-- 教員情報を繰り返し表示 --%>
						<c:forEach var="teacher" items="${teachers}">
							<tr>

								<%-- 教員ID --%>
								<td>${teacher.id}</td>

								<%-- 氏名 --%>
								<td>${teacher.name}</td>

								<%-- 学校コード --%>
								<td>${teacher.school.cd}</td>

								<%-- 管理者権限の有無表示 --%>
								<td class="text-center">
									<c:choose>

										<%-- 管理者の場合 --%>
										<c:when test="${teacher.manage}">
											〇
										</c:when>

										<%-- 一般教員の場合 --%>
										<c:otherwise>
											×
										</c:otherwise>

									</c:choose>
								</td>

								<%-- 更新画面へのリンク --%>
								<td>
									<a href="TeacherUpdate.action?id=${teacher.id}">
										変更
									</a>
								</td>

							</tr>
						</c:forEach>

					</table>

				</c:when>

				<%-- 教員情報が存在しない場合 --%>
				<c:otherwise>
					<div class="px-3">
						先生情報が存在しませんでした。
					</div>
				</c:otherwise>

			</c:choose>

		</section>

	</c:param>

</c:import>