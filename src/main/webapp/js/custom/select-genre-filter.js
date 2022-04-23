
$('.filter-action').on('click', function() {
	console.log('asfds');
	$('#show-genre-name').text($(this).text());
	window.location.href = "/PC01545/home/index?filter=" + $(this).val();
	console.log($(this).val());
});