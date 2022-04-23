/**
 * 
 */	console.log('asdfasfda');
$('.confirm-delete').on('click', function() {
	console.log('asdfasfda');
	var $sortBy = $('#sort-by').val();
	var $currentPage = $('#current-page').val();
	var $search = $('#search-in-list-user').val();
	var $userId = $(this).val();
	window.location.href = "/PC01545/admin/list-user?page=" + $currentPage + "&sort-id=" + $sortBy + "&search=" + $search + "&action=delete&delete-id=" + $userId;
});

$('.user__sort-by').on('click', function() {
	var $currentPage = $('#current-page').val();
	var $search = $('#search-in-list-user').val();
	window.location.href = "/PC01545/admin/list-user?page=" + $currentPage + "&sort-id=" + $(this).val() + "&search=" + $search;
});
$('#btn-search-user').on('click', function() {
	var $currentPage = $('#current-page').val();
	var $search = $('#search').val();
	var $sortBy = $('#sort-by').val();
	window.location.href = "/PC01545/admin/list-user?page=" + $currentPage + "&sort-id=" + $sortBy + "&search=" + $search;
});