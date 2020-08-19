PrimeFaces.locales['pt_BR'] = {
	closeText : 'Concluir',
	prevText : 'Anterior',
	nextText : 'Próximo',
	monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho',
			'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro' ],
	monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago',
			'Set', 'Out', 'Nov', 'Dez' ],
	dayNames : [ 'Domingo', 'Segunda-feira', 'Terça-feira', 'Quarta-feira',
			'Quinta-feira', 'Sexta-feira', 'Sábado' ],
	dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab' ],
	dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
	weekHeader : 'Semana',
	firstDay : 1,
	isRTL : false,
	showMonthAfterYear : false,
	yearSuffix : '',
	timeOnlyTitle : 'Apenas tempo',
	timeText : 'Horário',
	hourText : 'Hora',
	minuteText : 'Minuto',
	secondText : 'Segundo',
	currentText : 'Data atual',
	ampm : false,
	month : 'Mês',
	week : 'Semana',
	day : 'Dia',
	allDayText : 'Dia inteiro'
};

// Menu
$('.picture').ready(function(e) {
	$('.profile-options').slideUp();
});

$('.picture').on('click', function(e) {
	e.preventDefault();
	if ($('.profile-options').attr('class') == 'profile-options') {
		$('.profile-options').addClass('active');
		$('.profile-options').slideDown();
	} else {
		$('.profile-options').removeClass('active');
		$('.profile-options').slideUp();
	}
});

$('.layout-menu li').ready(function(e) {
	$('ul li ul').slideUp();
});

$('.layout-menu li').on('click', 'span', function(e) {
	if ($(this).parent().children('ul').length) {
		e.preventDefault();
		$(this).addClass('active');
		$(this).parent().children('ul').slideDown();
	}
});

$('.layout-menu li').on('click', 'span.active', function(e) {
	e.preventDefault();
	$(this).removeClass('active');
	$(this).parent().children('ul').slideUp();
});

// Toggle
$('.menu-btn').on('click', function(e) {
	e.preventDefault();
	e.stopPropagation();
	$('.layout-sidebar').toggleClass('active');
});

$('.menu-btn').on('click', '.layout-sidebar.active', function(e) {
	e.preventDefault();
	e.stopPropagation();
	$('.layout-sidebar').removeClass('active');
});

// Redirect
function redirectTo(url) {
	window.location = "http://localhost:8080/Acougue_TopicosII/faces" + url;
}

function maintenance() {
	alert("Desculpe, esta opção está em manutenção. Em breve retornaremos.");
}