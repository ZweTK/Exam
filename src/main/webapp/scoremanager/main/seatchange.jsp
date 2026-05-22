<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <section class="me-4">

        <form action="SeatChange.action"
                      method="post">
            <%-- 見出し --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                座席切り替え
            </h2>
            <div class="d-flex justify-content-between align-items-center mb-3">

                <!-- Left side -->
                <div class="d-flex align-items-center">

                    <label class="me-2 fw-bold">
                        クラス :
                    </label>

                    <select name="class_num"
                            class="form-select w-auto">

                        <c:forEach var="num"
                                   items="${class_num_set}">

                            <option value="${num}"
                                <c:if test="${num == class_num}">
                                    selected
                                </c:if>>
                                ${num}
                            </option>

                        </c:forEach>

                    </select>

                </div>

                <!-- Right side -->
                <button type="button"
                        class="btn btn-outline-secondary"
                        onclick="window.print()">
                    印刷
                </button>

            </div>

            <%-- クラス表示 --%>
            <style>
                .seat-table tr {
                    height: 40px;
                }

                .seat-table td,
                .seat-table th {
                    text-align: center;
                    vertical-align: middle;
                }

                /* ========== アニメーション ========== */
                .seat-cell {
                    opacity: 0;
                    transform: scale(0.7);
                    transition: opacity 0.35s ease, transform 0.35s ease;
                }
                .seat-cell.visible {
                    opacity: 1;
                    transform: scale(1);
                }
                /* ==================================== */

                @media print {

                    body * {
                        visibility: hidden;
                    }

                    .print-area,
                    .print-area * {
                        visibility: visible;
                    }

                    .print-area {
                        position: absolute;
                        top: 0;
                        left: 0;
                        width: auto;
                        margin: 0 auto;
                    }

                    .seat-table {
                        table-layout: fixed;
                        border-collapse: collapse;
                        margin: auto;
                    }

                    .seat-table tr {
                        height: 80px;
                    }

                    .seat-table td,
                    .seat-table th {
                        text-align: center;
                        vertical-align: middle;
                        min-width: 70px;
                        padding: 4px;
                    }

                    @page {
                        size: landscape;
                        margin: 5mm;
                    }

                    /* 印刷時はアニメーション無効 */
                    .seat-cell {
                        opacity: 1 !important;
                        transform: none !important;
                    }

                }
            </style>

            <%-- 座席表 --%>
            <div class="mx-4 print-area">

                <table class="w-100 seat-table"
                       style="table-layout:fixed;">

                    <tr>
                        <th colspan="4"></th>
                        <th colspan="6" style="border:1px solid; text-align:center;">モニター</th>
                        <th colspan="4"></th>
                    </tr>
                    <tr style="height:20px"></tr>
                    <tr>
                        <td style="border:1px solid;"></td>
                        <td style="border:1px solid;"></td>
                        <%-- 通路 --%>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[0].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[1].name}</td>
                        <td style="border:none; width:20px"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[2].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[3].name}</td>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[4].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[5].name}</td>
                        <td style="border:none; width:20px"></td>
                        <td style="border:1px solid;"></td>
                        <td style="border:1px solid;"></td>
                    </tr>
                    <tr>
                        <td style="border:1px solid;" class="seat-cell">${list[38].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[30].name}</td>
                        <%-- 通路 --%>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[6].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[7].name}</td>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[8].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[9].name}</td>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[10].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[11].name}</td>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[31].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[39].name}</td>
                    </tr>
                    <tr>
                        <td style="border:1px solid;" class="seat-cell">${list[40].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[32].name}</td>
                        <%-- 通路 --%>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[12].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[13].name}</td>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[14].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[15].name}</td>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[16].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[17].name}</td>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[33].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[41].name}</td>
                    </tr>
                    <tr>
                        <td style="border:1px solid;" class="seat-cell">${list[42].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[34].name}</td>
                        <%-- 通路 --%>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[18].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[19].name}</td>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[20].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[21].name}</td>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[22].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[23].name}</td>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[35].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[43].name}</td>
                    </tr>
                    <tr>
                        <td style="border:none;"></td>
                        <td style="border:none;"></td>
                        <%-- 通路 --%>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[24].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[25].name}</td>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[26].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[27].name}</td>
                        <td style="border:none;"></td>
                        <td style="border:1px solid;" class="seat-cell">${list[28].name}</td>
                        <td style="border:1px solid;" class="seat-cell">${list[29].name}</td>
                        <td style="border:none;"></td>
                        <td style="border:none;"></td>
                        <td style="border:none;"></td>
                    </tr>

                </table>
            </div>

            <button class="btn btn-secondary">
                座席切り替え
            </button>

        </form>

        <%-- ========== アニメーション発火スクリプト ========== --%>
        <script>
            (function () {
                var cells = document.querySelectorAll('.seat-cell');
                cells.forEach(function (cell, i) {
                    setTimeout(function () {
                        cell.classList.add('visible');
                    }, i * 400); /* 80ms ごとに1席ずつ表示。速さを変えるならこの数値を調整 */
                });
            })();
        </script>
        <%-- ================================================== --%>

        </section>

    </c:param>

</c:import>
