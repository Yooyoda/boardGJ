<%--------------------------------------------------------------------------------
	* 화면명 : Smart Editor 2.8 에디터 연동 페이지
	* 파일명 : /page/test/index.jsp
--------------------------------------------------------------------------------%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>Smart Editor</title>

<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico" />

<!-- jQuery -->
<!-- <script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>-->

<!-- <script type="text/javascript" src="/js/jquery/jquery-3.2.1.js"></script> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>


<script
	src="${pageContext.request.contextPath }/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
	var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

	$(document)
			.ready(
					function() {

	$("#fileadd").on("click",function(){
		if($(".file").length <= 4){
			 $( '#filediv' ).append( '<input type="file" class="file" name="file"/>' );
			}else{
				alert("첨부파일은 5개만 가능합니다");
			}
		
	});
						// 	var inputList = "";
						// 	var fileCnt = "";

						// 	$("#plus").on("click", function(){
						// 		if($(".fileWrap p").length < 5){
						// 			fileCnt = $(".fileWrap input").length; 
						// 			inputList = '<p id="fileList'+ fileCnt +'"><input type="file" id="profile" name="profile"><input type="button" onclick="minus('+fileCnt+')" value="-"></p>';
						// 			$(".fileWrap").append(inputList);
						// 		}
						// 	});


						// Editor Setting
						nhn.husky.EZCreator
								.createInIFrame({
									oAppRef : oEditors, // 전역변수 명과 동일해야 함.
									elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
									sSkinURI : "${pageContext.request.contextPath }/SE2/SmartEditor2Skin.html", // Editor HTML
									fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
									htParams : {
										// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
										bUseToolbar : true,
										// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
										bUseVerticalResizer : true,
										// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
										bUseModeChanger : true,
									}
								});

						// 전송버튼 클릭이벤트
						$("#savebutton").click(
								function() {
									if (confirm("저장하시겠습니까?")) {
										// id가 smarteditor인 textarea에 에디터에서 대입
										oEditors.getById["smarteditor"].exec(
												"UPDATE_CONTENTS_FIELD", []);

										// 이부분에 에디터 validation 검증
										if (validation()) {
											$("#frm").submit();
										}
									}
								});

					});

	// 필수값 Check
	function validation() {
		var contents = $.trim(oEditors[0].getContents());
		if (contents === '<p>&nbsp;</p>' || contents === '') { // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
			alert("내용을 입력하세요.");
			oEditors.getById['smarteditor'].exec('FOCUS');
			return false;
		}

		return true;
	};

	//file 리스트 제거
	function minus(minusNum) {
		$("#fileList" + minusNum).remove();
	};
	
// 	$(document).on("change",".file", function(){
// 		if($(".file").length <= 4){
// 		 $( '#filediv' ).append( '<input type="file" class="file" name="file"/>' );
// 		}else{
// 			alert("첨부파일은 5개만 가능합니다");
// 		}
// 	});
	
	
</script>
<!-- css, js -->
<%@include file="/common/basicLib.jsp"%>
</head>
<body>
	<!-- 	header -->
	<%@include file="/common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@include file="/common/left.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<form action="${pageContext.request.contextPath }/newbw"
					method="post" id="frm" enctype="multipart/form-data" >
					<input type="hidden" name="boardId" id="boardId" value="${boardId }"> 
					<input type="hidden" name="writeId" id="writeId" value="${writeId }">

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">제목</label>
						<div class="col-sm-10">
							<input type="text" id="title" name="title"> <br>
							<br>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">글내용</label>
						<div class="col-sm-10">
							<textarea name="smarteditor" id="smarteditor" rows="10"
								cols="100" style="width: 600px; height: 350px;"></textarea>
						</div>
					</div>


					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">첨부파일</label>
						<div class="col-sm-10" id="filediv">
							<input type="button" class = "form-control" id="fileadd" value="파일추가" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">저장</label>
						<div class="col-sm-10" id="filediv">
							<input type="button" class = "form-control" id="savebutton" value="저장" />
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>
</body>
</html>