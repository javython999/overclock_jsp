"use strict";


//URL에서 파라미터 값 가져오기
function getParameterByName(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
        return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }
	
let msg = getParameterByName('msg');

function failInfo(msg) {
	if(msg === '2') {
		alert("비밀번호를 확인하세요.");
	} else if(msg === '3') {
		alert("아이디를 확인하세요.");
	} else if(msg === '4') {
		alert("아이디/비밀번호 모두 확인하세요.");
	} else if(msg === '5') {
		alert("계정이 존재하지 않습니다.");
	} else if(msg === '0') {
		alert("계정이 존재하지 않습니다.");
	}
}

failInfo(msg);