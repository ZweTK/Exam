<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

<c:param name="title">CSV確認</c:param>

<c:param name="content">

<section class="me-4">

<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
CSVアップロード確認
</h2>

<table class="table table-bordered">

<thead>
<tr>
<th>学生番号</th>
<th>氏名</th>
<th>入学年度</th>
<th>クラス</th>
<th>在学</th>
<th>学校コード</th>
</tr>
</thead>

<tbody>

<c:forEach var="row" items="${previewList}">
<tr>
<td>${row.no}</td>
<td>${row.name}</td>
<td>${row.entYear}</td>
<td>${row.classNum}</td>
<td>${row.attend}</td>
<td>${row.school.cd}</td>
</tr>
</c:forEach>

</tbody>

</table>

<form action="CsvSave.action" method="post">

<button type="submit" class="btn btn-success">
確認して登録
</button>

</form>

</section>

</c:param>

</c:import>