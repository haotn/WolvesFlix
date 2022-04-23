/**
 * Paginator
 */

$(document).ready(function() {
	function submitForm($parameter) {
		var $target = $('#videoId').text();
		$('#tab-navigator-edit-user').attr('action', '/PC01545/admin/list-user?action=edit&edit-id=' + $target + '&current-tab=' + $parameter);
		$('#tab-navigator-edit-user').submit();
	}
	$('#profile-tab').on('click', function() {
		submitForm('profile');
	});
	$('#comment-tab').on('click', function() {
		submitForm('comment');
	})
});