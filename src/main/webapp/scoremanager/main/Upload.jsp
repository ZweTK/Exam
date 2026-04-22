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
                CSVアップロード
            </h2>

            <%-- CSVファイル送信用フォーム --%>
            <form action="CsvUpload.action"
                  method="post"
                  enctype="multipart/form-data">

                <%-- ファイル選択欄 --%>
                <div class="mb-3">
                    <label class="form-label">CSVファイル</label>

                    <input type="file"
                           name="csvfile"
                           class="form-control"
                           required>
                </div>

                <%-- アップロードボタン --%>
                <div class="mt-3">
                    <button class="btn btn-secondary">
                        Upload
                    </button>
                </div>

            </form>

        </section>

        <%-- 戻るリンク --%>
        <div class="mx-2 mt-3">
            <a href="menu.jsp">戻る</a>
        </div>

    </c:param>

</c:import>