'use strict';
function askDelete() {
	if(confirm("삭제 하시겠습니까?")) {
		document.querySelector('.Target').submit();
	} else {
		return false;
	}	
}

function checkValue() {
	if (!document.querySelector(".commentWriteArea").value) {
    alert("댓글을 작성해주세요.");
    return false;
  }
}


function cmtModifySubmit() {
	
	
	if(confirm("수정 하시겠습니까?")) {
		document.querySelector('.cmtModifyForm').submit();
	} else {
		return false;
	}
}


function cmtModify(event) {
	let parent = event.target.parentNode.parentNode;
	let target = parent.querySelector('.commentContent');
	let cmtId =  parent.querySelector('.cmtId').value;
	let orgin =  parent.querySelector('.originContent').value;
	let pageId =  parent.querySelector('.pageId').value;
	console.log(pageId);
	target.innerHTML = "<form class='cmtModifyForm' method='get' action='/board/cpu/comment/modify'><textarea class='cmtModifyTextarea' name='cmtModifyContent'>"+orgin+"</textarea><input type='button' value='수정하기' onclick='cmtModifySubmit();'/><input type='button' value='닫기' onclick='window.location.reload();'/><input type='hidden' name='cmtId' value="+cmtId+"><input type='hidden' name='pageId' value="+pageId+"></form>";
}