<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTLコアタグライブラリ読込 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%-- 共通レイアウト読込 --%>
<c:import url="/common/base.jsp">

    <%-- ページタイトル設定 --%>
    <c:param name="title">
        得点管理システム
    </c:param>

    <%-- JavaScript設定 --%>
    <c:param name="scripts">

        <script>
        window.onload = function () {

            // ドロップエリア取得
            const dropArea = document.getElementById("dropArea");

            // file input取得
            const fileInput = document.getElementById("csvfile");

            // ドロップエリアクリックでファイル選択画面表示
            dropArea.onclick = function () {
                fileInput.click();
            };

            // ドラッグ中
            dropArea.addEventListener("dragover", function(e) {
                e.preventDefault();
                dropArea.classList.add("border-primary");
            });

            // ドラッグ離脱時
            dropArea.addEventListener("dragleave", function(e) {
                e.preventDefault();
                dropArea.classList.remove("border-primary");
            });

            // ファイルドロップ時
            dropArea.addEventListener("drop", function(e) {
                e.preventDefault();

                // 枠色戻す
                dropArea.classList.remove("border-primary");

                // ドロップされたファイル取得
                const files = e.dataTransfer.files;

                if (files.length > 0) {

                    // FileListを作成してinputへ設定
                    const dt = new DataTransfer();
                    dt.items.add(files[0]);

                    fileInput.files = dt.files;

                    // ファイル名表示
                    dropArea.innerHTML = files[0].name;
                }
            });

            // 通常選択時もファイル名表示
            fileInput.addEventListener("change", function() {
                if (fileInput.files.length > 0) {
                    dropArea.innerHTML = fileInput.files[0].name;
                }
            });

        };
        </script>

    </c:param>

    <%-- メイン画面 --%>
    <c:param name="content">

        <section class="me-4">

            <%-- 画面タイトル --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                CSVアップロード
            </h2>

            <%-- CSVアップロードフォーム --%>
            <form action="CsvUpload.action"
                  method="post"
                  enctype="multipart/form-data">

                <div class="mb-3">

                    <%-- ラベル --%>
                    <label class="form-label">
                        CSVファイル
                    </label>

                    <%-- ドラッグ＆ドロップエリア --%>
                    <div id="dropArea"
                         class="border border-2 rounded p-5 text-center bg-light"
                         style="cursor:pointer;">

                        ここにCSVファイルをドラッグ＆ドロップ

                    </div>

                    <%-- ファイル選択欄 --%>
                    <input type="file"
                           id="csvfile"
                           name="csvfile"
                           class="form-control mt-2"
                           accept=".csv"
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