'use strict!'
function askDelete() {
	if(confirm("삭제 하시겠습니까?")) {
		document.querySelector('.deletTarget').submit();
	} else {
		return false;
	}
	
}