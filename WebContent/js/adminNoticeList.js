'use strict!'
function askDelete() {
	if(confirm("삭제 하시겠습니까?")) {
		document.querySelector('.noticeControll').submit();
	} else {
		return false;
	}
}

function askPub() {
	if(confirm("공개설정을 변경하시겠습니까?")) {
		document.querySelector('.noticeControll').submit();
	} else {
		return false;
	}
}