<%-- メニュー画面JSP --%>

<%@page import="bean.Teacher"%>
<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
	uri="jakarta.tags.core"%>

<%
	// セッションからログイン中ユーザー取得
	Teacher teacher =
		(Teacher) session.getAttribute("user");
%>

<c:import url="/common/base.jsp">

	<%-- タイトル設定 --%>
	<c:param name="title">
		得点管理システム
	</c:param>

	<%-- JavaScript領域（未使用） --%>
	<c:param name="scripts"></c:param>

	<%-- メインコンテンツ --%>
	<c:param name="content">

		<section class="me-4">

			<%-- 見出し --%>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
				メニュー
			</h2>

			<%-- 1行目メニュー --%>
			<div class="row text-center px-4 fs-3 my-5">

				<%-- 学生管理 --%>
				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #dbb;">

					<a href="StudentList.action">
						学生管理
					</a>
				</div>

				<%-- 成績管理 --%>
				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #bdb;">

					<div>
						<div>成績管理</div>

						<div>
							<a href="TestRegist.action">
								成績登録
							</a>
						</div>

						<div>
							<a href="TestList.action">
								成績参照
							</a>
						</div>
					</div>
				</div>

				<%-- 科目管理 --%>
				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #bbd;">

					<a href="SubjectList.action">
						科目管理
					</a>
				</div>

				<%-- クラス管理 --%>
				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #ddb;">

					<a href="ClassList.action">
						クラス管理
					</a>
				</div>

			</div>

			<%-- 2行目メニュー --%>
			<div class="row text-center px-4 fs-3 my-5">

				<%-- CSVアップロード --%>
				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #dbd;">

					<a href="Csv.action">
						CSVアップロード
					</a>
				</div>

				<%-- 管理者のみ表示：先生管理 --%>
				<% if (teacher != null && teacher.isManage()) { %>

				<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #dbe;">

					<a href="TeacherList.action">
						先生管理
					</a>
				</div>

				<% } %>

			</div>

		</section>

	</c:param>

</c:import>