<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- JSTLコアタグライブラリ使用 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通レイアウト読み込み --%>
<c:import url="/common/base.jsp">

	<%-- タイトル設定 --%>
	<c:param name="title">
		得点管理システム
	</c:param>

	<%-- JavaScript領域（未使用） --%>
	<c:param name="scripts"></c:param>

	<%-- メインコンテンツ開始 --%>
	<c:param name="content">

		<section class="me-4">

			<%-- 画面タイトル --%>
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				クラス一覧
			</h2>

			<%-- 新規登録リンク --%>
			<div class="my-2 text-end px-4">
				<a href="ClassCreate.action">
					新規登録
				</a>
			</div>

			<%-- クラスデータ有無判定 --%>
			<c:choose>

				<%-- クラス情報あり --%>
				<c:when test="${list.size() > 0}">

					<%-- 件数表示 --%>
					<div class="px-3">
						検索結果：${list.size()}件
					</div>

					<%-- 一覧テーブル --%>
					<table class="table table-hover">

						<tr>
							<th>クラス番号</th>
							<th></th>
							<th></th>
						</tr>

						<%-- 繰り返し表示 --%>
						<c:forEach var="classNum" items="${list}">
							<tr>

								<%-- クラス番号 --%>
								<td>${classNum}</td>

								<%-- 変更リンク --%>
								<td>
									<a href="ClassUpdate.action?class_num=${classNum}">
										変更
									</a>
								</td>
								<%--ビュー --%>
								<td>
									<a href="ClassView.action?class_num=${classNum}">
										ビュー
									</a>
								</td>

							</tr>
						</c:forEach>

					</table>

				</c:when>

				<%-- データなし --%>
				<c:otherwise>

					<div class="px-3">
						クラス情報が存在しませんでした。
					</div>

				</c:otherwise>

			</c:choose>

		</section>

	</c:param>

</c:import>