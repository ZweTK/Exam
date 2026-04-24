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
				成績参照
			</h2>

			<%-- 検索フォーム --%>
			<form action="ScoreList.action" method="get">

				<div class="row border mx-3 mb-3 py-3 align-items-end rounded"
					 id="filter">
					 
					 <div class="col-2">
					 <p>科目情報</p>
					 </div>

					<%-- 入学年度 --%>
					<div class="col-2">

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
								items="${ent_year_set}">

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
					<div class="col-2">

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
								items="${class_num_set}">

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
								items="${subject_set}">

								<option value="${sub.cd}"
									<c:if test="${sub.cd==f3}">
										selected
									</c:if>>

									${sub.name}

								</option>

							</c:forEach>

						</select>

					</div>

					<%-- 科目検索ボタン --%>
					<div class="col-2 text-center">

						<button class="btn btn-secondary w-100">
							検索
						</button>

					</div>
					
					<div class="w-100 border-top my-3"></div>
					
					<div class="col-2">
					 <p>学生情報</p>
					 </div>

					<%-- 学生番号 --%>
					<div class="col-4">

						<label class="form-label"
							   for="score-f4-select">
							学生番号
						</label>

						<input name="no"
							   type="text"
							   value=""
							   class="form-control border-1"
							   required
							   placeholder="学生番号を入力してください">

					</div>
					
					<%-- 学生検索ボタン --%>
					<div class="col-2 text-center">

						<button class="btn btn-secondary w-100">
							検索
						</button>

					</div>

				</div>

			</form>

		</section>

	</c:param>

</c:import>