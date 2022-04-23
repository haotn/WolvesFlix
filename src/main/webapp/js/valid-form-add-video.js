
var title = document.getElementsByName('title')[0];
var des = document.getElementsByName('description')[0];

title.onblur = function() {
	if (title.value.trim() == "") {
		title.setAttribute('class', 'form__input is__invalid');
		var parent = title.parentElement;
		var fe = parent.children[1];
		fe.setAttribute('class', 'valid__feedback');
	//	fe.textContent = 'Vui lòng nhập tên phim';
		//fe.style.display = 'block';
	} else {
		title.setAttribute('class', 'form__input is__valid');
		var parent = title.parentElement;
		var fe = parent.children[1];
		fe.setAttribute('class', 'invalid__feedback');
	//	fe.textContent = 'Chấp nhận';
		//fe.style.display = 'none';
	}
}