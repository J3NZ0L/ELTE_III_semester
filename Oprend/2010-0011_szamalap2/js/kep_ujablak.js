var RAHAGYAS_X = 100;
var RAHAGYAS_Y = 370;
var IKONSOR = '<div><a name="teteje" id="teteje"></a></div><div class="jawsfmenu"><ul><li><a class="tartalom" href="#tartalom" accesskey="t" tabindex="2">Ugrás a <span class="gyorsb">t</span>artalomra</a></li></ul></div><div class="utmutato"><div class="utm_hivatkozasok"><a class="nyomtatas" href="javascript:window.print();" title="Oldal nyomtatása" accesskey="y" tabindex="29"><img src="../navi/ikon_nyomtatas.gif" alt="Oldal nyomtatása" /><span class="ikonfelirat_ki">Oldal n<span class="gyorsb">y</span>omtatása</span></a><a class="ablakbezar" href="../index.html" onclick="javascript:window.close();" accesskey="b" tabindex="37"><img src="../navi/ikon_ablakbezar.gif" alt="Ablak bezárása" title="Ablak bezárása" width="36" height="36"/><span class="ikonfelirat_ki"><span class="gyorsb">B</span>ezárás</span></a></div></div>';
var KEPIKONOK = '<div class="kep_ikonok kozepre"><a class="ablakbezar" href="index.html" onclick="javascript:window.close();" accesskey="b" title="Ablak bezárása">Ablak <span class="gyorsb">b</span>ezárása</a></div>';
var KEPNAVIGACIO = 'Az ablakot a képre kattintással is be tudja zárni.';

window.onload = function () {

var width = $('div.kep img').width();
	var height = $('div.kep img').height();
	width += RAHAGYAS_X;
	height += RAHAGYAS_Y;
	var top = (screen.height-height)/2;
	var left = (screen.width-width)/2;

	window.resizeTo(width, height);
	window.moveTo(left, top);

	$(document.body).prepend($(IKONSOR));
	$('div.kep').after(KEPIKONOK);
	$('div.kep img').click(function () {
		self.close();
	});
	$('div.kepnavigacio').text(KEPNAVIGACIO);
};