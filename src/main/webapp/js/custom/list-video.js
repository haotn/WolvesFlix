/**
 * Sort
 */
$('.video__sort-by').on('click', function() {
	var $currentPage = $('#current-page').val();
	var $search = $('#search-in-list-video').val();
	window.location.href = "/PC01545/admin/list-video?page=" + $currentPage + "&sort-id=" + $(this).val() + "&search=" + $search;
});
$('#btn-search-video').on('click', function() {
	var $currentPage = $('#current-page').val();
	var $search = $('#search').val();
	var $sortBy = $('#sort-by').val();
	window.location.href = "/PC01545/admin/list-video?page=" + $currentPage + "&sort-id=" + $sortBy + "&search=" + $search;
});

$('#action-status').on('click', function(){
	var $currentPage = $('#current-page').val();
	var $search = $('#search').val();
	var $sortBy = $('#sort-by').val();
	var $videoId = $('#hiddenId').val();
	alert(Number($videoId));
	window.location.href="/PC01545/admin/list-video?page=" + $currentPage + "&sort-id=" + $sortBy + "&search=" + $search+"&action=hidden&hidden-id="+$videoId;
});