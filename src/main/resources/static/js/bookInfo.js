$(function () {
	const isbn = $("#isbn").val();
	const url = "https://api.openbd.jp/v1/get?isbn=" + isbn;

	$.getJSON(url, function (data) {
		var title = 'なし';
		var publisher = 'なし';
		var author = 'なし';
		var thumbnail = '../images/noImage.png';
		
		if (data[0] != null) {
			if (data[0].summary.cover == "") {
				$("#thumbnail_image").html('<img src=\"' + thumbnail + '\" style=\"border:solid 1px #000000\" />');
			} else {
				$("#thumbnail_image").html('<img src=\"' + data[0].summary.cover + '\" style=\"border:solid 1px #000000\" />');
				thumbnail = data[0].summary.cover;
			}

			title = data[0].summary.title;
			publisher = data[0].summary.publisher;
			author = data[0].summary.author;
		}else{
			$("#thumbnail_image").html('<img src=\"' + thumbnail + '\" style=\"border:solid 1px #000000\" />');
		}

		$("#title").val(title);
		$("#publisher").val(publisher);
		$("#author").val(author);
		$("#thumbnail").val(thumbnail);
	});
});