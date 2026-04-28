<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                CSVアップロード
            </h2>

            <%-- 成功時メッセージ --%>
            <c:if test="${msg == 'CSV Upload Success'}">
                <div class="alert alert-success">
                    ${msg}
                </div>
            </c:if>

            <%-- エラー時メッセージ --%>
            <c:if test="${msg != 'CSV Upload Success'}">
                <div class="alert alert-danger">
                    ${msg}
                </div>
            </c:if>

            <div class="mx-2 mt-3">
                <a href="StudentList.action">学生一覧</a>
            </div>

        </section>

    </c:param>

</c:import>