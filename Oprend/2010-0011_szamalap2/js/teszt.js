window.test = true;
$(document).ready(function () {
	$('.valaszok')
		.shuffle()
		.each(function (i) {
			var valaszok = $('.valasz', this);
			var helyesek = valaszok.filter('.helyes');
			var helyes_db = helyesek.length;

			var type = helyes_db > 1 ? 'checkbox' : 'radio';

			valaszok.each(function (j) {
				var id = 'kerdes_' + i + '_valasz_' + j;
				var tabindex = (i+1)*100 + j*5;
				$(this)
					.children('span')
						.remove()
						.end()
					.wrapInner(
						$('<label></label>').attr('for', id)
					)
					.prepend(
						$('<input type="'+type+'" name="'+(helyes_db > 1 ? id : 'kerdes_' + i)+'" />').attr({
							'id':		id,
							'class':	$(this).hasClass('helyes') ? 'helyes' : '',
							'tabindex':	tabindex
						})
					);
			});
		});

	var submit_tabindex = parseInt( $('.urlap_gombok > :submit').val('Kiértékelés (r)').attr('tabindex') );
	
	$('.urlap_gombok > :submit').attr({
			'accesskey' : 'r'						  
									  });

	
	$('.urlap_gombok').append(
		$('<input type="reset" />').attr({
			'value':		'Alaphelyzetbe állítás (h)',
			'tabindex':		submit_tabindex + 5,
			'accesskey':	'h'
		})
	);

	$('div.kerdesek > form').submit(function (e) {
		var w = window.open('kiertekel.html', '_blank', 'width=800,height=600,scrollbars=yes');
		e.preventDefault();
	});

});

function statisztika() {
	var kerdes_db = $('.kerdesblokk').length;
	var db = 0;
	$('.valaszok').each(function () {
		var bejeloltek = $('input:checked', this);
		if (bejeloltek.length > 0 && bejeloltek.not('.helyes').length == 0) {
			db++;
		}
	});
	var helyes_valasz_db = db;

	return 'A feladatsor ' + kerdes_db + ' feladatból állt. Ön ' + helyes_valasz_db + ' feladatot oldott meg helyesen.';
}

function kiertekel() {
	var eredeti = $('.kerdesek > form').find(':radio').add($('.kerdesek > form').find(':checkbox'));
	var kerdesek = $('.kerdesek > form').clone();
	var kerdesdb = kerdesek.children().length;

	kerdesek
		.find(':radio').add(kerdesek.find(':checkbox'))
			.each(function (i) {
				var szoveg;

				var orignode = eredeti.filter("[id='" + $(this).attr('id') + "']");
				if (orignode.is(':checked')) {
					$(this).attr('checked', 'checked')
				}
				
				if (!$(this).is(':checked') && !$(this).hasClass('helyes')) {
					szoveg = '<span class="visszajelzes">Nem megjelölt válasz &mdash; </span>';
				}
				else if ($(this).is(':checked') && !$(this).hasClass('helyes')) {
					szoveg = '<span class="visszajelzes">Megjelölve, de a válasz hibás &mdash; </span>';
					$(this).parent().addClass('helytelen');
				}
				else if (!$(this).is(':checked') && $(this).hasClass('helyes')) {
					szoveg = '<span class="visszajelzes">Nincs megjelölve, de a válasz helyes &mdash; </span>';
					$(this).parent().removeClass('helyes').addClass('hibas');
				}
				else {
					szoveg = '<span class="visszajelzes">Megjelölve, helyes válasz  &mdash; </span>';
				}

				var valasz = $(this).next().text();
				$(this)
					.parent()
						.children()
							.remove()
							.end()
						.prepend(szoveg + valasz);
			})
			.end().end()
		.find(':submit').add(kerdesek.find(':reset'))
			.remove()
			.end().end()
		//workaround vege
		.children('.urlap_gombok')
			.append(
				$('<input type="button" />')
					.attr({
						'value':		'Ablak bezárása (b)',
						'tabindex':		kerdesdb * 100
						
					})
			);

	return kerdesek.html();
}