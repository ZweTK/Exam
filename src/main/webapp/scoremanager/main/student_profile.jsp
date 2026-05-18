<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
	uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

	<%-- タイトル設定 --%>
	<c:param name="title">
		得点管理システム
	</c:param>

	<%-- JavaScript --%>
	<c:param name="scripts">
		<script>
			function previewPhoto(input) {
				if (input.files && input.files[0]) {
					var reader = new FileReader();
					reader.onload = function(e) {
						var preview = document.getElementById('photo-preview');
						preview.src = e.target.result;
						preview.style.display = 'block';
					};
					reader.readAsDataURL(input.files[0]);
				}
			}
		</script>
	</c:param>

	<%-- メイン画面内容 --%>
	<c:param name="content">

		<section class="me-4">

			<%-- 見出し --%>
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				学生プロフィール
			</h2>

			<%-- 戻るリンク --%>
			<div class="mb-3 px-4">
				<a href="StudentList.action">
					&laquo; 学生一覧へ戻る
				</a>
			</div>

			<%-- 学生基本情報カード --%>
			<div class="mx-4 mb-4 p-1 border rounded bg-light">
				<div class="row align-items-center ps-4">

					<%-- プロフィール写真 --%>
					<div class="col-3 text-center">
						<c:choose>
							<c:when test="${not empty profile and not empty profile.photoBase64}">
								<img src="data:${profile.photoMimeType};base64,${profile.photoBase64}"
									 alt="プロフィール写真"
									 class="img-thumbnai"
									 style="width:180px; height:180px; object-fit:cover; border-radius:20px" />
							</c:when>
							<c:otherwise>
								<div class="bg-secondary bg-opacity-25 d-flex align-items-center justify-content-center mx-auto"
									 style="width:180px; height:180px; font-size:2.5rem; color:#6c757d; border-radius:20px">
									&#128100;
								</div>
							</c:otherwise>
						</c:choose>
					</div>

					<%-- 基本情報 --%>
					<div class="col-9">
						<table class="table table-borderless mb-0 small">
							<tr>
								<th class="text-muted" style="width:35%;">学生番号</th>
								<td><strong>${student.no}</strong></td>
							</tr>
							<tr>
								<th class="text-muted">氏名</th>
								<td>${student.name}</td>
							</tr>
							<tr>
								<th class="text-muted">入学年度</th>
								<td>${student.entYear}</td>
							</tr>
							<tr>
								<th class="text-muted">クラス</th>
								<td>${student.classNum}</td>
							</tr>
							<tr>
								<th class="text-muted">在学中</th>
								<td>
									<c:choose>
										<c:when test="${student.isAttend()}">&#9711;</c:when>
										<c:otherwise>&#10005;</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</table>
					</div>

				</div>
			</div>

			<%-- プロフィール詳細編集フォーム --%>
			<div class="mx-4" id="detail">

				<h5 class="fw-normal mb-3 border-bottom pb-2">詳細プロフィール</h5>

				<form action="StudentProfileSave.action"
					  method="post"
					  enctype="multipart/form-data">

					<input type="hidden" name="no" value="${student.no}" />

					<div class="row mx-1 mb-3 py-3">

						<%-- 名前 --%>
						<div class="col-md-12 mb-3">

							<label class="form-label" for="profile-name">
								名前
							</label>

							<input type="text"
								   id="profile-name"
								   name="name"
								   class="form-control"
								   readonly
								   value="${not empty profile ? profile.name : student.name}"
								   placeholder="フルネームを入力してください" />

						</div>

						<%-- 住所 --%>
						<div class="col-md-12 mb-3">

							<label class="form-label" for="profile-address">
								住所
							</label>

							<input type="text"
								   id="profile-address"
								   name="address"
								   class="form-control"
								   value="${not empty profile ? profile.address : ''}"
								   placeholder="都道府県から入力してください" />

						</div>

						<%-- 電話番号 --%>
						<div class="col-md-12 mb-3">

							<label class="form-label" for="profile-phone">
								電話番号
							</label>

							<input type="tel"
								   id="profile-phone"
								   name="phone"
								   class="form-control ${not empty phoneError ? 'is-invalid' : ''}"
								   value="${not empty profile ? profile.phone : ''}"
								   placeholder="例: 090-0000-0000" />

							<%-- 電話番号エラー --%>
							<div class="text-warning">
								${phoneError}
							</div>

						</div>

						<%-- メールアドレス --%>
						<div class="col-md-12 mb-3">

							<label class="form-label" for="profile-email">
								メールアドレス
							</label>

							<input type="email"
								   id="profile-email"
								   name="email"
								   class="form-control ${not empty emailError ? 'is-invalid' : ''}"
								   value="${not empty profile ? profile.email : ''}"
								   placeholder="例: student@example.com" />

							<%-- メールアドレスエラー --%>
							<div class="text-warning">
								${emailError}
							</div>

						</div>

						<%-- プロフィール写真 --%>
						<div class="col-md-12 mb-3">

							<label class="form-label" for="profile-photo">
								プロフィール写真
							</label>

							<input type="file"
								   id="profile-photo"
								   name="photo"
								   class="form-control"
								   accept="image/*"
								   onchange="previewPhoto(this)" />

							<img id="photo-preview"
								 src=""
								 alt="プレビュー"
								 class="img-thumbnail mt-2"
								 style="display:none; max-width:120px; max-height:120px; object-fit:cover;" />

							<div class="form-text">
								JPEG・PNG・GIF に対応しています。変更しない場合は空欄のままにしてください。
							</div>

						</div>

						<%-- ボタン --%>
						<div class="col-12 mt-2">

							<button class="btn btn-secondary">
								保存して終了
							</button>

						</div>

					</div>

				</form>

			</div>

		</section>

	</c:param>

</c:import>
