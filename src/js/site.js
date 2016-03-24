$.validator.addMethod(
		"regex",
		function(value, element, regexp) {
			var re = new RegExp(regexp);
			return this.optional(element) || re.test(value);
		},
		"Format de téléphone incorrect"
);

$( document ).ready(function() {

	/*
	//reload de la page toute les 1 min
	setInterval(function(){
		$.ajax({
			url: "Home",
			type: "POST",
			data: "",
			success: function() {
				$('#portfolio').load(document.URL + ' #current_container', function() {
					$(".GaugeMeter").gaugeMeter();
				});
			}
		});
	}, 10000);
	 */

	//gaude de temp et humidite
	$(".GaugeMeter").gaugeMeter();

	//periode pour historique
	function cb(start, end) {
		$('#periode').val(start.format('DD-MM-YYYY HH:mm:ss') + '#' + end.format('DD-MM-YYYY HH:mm:ss'));
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
			format: "DD-MM-YYYY HH:mm:ss",
			customRangeLabel: "Autre",
		},
		autoUpdateInput: false,
	}, cb);

	$('#reportrange').on('apply.daterangepicker', function(ev, picker) {
		$('#periode').val( picker.startDate.format('DD-MM-YYYY HH:mm:ss') + '#' + picker.endDate.format('DD-MM-YYYY HH:mm:ss'));
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
					//$('#graphique').load(document.URL + ' #graphique_container');					

					$('html, body').stop().animate({
						scrollTop: $("#graphique").offset().top
					}, 1500, 'easeInOutExpo');

					var datas = $.parseJSON($.cookie('datas'));

					//charts
					var chart = AmCharts.makeChart("chartdiv", {
						"type": "serial",
						"theme": "light",
						"marginRight": 40,
						"marginLeft": 40,
						"autoMarginOffset": 20,
						"dataDateFormat": "DD-MM-YYYY JJ:NN:SS",
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
							"useNegativeColorIfDown": true,
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
						"chartCursor": {
							"pan": true,
							"valueLineEnabled": true,
							"valueLineBalloonEnabled": true,
							"cursorAlpha":1,
							"cursorColor":"#258cbb",
							"limitToGraph":"g1",
							"valueLineAlpha":0.2
						},
						"categoryField": "date",
						"categoryAxis": {
							"dashLength": 1,
							"minorGridEnabled": true,
							"labelFunction" : function(valueText, serialDataItem, categoryAxis) {	
								if(valueText !=  null){
									for(i = 0; i < datas.length; i++){
										if(datas[i].date.split(" ")[0] != valueText.split(" ")[0] ){
											var tmp = valueText.split(" ")[0];
											var tmp2 = tmp.split("-");
											return tmp2[0] + '/'+ tmp2[1];
										}
									}
									var tmp = valueText.split(" ")[1];
									var tmp2 = tmp.split(":");
									return tmp2[0]+':'+tmp2[1];
								}
								return "";
							}
						},
						"dataProvider": datas,
						"responsive": {
							"enabled": true,
							"rules" : {
								"maxWidth": 300,
								"overrides": {
									"precision": 2,
									"legend": {
										"enabled": false
									},
									"valueAxes": {
										"inside": true
									}
								}
							}
						}
					});	
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
		focusInvalid: false,
		submitHandler: function(form){
			$.ajax({
				url: "SaveDatas",
				type: "POST",
				data: $(form).serialize(),
				success: function(html) {
					location.reload();

					$('html, body').stop().animate({
						scrollTop: $("#about").offset().top
					}, 1500, 'easeInOutExpo');
				}
			});
		}
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
			$.ajax({
				url: "SaveDatas",
				type: "POST",
				data:{
					"operation" : "remove",
					"id" : $(this).attr("id"),
				},
				success: function(html) {
					/*location.reload();

					$('html, body').stop().animate({
						scrollTop: $("#about").offset().top
					}, 1500, 'easeInOutExpo');
					*/
				}
			});
		}
		else{
			$('.details_contact:first').find('input').val('');
		}
		return false;
	});	
});