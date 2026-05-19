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
			<div class="d-flex align-items-center mb-3">
			
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

            <%-- クラス表示 --%>
            <style>
				.seat-table tr{
					height:40px;
				}
				
				.seat-table td,
				.seat-table th{
				    text-align:center;
				    vertical-align:middle;
				}
			</style>

            <%-- 座席表 --%>
            <div class="mx-4">

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
					
					    <td style="border:1px solid;">${list[0].name}</td>
					    <td style="border:1px solid;">${list[1].name}</td>
					
						<td style="border:none; width:20px"></td>
						
					    <td style="border:1px solid;">${list[2].name}</td>
					    <td style="border:1px solid;">${list[3].name}</td>
					    
					    <td style="border:none;"></td>
					
					    <td style="border:1px solid;">${list[4].name}</td>
					    <td style="border:1px solid;">${list[5].name}</td>
					    
					    <td style="border:none;width:20px"></td>
					
					    <td style="border:1px solid;"></td>
					    <td style="border:1px solid;"></td>
					
					</tr>
					<tr>
					
					    <td style="border:1px solid;">${list[38].name}</td>
					    <td style="border:1px solid;">${list[30].name}</td>
					
					    <%-- 通路 --%>
					    <td style="border:none;"></td>
					
					    <td style="border:1px solid;">${list[6].name}</td>
					    <td style="border:1px solid;">${list[7].name}</td>
					
						<td style="border:none;"></td>
						
					    <td style="border:1px solid;">${list[8].name}</td>
					    <td style="border:1px solid;">${list[9].name}</td>
					    
					    <td style="border:none;"></td>
					
					    <td style="border:1px solid;">${list[10].name}</td>
					    <td style="border:1px solid;">${list[11].name}</td>
					    
					    <td style="border:none;"></td>
					
					    <td style="border:1px solid;">${list[31].name}</td>
					    <td style="border:1px solid;">${list[39].name}</td>
					
					</tr>
					<tr>
					
					    <td style="border:1px solid;">${list[40].name}</td>
					    <td style="border:1px solid;">${list[32].name}</td>
					
					    <%-- 通路 --%>
					    <td style="border:none;"></td>
					
					    <td style="border:1px solid;">${list[12].name}</td>
					    <td style="border:1px solid;">${list[13].name}</td>
					
						<td style="border:none;"></td>
						
					    <td style="border:1px solid;">${list[14].name}</td>
					    <td style="border:1px solid;">${list[15].name}</td>
					    
					    <td style="border:none;"></td>
					
					    <td style="border:1px solid;">${list[16].name}</td>
					    <td style="border:1px solid;">${list[17].name}</td>
					    
					    <td style="border:none;"></td>
					
					    <td style="border:1px solid;">${list[33].name}</td>
					    <td style="border:1px solid;">${list[41].name}</td>
					
					</tr>
					<tr>
					
					    <td style="border:1px solid;">${list[42].name}</td>
					    <td style="border:1px solid;">${list[34].name}</td>
					
					    <%-- 通路 --%>
					    <td style="border:none;"></td>
					
					    <td style="border:1px solid;">${list[18].name}</td>
					    <td style="border:1px solid;">${list[19].name}</td>
					
						<td style="border:none;"></td>
						
					    <td style="border:1px solid;">${list[20].name}</td>
					    <td style="border:1px solid;">${list[21].name}</td>
					    
					    <td style="border:none;"></td>
					
					    <td style="border:1px solid;">${list[22].name}</td>
					    <td style="border:1px solid;">${list[23].name}</td>
					    
					    <td style="border:none;"></td>
					
					    <td style="border:1px solid;">${list[35].name}</td>
					    <td style="border:1px solid;">${list[43].name}</td>
					
					</tr>
					<tr>
					
					    <td style="border:none;"></td>
					    <td style="border:none;"></td>
					
					    <%-- 通路 --%>
					    <td style="border:none;"></td>
					
					    <td style="border:1px solid;">${list[24].name}</td>
					    <td style="border:1px solid;">${list[25].name}</td>
					
						<td style="border:none;"></td>
						
					    <td style="border:1px solid;">${list[26].name}</td>
					    <td style="border:1px solid;">${list[27].name}</td>
					    
					    <td style="border:none;"></td>
					
					    <td style="border:1px solid;">${list[28].name}</td>
					    <td style="border:1px solid;">${list[29].name}</td>
					    
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


        </section>

    </c:param>

</c:import>