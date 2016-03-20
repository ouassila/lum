$.validator.addMethod(
		"regex",
		function(value, element, regexp) {
			var re = new RegExp(regexp);
			return this.optional(element) || re.test(value);
		},
		"Format de téléphone incorrect"
);

$( document ).ready(function() {

	//reload de la page toute les 1 min
	/*setInterval(function(){
		$.ajax({
			url: "Home",
			type: "GET",
			data: "",
			success: function() {
				$('#current').load(document.URL + ' #current_container'); 
			}
		});
	}, 30000);
*/
	//gaude de temp et humidite
	$(".GaugeMeter").gaugeMeter();

	//periode pour historique
	function cb(start, end) {
		$('#periode').val(start.format('DD/MM/YYYY HH:mm:ss') + '-' + end.format('DD/MM/YYYY HH:mm:ss'));
		$('#reportrange span').html('Du ' +start.format('DD MMMM YYYY à HH:mm') + ' au ' + end.format('DD MMMM YYYY à HH:mm'));
	}
	cb(moment().subtract(29, 'days'), moment());

	$('#reportrange').daterangepicker({
		ranges: {
			'Aujourd\'hui': [moment(), moment()],
			'Hier': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
			'Depuis 7 jours': [moment().subtract(6, 'days'), moment()],
			'Depuis 30 jours': [moment().subtract(29, 'days'), moment()],
			'Ce mois-ci': [moment().startOf('month'), moment().endOf('month')],
			'Le dernier mois': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
		},
		locale: {
			moment : 'fr',
			cancelLabel: 'Quitter',
			applyLabel: 'Valider',
			format: "DD/MM/YYYY HH:mm:ss",
			customRangeLabel: "Autre",
		},
		autoUpdateInput: false,
	}, cb);

	$('#reportrange').on('apply.daterangepicker', function(ev, picker) {
		$('#periode').val( picker.startDate.format('DD/MM/YYYY HH:mm:ss') + '-' + picker.endDate.format('DD/MM/YYYY HH:mm:ss'));
	});

	//formulaire historique
	$("#formShow").validate({
		rules: {
			datas: {
				required: true,
			},
		},
		errorClass : "invalid",
		validClass: "success",
		errorPlacement: function (error, element) {
			$("#" + $(element).attr("id")).addClass('invalid');
		},
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);                            
		},
		unhighlight: function(element, errorClass) {                  
			$(element).removeClass(errorClass);   
		},
		focusInvalid: false,
		submitHandler: function(form){
			$.ajax({
				url: "ShowCharts",
				type: "POST",
				data: $(form).serialize(),
				success: function(html) {
					$("#graphique").show();

					$('html, body').stop().animate({
						scrollTop: $("#graphique").offset().top
					}, 1500, 'easeInOutExpo');	

					//charts
					var chart = AmCharts.makeChart("chartdiv", {
						"type": "serial",
						"theme": "light",
						"marginRight": 40,
						"marginLeft": 40,
						"autoMarginOffset": 20,
						"dataDateFormat": "YYYY-MM-DD",
						"valueAxes": [{
							"id": "v1",
							"axisAlpha": 0,
							"position": "left",
							"ignoreAxisWidth":true
						}],
						"balloon": {
							"borderThickness": 1,
							"shadowAlpha": 0
						},
						"graphs": [{
							"id": "g1",
							"balloon":{
								"drop":true,
								"adjustBorderColor":false,
								"color":"#ffffff"
							},
							"bullet": "round",
							"bulletBorderAlpha": 1,
							"bulletColor": "#FFFFFF",
							"bulletSize": 5,
							"hideBulletsCount": 50,
							"lineThickness": 2,
							"title": "red line",
							"useLineColorForBulletBorder": true,
							"valueField": "value",
							"balloonText": "<span style='font-size:18px;'>[[value]]</span>"
						}],
						"chartScrollbar": {
							"graph": "g1",
							"oppositeAxis":false,
							"offset":30,
							"scrollbarHeight": 80,
							"backgroundAlpha": 0,
							"selectedBackgroundAlpha": 0.1,
							"selectedBackgroundColor": "#888888",
							"graphFillAlpha": 0,
							"graphLineAlpha": 0.5,
							"selectedGraphFillAlpha": 0,
							"selectedGraphLineAlpha": 1,
							"autoGridCount":true,
							"color":"#AAAAAA"
						},
						"chartCursor": {
							"pan": true,
							"valueLineEnabled": true,
							"valueLineBalloonEnabled": true,
							"cursorAlpha":1,
							"cursorColor":"#258cbb",
							"limitToGraph":"g1",
							"valueLineAlpha":0.2
						},
						"valueScrollbar":{
							"oppositeAxis":false,
							"offset":50,
							"scrollbarHeight":10
						},
						"categoryField": "date",
						"categoryAxis": {
							"parseDates": true,
							"dashLength": 1,
							"minorGridEnabled": true
						},
						"export": {
							"enabled": true
						},
						"dataProvider": $.parseJSON($("#datas_charts").val()),
					});
					/*
					chart.addListener("rendered", zoomChart);

					zoomChart();

					function zoomChart() {
					    chart.zoomToIndexes(chart.dataProvider.length - 40, chart.dataProvider.length - 1);
					}
					 */
				}
			});		
		}
	});

	$("#formConfig").validate({
		rules: {
			min_temp: {
				required: true,
				number: true
			},
			max_temp: {
				required: true,
				number: true
			},
			min_humd: {
				required: true,
				number: true
			},
			max_humd: {
				required: true,
				number: true
			},
			email: {
				required: true,
				email: true,
			},
			telephone: {
				required: true,
				regex: "^0[1-68][0-9]{8}$",
			}
		},
		errorClass : "invalid",
		validClass: "success",
		errorPlacement: function (error, element) {
			$("#" + $(element).attr("id")).addClass('invalid');
		},
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);                            
		},
		unhighlight: function(element, errorClass) {                  
			$(element).removeClass(errorClass);   
		},
		focusInvalid: false
	});

//	ajouter contact
	$('.addContact').click(function(){
		$('.details_contact:visible').last().clone(true).insertAfter('.details_contact:last').find('input').val('');
		$('.details_contact:visible').last().find('.addContact').remove();
		return false;
	});

//	suppr contact	
	$('.rmContact').click(function(){
		if($('.details_contact:visible').length > 1 ){
			$(this).parent().parent().parent().hide();
		}
		else{
			$('.details_contact:first').find('input').val('');
		}
		return false;
	});	
});