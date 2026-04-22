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
                科目情報登録
            </h2>

            <%-- 登録フォーム開始 --%>
            <form action="SubjectCreateExecute.action" method="post">

                <div class="row mx-2 mb-3 py-4">

                    <%-- 科目コード入力欄 --%>
                    <div class="col-md-12 mb-3">

                        <label class="form-label">
                            科目コード
                        </label>

                        <input type="text"
                               name="cd"
                               value="${cd}"
                               class="form-control"
                               placeholder="科目コードを入力してください">

                        <%-- エラー表示 --%>
                        <div class="text-warning mt-1">
                            ${errors.get("cd")}
                        </div>

                    </div>

                    <%-- 科目名入力欄 --%>
                    <div class="col-md-12 mb-3">

                        <label class="form-label">
                            科目名
                        </label>

                        <input type="text"
                               name="name"
                               value="${name}"
                               class="form-control"
                               placeholder="科目名を入力してください"
                               required>

                        <%-- エラー表示 --%>
                        <div class="text-warning mt-1">
                            ${errors.get("name")}
                        </div>

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
                <a href="SubjectList.action">
                    戻る
                </a>
            </div>

        </section>

    </c:param>

</c:import>