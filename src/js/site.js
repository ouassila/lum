$( document ).ready(function() {
	$(".tempGauge-demo").tempGauge({

		// border color
		borderColor: "black",
		// border width
		borderWidth: 4,
		// default temperature
		defaultTemp: 26,
		// fill color
		fillColor: "blue",
		// show label
		showLabel:true,
		// label size in pixels
		labelSize: 15,
		// maximum temperature
		maxTemp: 40,
		// minimum temperature
		minTemp: -10,
		// Temperature width
		width: 50
	});

	$(".GaugeMeter").gaugeMeter();
	
	$('#startDate').datetimepicker();
	$('#endDate').datetimepicker();
	
	$("#startDate").on("dp.change",function (e) {
        $('#endDate').data("DateTimePicker").setMinDate(e.date);
	});
	$("#endDate").on("dp.change",function (e) {
        $('#startDate').data("DateTimePicker").setMaxDate(e.date);
	});
});