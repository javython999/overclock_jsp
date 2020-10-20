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

}
