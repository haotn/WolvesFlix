/**
 * Statistical action
 */

$('#videoid-report-user').on('change', function() {
	var $videoId1 = $(this).val();
	var $videoId2 = $('#videoid-report-share').val();
	window.location.href = "/PC01545/admin/dashboard?action=select&video-id-1=" + $videoId1 + "&video-id-2=" + $videoId2 + "&current-tab=2";
});
$('#videoid-report-share').on('change', function() {
	var $videoId1 = $('#videoid-report-user').val();
	var $videoId2 = $(this).val();
	window.location.href = "/PC01545/admin/dashboard?action=select&video-id-1=" + $videoId1 + "&video-id-2=" + $videoId2 + "&current-tab=3";
});