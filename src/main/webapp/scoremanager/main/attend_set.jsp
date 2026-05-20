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

            <div class="px-4 mt-3">

                <!-- 検索フォーム -->

                <form method="get"
                      action="AttendSet.action"
                      class="border rounded p-3 mb-4">

                    <div class="row align-items-end">

                        <!-- クラス -->

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

                                    <option value="${c}"
                                        <c:if test="${c==classNum}">
                                            selected
                                        </c:if>>

                                        ${c}

                                    </option>

                                </c:forEach>

                            </select>

                        </div>

                        <!-- 日付 -->

                        <div class="col-md-3">

                            <label class="form-label">
                                日付
                            </label>

                            <input type="date"
                                   name="date"
                                   value="${date}"
                                   class="form-control">

                        </div>

                        <!-- ボタン -->

                        <div class="col-md-2">

                            <button type="submit"
                                    class="btn btn-secondary w-100">

                                検索

                            </button>

                        </div>

                    </div>

                </form>

                <!-- エラー -->

                <c:if test="${!empty error}">

                    <div class="text-danger mb-3">
                        ${error}
                    </div>

                </c:if>

                <!-- 学生表示 -->

                <c:if test="${!empty students}">

                    <form method="post"
                          action="AttendExecute.action">

                        <input type="hidden"
                               name="class_num"
                               value="${classNum}">

                        <input type="hidden"
                               name="date"
                               value="${date}">

                        <table class="table table-hover">

                            <tr>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>出欠</th>
                            </tr>

                            <c:forEach var="s"
                                       items="${students}">

                                <tr>

                                    <td>${s.no}</td>

                                    <td>${s.name}</td>

                                    <td>
                                    
                                      <input type="hidden"
                   name="student_no"
                   value="${s.no}">
                                    

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
                                            
                                            <option value="早退">
                                                早退
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

                </c:if>

            </div>

        </section>

    </c:param>

</c:import>