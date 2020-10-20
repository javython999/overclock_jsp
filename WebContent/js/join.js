"use strict";

function checkValue() {
  if (!document.querySelector(".inputId").value) {
    alert("아이디를 입력하세요.");
    return false;
  }

  if (!document.querySelector(".inputPw").value) {
    alert("비밀번호를 입력하세요.");
    return false;
  }

  if (
    document.querySelector(".inputPw").value !=
    document.querySelector(".inputPwc").value
  ) {
    alert("비밀번호가 일치하지 않습니다.");
    return false;
  }

  if (!document.querySelector(".inputNic").value) {
    alert("닉네임을 입력하세요.");
    return false;
  }

  if (!document.querySelector(".inputName").value) {
    alert("이름을 입력하세요.");
    return false;
  }

  if (!document.querySelector(".inputEmail").value) {
    alert("이메일을 입력하세요.");
    return false;
  }
}

//아이디 중복체크
function idOverlapCheck(input) {
	let form = input.form;

	form.id.value = form.id.value.trim();
	let checkId = form.id.value;
	const id = "id=";
	 $.ajax({
			async: true,
            type : 'GET',
            data : id + checkId,
            url : "/member/join/idoverlapcheck",
            dataType : "json",
            contentType: "application/json; charset=UTF-8",
            success : function(data) {
				if(data.result == '0') {
					$(".idcheckMsg").text("사용가능한 아이디입니다");
					$("#idchecker").val(true);
				} else {
					$(".idcheckMsg").text("이미 사용중인 아이디입니다");
					$("#idchecker").val(false);
				}
	}
	});
}	

//닉네임 중복체크
function nickOverlapCheck(input) {
	let form = input.form;

	form.nic.value = form.nic.value.trim();
	let checkNic = form.nic.value;
	const nic = "nic=";
	 $.ajax({
			async: true,
            type : 'GET',
            data : nic + checkNic,
            url : "/member/join/nickoverlapcheck",
            dataType : "json",
            contentType: "application/json; charset=UTF-8",
            success : function(data) {
				if(data.result == '0') {
					$(".nickcheckMsg").text("사용가능한 닉네임입니다");
					$("#nickchecker").val(true);
				} else {
					$(".nickcheckMsg").text("이미 사용중인 닉네임입니다");
					$("#nickchecker").val(false);
				}
	}
	});
}
