<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

<c:param name="title">
    出欠登録
</c:param>

<c:param name="content">

<section class="me-4">

<h2 class="h3 mb-3 fw-normal
bg-secondary bg-opacity-10
py-2 px-4">

    出欠登録

</h2>

<div class="px-4">

    <!-- エラー -->

    <c:if test="${!empty error}">

        <div class="alert alert-danger">

            ${error}

        </div>

    </c:if>

    <!-- 成功 -->

    <c:if test="${!empty message}">

        <div class="alert alert-success">

            ${message}

        </div>

    </c:if>

    <form method="post"
          action="AttendExecute.action">

        <!-- 日付 -->

        <div class="mb-3">

            <label class="form-label">

                日付

            </label>

            <input type="date"
                   name="date"
                   class="form-control"
                   required>

        </div>

        <!-- 一覧 -->

        <table class="table table-hover">

            <tr>

                <th>学生番号</th>

                <th>氏名</th>

                <th>出欠</th>

            </tr>

            <c:forEach var="s"
                       items="${studentList}">

                <tr>

                    <td>

                        ${s.no}

                        <input type="hidden"
                               name="student_no"
                               value="${s.no}">

                    </td>

                    <td>

                        ${s.name}

                    </td>

                    <td>

                        <select
                            name="status_${s.no}"
                            class="form-select">

                            <option value="出席">

                                出席

                            </option>

                            <option value="欠席">

                                欠席

                            </option>

                            <option value="遅刻">

                                遅刻

                            </option>

                        </select>

                    </td>

                </tr>

            </c:forEach>

        </table>

        <button type="submit"
                class="btn btn-secondary">

            登録

        </button>

    </form>

</div>

</section>

</c:param>

</c:import>