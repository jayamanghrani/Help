if ($.browser.msie) {         //this is for only ie condition ( microsoft internet explore )
	$('input[type="text"][placeholder], textarea[placeholder]').each(function () {
		var obj = $(this);
		if (obj.attr('placeholder') != '') {
			obj.addClass('IePlaceHolder');
			if ($.trim(obj.val()) == '' && obj.attr('type') != 'password') {
				obj.val(obj.attr('placeholder'));
			}
		}
	});
	$('.IePlaceHolder').focus(function () {
		var obj = $(this);
		if (obj.val() == obj.attr('placeholder')) {
			obj.val('');
		}
	});
	$('.IePlaceHolder').blur(function () {
		var obj = $(this);
		if ($.trim(obj.val()) == '') {
			obj.val(obj.attr('placeholder'));
		}
	});
}