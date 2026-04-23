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
				成績管理
			</h2>

			<%-- 検索フォーム --%>
			<form action="TestRegist.action" method="get">

				<div class="row border mx-3 mb-3 py-3 align-items-end rounded"
					 id="filter">

					<%-- 入学年度 --%>
					<div class="col-3">

						<label class="form-label"
							   for="score-f1-select">
							入学年度
						</label>

						<select class="form-select"
								id="score-f1-select"
								name="f1">

							<option value="">
								--------
							</option>

							<c:forEach var="year"
								items="${entYearSet}">

								<option value="${year}"
									<c:if test="${year==f1}">
										selected
									</c:if>>

									${year}

								</option>

							</c:forEach>

						</select>

					</div>

					<%-- クラス --%>
					<div class="col-3">

						<label class="form-label"
							   for="score-f2-select">
							クラス
						</label>

						<select class="form-select"
								id="score-f2-select"
								name="f2">

							<option value="">
								--------
							</option>

							<c:forEach var="num"
								items="${classList}">

								<option value="${num}"
									<c:if test="${num==f2}">
										selected
									</c:if>>

									${num}

								</option>

							</c:forEach>

						</select>

					</div>

					<%-- 科目 --%>
					<div class="col-3">

						<label class="form-label"
							   for="score-f3-select">
							科目
						</label>

						<select class="form-select"
								id="score-f3-select"
								name="f3">

							<option value="">
								--------
							</option>

							<c:forEach var="sub"
								items="${subjectList}">

								<option value="${sub.cd}"
									<c:if test="${sub.cd==f3}">
										selected
									</c:if>>

									${sub.name}

								</option>

							</c:forEach>

						</select>

					</div>

					<%-- 回数 --%>
					<div class="col-2">

						<label class="form-label"
							   for="score-f4-select">
							回数
						</label>

						<select class="form-select"
								id="score-f4-select"
								name="f4">

							<option value="">
								--------
							</option>

							<option value="1"
								<c:if test="${f4==1}">
									selected
								</c:if>>
								1
							</option>

							<option value="2"
								<c:if test="${f4==2}">
									selected
								</c:if>>
								2
							</option>

						</select>

					</div>

					<%-- 検索ボタン --%>
					<div class="col-1 text-center">

						<button class="btn btn-secondary w-100">
							検索
						</button>

					</div>

				</div>

			</form>
			<%--こちらから直す --%>
			<%-- 検索結果 --%>
			<c:choose>

				<c:when test="${scores.size() > 0}">

					<div class="mb-2">
						検索結果：${scores.size()}件
					</div>

					<table class="table table-hover">

						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>点数</th>
						</tr>

						<c:forEach var="score"
							items="${scores}">

							<tr>
								<td>${score.entYear}</td>
								<td>${score.classNum}</td>
								<td>${score.studentNo}</td>
								<td>${score.studentName}</td>						
								<td>${score.point}</td>
							</tr>

						</c:forEach>

					</table>

				</c:when>

				<c:otherwise>

					<div>
						成績情報が存在しませんでした。
					</div>

				</c:otherwise>

			</c:choose>

		</section>

	</c:param>

</c:import>