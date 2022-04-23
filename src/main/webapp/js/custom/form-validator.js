/**
 * Form validator
 */

function isTextNotNull($element) {
	var $mess = $element.parent().find('.message');
	if ($element.val().trim().length == 0) {
		$mess.css('visibility', 'visible');
		return false;
	}
	$mess.css('visibility', 'hidden');
	return true;
}