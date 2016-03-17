function generatechartData() {
	var chartData = [];
	var firstDate = new Date();
	firstDate.setDate(firstDate.getDate() - 150);

	for (var i = 0; i < 30; i++) {
		// we create date objects here. In your data, you can have date strings
		// and then set format of your dates using chart.dataDateFormat property,
		// however when possible, use date objects, as this will speed up chart rendering.
		var newDate = new Date(firstDate);
		newDate.setDate(newDate.getDate() + i);

		var visits = Math.round(Math.random() * 100 - 50);

		chartData.push({
			date: newDate,
			visits: visits
		});
	}
	return chartData;
}

$.validator.addMethod(
		"regex",
		function(value, element, regexp) {
			var re = new RegExp(regexp);
			return this.optional(element) || re.test(value);
		},
		"Format de téléphone incorrect"
);

$( document ).ready(function() {

	$(".GaugeMeter").gaugeMeter();

	function cb(start, end) {
		$('#reportrange span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
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
			format: "DD/MM/YYYY",
			customRangeLabel: "Autre",
		},
		autoUpdateInput: true,
	}, cb);

	$('#periode').val($('#reportrange span').text());

	$('#reportrange').on('apply.daterangepicker', function(ev, picker) {
		$('#periode').val( picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
	});
	
	//charts
	var chartData = generatechartData();

	var chart = AmCharts.makeChart("chartdiv", {
		"theme": "light",
		"type": "serial",
		"marginRight": 80,
		"autoMarginOffset": 20,    
		"marginTop":20,
		"dataProvider": chartData,
		"valueAxes": [{
			"id": "v1",
			"axisAlpha": 0.1
		}],
		"graphs": [{
			"useNegativeColorIfDown": true,
			"balloonText": "[[category]]<br><b>value: [[value]]</b>",
			"bullet": "round",
			"bulletBorderAlpha": 1,
			"bulletBorderColor": "#FFFFFF",
			"hideBulletsCount": 50,
			"lineThickness": 2,
			"lineColor": "#fdd400",
			"negativeLineColor": "#67b7dc",
			"valueField": "visits"
		}],
		"categoryField": "date",
		"categoryAxis": {
			"parseDates": true,
			"axisAlpha": 0,
			"minHorizontalGap": 60
		},
	});

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
		
		onsubmit: function(){
			$("#graphique").fadeIn("slow");
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
	
	//ajouter contact
	$('.addContact').click(function(){
		$('.details_contact:visible').last().clone(true).insertAfter('.details_contact:last').find('input').val('');
		$('.details_contact:visible').last().find('.addContact').remove();
		return false;
	});
	
	//suppr contact	
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