<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

<c:param name="title">
    出欠一覧
</c:param>

<c:param name="content">

<section class="me-4">

<h2 class="h3 mb-3 fw-normal
bg-secondary bg-opacity-10
py-2 px-4">

    出欠一覧

</h2>

<div class="px-4">

<c:if test="${!empty sessionScope.message}">

    <div class="alert alert-success">

        ${sessionScope.message}

    </div>

    <c:remove var="message"
              scope="session"/>

</c:if>

    <!-- クラス検索 -->

    <form method="get"
          action="AttendList.action"
          class="border rounded p-3 mb-4">

        <div class="row align-items-end">

            <div class="col-md-3">

                <label class="form-label">
                    クラス
                </label>

                <select name="class_num"
                        class="form-select">

                    <option value="">
                        --------
                    </option>

                    <c:forEach var="c"
                               items="${classList}">

                        <option value="${c}">
                            ${c}
                        </option>

                    </c:forEach>

                </select>

            </div>

            <div class="col-md-3">

                <label class="form-label">
                    日付
                </label>

                <input type="date"
                       name="date"
                       class="form-control">

            </div>

            <div class="col-md-2">

                <button type="submit"
                        class="btn btn-secondary w-100">

                    検索

                </button>

            </div>

        </div>

    </form>

    <!-- 学生番号検索 -->

    <form method="get"
          action="AttendList.action"
          class="border rounded p-3 mb-4">

        <div class="row align-items-end">

            <div class="col-md-4">

                <label class="form-label">
                    学生番号
                </label>

                <input type="text"
                       name="student_no"
                       class="form-control">

            </div>

            <div class="col-md-2">

                <button type="submit"
                        class="btn btn-secondary w-100">

                    検索

                </button>

            </div>

        </div>

    </form>

    <!-- 一覧 -->

    <c:if test="${!empty attendList}">

        <table class="table table-hover">

            <tr>
                <th>学生番号</th>
                <th>日付</th>
                <th>出欠</th>
            </tr>

            <c:forEach var="a"
                       items="${attendList}">

                <tr>

                    <td>${a.studentNo}</td>

                    <td>${a.attendDate}</td>

                    <td>${a.status}</td>

                </tr>

            </c:forEach>

        </table>

    </c:if>

</div>

</section>

</c:param>

</c:import>