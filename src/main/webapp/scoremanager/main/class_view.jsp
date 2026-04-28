<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">
        学生一覧
    </c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                ${class_num} クラス(${list.size()})人
            </h2>

            <table class="table table-bordered">

                <tr>
                    <th>学生番号</th>
                    <th>氏名</th>
                    <th>入学年度</th>
                    <th>在学</th>
                </tr>

                <c:forEach var="student" items="${list}">

                    <tr>
                        <td>${student.no}</td>
                        <td>${student.name}</td>
                        <td>${student.entYear}</td>
                        <td>
                            <c:choose>
                                <c:when test="${student.attend}">
                                   o
                                </c:when>
                                <c:otherwise>
                                    x
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>

                </c:forEach>

            </table>

            <div class="mt-3">
                <a href="ClassList.action">戻る</a>
            </div>

        </section>

    </c:param>

</c:import>