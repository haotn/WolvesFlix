/**
 * Show hide pass
 */
function showHidePass() {
	var input = document.getElementsByClassName('pass__input');
	var parent = input[0].parentNode;
	var label = parent.getElementsByTagName('label')[0];
	if (input[0].type === "password") {
		input[0].setAttribute('type', 'text');
		input[1].setAttribute('type', 'text');
		label.textContent = "Ẩn mật khẩu";
	} else {
		input[0].setAttribute('type', 'password');
		input[1].setAttribute('type', 'password');
		label.textContent = "Hiện mật khẩu";
	}
}