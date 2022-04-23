<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>type any thing</h1>
	<input type="text" id="param1">
	<input type="text" id="param2">
	<div id="result"></div>
	<button id="submit" type="button">>Send</button>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		if (jQuery('.data-tables').length) {
			$('.data-tables').DataTable();
		}
		$('#submit').on('click', function() {
			var $p1 = $('#param1').val();
			var $p2 = $('#param2').val();
			var $formData = {
				numb1 : $p1,
				numb2 : $p2
			};
			$.ajax({
				type : 'POST',
				data : $formData,
				dataType : 'HTML',
				url : '/PC01545/demo',
				success : function(response) {
					console.log(response);
					$('#result').text(Number(response));
				}
			});
		});
	</script>
</body>
</html>