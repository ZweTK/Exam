<!-- test_regist.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				成績管理一覧
			</h2>

			<div class="px-4 mt-3">

				<c:if test="${!empty sessionScope.registError}">

					<div class="text-danger mb-3">
						${sessionScope.registError}
					</div>

					<c:remove var="registError"
							  scope="session" />

				</c:if>

				<c:if test="${!empty error}">

					<div class="text-danger mb-3">
						${error}
					</div>

				</c:if>

				<form method="get"
					  action="TestRegist.action"
					  class="border mx-3 mb-3 py-3 px-3 rounded">

					<div class="row align-items-end">

						<div class="col-md-2">

							<label class="form-label">
								入学年度
							</label>

							<select name="f1"
									class="form-select">

								<option value="0">
									--------
								</option>

								<c:forEach var="year"
										   items="${entYearSet}">

									<option value="${year}"
										<c:if test="${year==entYear}">
											selected
										</c:if>>

										${year}

									</option>

								</c:forEach>

							</select>

						</div>

						<div class="col-md-2">

							<label class="form-label">
								クラス
							</label>

							<select name="f2"
									class="form-select">

								<option value="0">
									--------
								</option>

								<c:forEach var="num"
										   items="${classList}">

									<option value="${num}"
										<c:if test="${num==classNum}">
											selected
										</c:if>>

										${num}

									</option>

								</c:forEach>

							</select>

						</div>

						<div class="col-md-4">

							<label class="form-label">
								科目
							</label>

							<select name="f3"
									class="form-select">

								<option value="0">
									--------
								</option>

								<c:forEach var="subject"
										   items="${subjectList}">

									<option value="${subject.cd}"
										<c:if test="${subject.cd==subjectCd}">
											selected
										</c:if>>

										${subject.name}

									</option>

								</c:forEach>

							</select>

						</div>

						<div class="col-md-2">

							<label class="form-label">
								回数
							</label>

							<select name="f4"
									class="form-select">

								<option value="0">
									--------
								</option>

								<c:forEach var="n"
										   items="${noSet}">

									<option value="${n}"
										<c:if test="${n==no}">
											selected
										</c:if>>

										${n}

									</option>

								</c:forEach>

							</select>

						</div>

						<div class="col-md-2">

							<button type="submit"
									class="btn btn-secondary w-100 text-nowrap">
								検索
							</button>

						</div>

					</div>

				</form>

				<c:if test="${!empty tests}">

					<div class="px-3 mb-2">
						科目：${subjectName}（${no}回）
					</div>

					<form method="post"
						  action="TestRegistExecute.action">

						<input type="hidden"
							   name="f1"
							   value="${entYear}" />

						<input type="hidden"
							   name="f2"
							   value="${classNum}" />

						<input type="hidden"
							   name="subject"
							   value="${subjectCd}" />

						<input type="hidden"
							   name="count"
							   value="${no}" />

						<table class="table table-hover mx-3">

							<tr>
								<th>入学年度</th>
								<th>クラス</th>
								<th>学生番号</th>
								<th>氏名</th>
								<th>点数</th>
							</tr>

							<c:forEach var="row"
									   items="${tests}">

								<tr>

									<td>${row.student.entYear}</td>
									<td>${row.classNum}</td>
									<td>${row.student.no}</td>
									<td>${row.student.name}</td>

									<td>

										<input type="hidden"
											   name="regist"
											   value="${row.student.no}" />

										<input type="text"
											   name="point_${row.student.no}"
											   value="${row.point}"
											   class="form-control"
											   style="width:200px;">

										<c:if test="${not empty errorMap[row.student.no]}">

											<div class="text-warning mt-1">
												${errorMap[row.student.no]}
											</div>

										</c:if>

									</td>

								</tr>

							</c:forEach>

						</table>

						<button type="submit"
								class="btn btn-secondary ms-3">
							登録して終了
						</button>

					</form>

				</c:if>

			</div>

		</section>

	</c:param>

</c:import>