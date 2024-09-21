$(document).ready(function () {
	var w = window.opener;
	if (w) {
		$('.statisztika > p').text(w.statisztika());
		$('.megoldasok')
			.append(w.kiertekel())
			.find(':button')
				.click(function () {
					self.close();
				});
	}
});