<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTLコアタグライブラリを使用 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%-- 共通レイアウト(base.jsp)を読み込み --%>
<c:import url="/common/base.jsp">

    <%-- ページタイトル設定 --%>
    <c:param name="title">
        得点管理システム
    </c:param>

    <%-- JavaScript用領域（今回は未使用） --%>
    <c:param name="scripts"></c:param>

    <%-- メインコンテンツ開始 --%>
    <c:param name="content">

        <section class="me-4">

            <%-- 画面タイトル --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                先生情報登録
            </h2>

            <%-- 登録フォーム開始 --%>
            <form action="TeacherCreateExecute.action" method="post">

                <div class="row mx-2 mb-3 py-4">

                    <%-- ID入力欄 --%>
                    <div class="col-md-12 mb-3">
                        <text class="form-label">ID</text>

                        <input type="text"
                               name="ID"
                               class="form-control"
                               placeholder="IDを入力してください"
                               required>
                    </div>

                    <%-- パスワード入力欄 --%>
                    <div class="col-md-12 mb-3">
                        <text class="form-label">Password</text>

                        <input type="password"
                               name="password"
                               class="form-control"
                               placeholder="Passwordを入力してください"
                               required>
                    </div>

                    <%-- 氏名入力欄 --%>
                    <div class="col-md-12 mb-3">
                        <text class="form-label">氏名</text>

                        <input type="text"
                               name="name"
                               value="${name}"
                               class="form-control"
                               placeholder="氏名を入力してください"
                               required">
                    </div>

                    <%-- 学校コード選択欄 --%>
                    <div class="col-md-12 mb-3">
                        <label class="form-label">学校コード</label>

                        <select name="school_cd" class="form-select">

                            <%-- 初期表示用空欄 --%>
                            <option value="0">--------</option>

                            <%-- 学校コード一覧を繰り返し表示 --%>
                            <c:forEach var="school" items="${school_num_set}">
                                <option value="${school}">
                                    ${school}
                                </option>
                            </c:forEach>

                        </select>
                    </div>

                    <%-- 管理者権限チェックボックス --%>
                    <div class="mb-3 form-check">
                        <input type="checkbox"
                               name="isManage"
                               value="t"
                               class="form-check-input"
                               id="isManage"
                               default="false">

                        <label class="form-check-label" for="isManage">
                            Manage
                        </label>
                    </div>

                    <%-- 登録ボタン --%>
                    <div class="col-12 mt-3">
                        <button class="btn btn-secondary">
                            登録して終了
                        </button>
                    </div>

                </div>

            </form>

            <%-- 戻るリンク --%>
            <div class="mx-4">
                <a href="StudentList.action">戻る</a>
            </div>

        </section>

    </c:param>

</c:import>