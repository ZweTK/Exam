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
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				科目管理
			</h2>

			<%-- 新規登録リンク --%>
			<div class="my-2 text-end px-4">
				<a href="SubjectCreate.action">
					新規登録
				</a>
			</div>

			<%-- 科目一覧テーブル --%>
			<table class="table table-hover">

				<tr>
					<th>科目コード</th>
					<th>科目名</th>
					<th></th>
					<th></th>
				</tr>

				<c:forEach var="subject"
					items="${subjects}">

					<tr>

						<%-- 科目コード --%>
						<td>
							${subject.cd}
						</td>

						<%-- 科目名 --%>
						<td>
							${subject.name}
						</td>

						<%-- 変更リンク --%>
						<td>
							<a href="SubjectUpdate.action?cd=${subject.cd}">
								変更
							</a>
						</td>

						<%-- 削除リンク --%>
						<td>
							<a href="SubjectDelete.action?cd=${subject.cd}">
								削除
							</a>
						</td>

					</tr>

				</c:forEach>

			</table>

			<%-- データなし --%>
			<c:if test="${subjects.size() == 0}">

				<div class="px-3">
					科目情報が存在しませんでした。
				</div>

			</c:if>

		</section>

	</c:param>

</c:import>