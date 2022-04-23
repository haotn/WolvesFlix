//Valid form signgin


//Show message
function showError($element) {
	var $message = $element.closest('.sign__group').find('span');
	console.log($message.text());
	$message.css("visibility", "visible");
}
//Hide message
function hideError($element) {
	//	var $parent = $element.parent();
	var $parent = $element.parent();
	var $message = $parent.find('span');
	console.log($message);
	$message.css('visibility', 'hidden');
}
/*
$('#input__fullname-signup').on('blur', function() {
	console.log('Blur');
	var $username = $(this).val();
	if ($username.trim().length > 6) {
		console.log("valid")
		hideError($(this));
	} else {
		console.log('invalid')
		showError($(this));
	}
});*/