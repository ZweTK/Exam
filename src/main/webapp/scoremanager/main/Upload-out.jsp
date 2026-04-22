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
                CSVアップロード完了
            </h2>

            <%-- 取込成功メッセージ表示 --%>
            <div class="alert alert-success">
                取り込み完了しました。
            </div>

            <%-- 学生一覧画面へのリンク --%>
            <div class="mx-2 mt-3">
                <a href="StudentList.action">学生一覧</a>
            </div>

        </section>

    </c:param>

</c:import>