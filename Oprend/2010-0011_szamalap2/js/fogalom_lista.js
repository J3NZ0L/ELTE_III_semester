$(document).ready(function () {
							
	var w = window.opener;
	if (w) {
		var fogalom_obj = w.get_fogalom_lista();
		var fogalom_lista = [];
		for (var fog in fogalom_obj) {
			fogalom_lista.push(fog);
		}
		fogalom_lista.sort(function (a, b) {
			if (typeof a === 'string' && typeof b === 'string') {
				return a.toLowerCase().localeCompare(b.toLowerCase());
			}
			return a < b;
		});

		var tartalom_jegyzek = new Array(fogalom_lista.length);
		var def_lista = new Array(fogalom_lista.length);
		for (var i = 0, l = fogalom_lista.length; i < l; i++) {
			tartalom_jegyzek[i] = '<li><a href="#fog'+(i+1)+'" tabindex="'+(40 + i*5)+'">'+fogalom_lista[i]+'</a></li>';
			def_lista[i] = '<dt><a name="fog'+(i+1)+'">'+fogalom_lista[i]+'</a></dt><dd>'+fogalmak[fogalom_lista[i]]+'<p class="vissza"><a href="#fejezetek" tabindex="1000" accesskey="v" title="Vissza a tartalomjegyzékhez">Vissza a tartalom<span class="gyorsb">j</span>egyzékhez</a></p></dd>';
		}
		$('div.tartalommenu')
			.append(
				$('<ul></ul>').html(tartalom_jegyzek.join(''))
			);
		$('div.tartalom')
			.append(
				$('<dl></dl>').html(def_lista.join(''))
			);
	}
});