<%@page import="Modele.Environnement"%>
<%@ page import="Modele.Multiprise"%>
<%@ page import="Modele.Prise"%>
<%@ page import="Modele.Environnement"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	String retour = (String) request.getAttribute("retour");
	Multiprise multiprise = (Multiprise) request.getAttribute("multiprise");
	Environnement environnement = (Environnement) request.getAttribute("environnement");
	String resultat = (String) request.getAttribute("resultat");
	List<Prise> prises = multiprise.getPrises();
	Map<Integer, HashMap<String, Integer>> suivi = (Map<Integer, HashMap<String, Integer>>) request
			.getAttribute("suivi");

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MONTH, -1);
	Date now = new Date();
%>
<!DOCTYPE html>
<html lang="fr">

<head>
<%@ page contentType="text/html; charset=UTF-8"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>LUM</title>

<!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
<link href="./Vue/css/bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="./Vue/css/freelancer.css" rel="stylesheet">
<link href="./Vue/css/site.css" rel="stylesheet">
<!-- <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet"> -->
<link href="./Vue/css/daterangepicker.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="./Vue/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.0/css/bootstrap-toggle.min.css"
	rel="stylesheet">

</head>

<body id="page-top" class="index">

	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header page-scroll">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#page-top">LUM</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="hidden"><a href="#page-top"></a></li>
					<li class="page-scroll"><a href="#portfolio">Etat Actuel</a></li>
					<li class="page-scroll"><a href="#about">Configuration</a></li>
					<li class="page-scroll"><a href="#bilan">Suivi</a></li>
					<li class="page-scroll"><a href="#historique">Historique</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<!-- Header -->
	<header>
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="col-lg-8 col-lg-offset-2">
						<img class="img-responsive" src="./Vue/img/logo.png" alt="LUM">
					</div>
				</div>
				<div class="row">
					<div class="intro-text">
						<span class="name">LUM</span>
						<hr class="star-light">
						<span class="skills">La multiprise connectée</span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<span id="erreur"></span>

	<!-- Portfolio Grid Section -->
	<section id="portfolio">
		<div id="current_container" class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Etat Actuel</h2>
					<hr class="star-primary">
				</div>
			</div>
			<div class="row">
				<div class="row">
					<div class="col-lg-12 text-center" style="margin-top: 50px;">
						<h4>Etats des prises</h4>
					</div>
				</div>

				<div class="row">

					<%
						for (int i = 0; i < prises.size(); i++) {
					%>
					<div
						class="<%=prises.size() < 3 ? "col-sm-6" : "col-sm-4"%> portfolio-item text-center <%=i == 0 ? "col-lg-offset-2" : ""%>">
						<label>Prise <%=i + 1%></label> <i class="fa fa-power-off fa-3x"
							data-allume="<%=prises.get(i).isAllume()%>"></i>
					</div>
					<%
						}
					%>
				</div>
				<div class="row">
					<div class="col-lg-12 text-center" style="margin-top: 30px;">
						<h4>Environnement</h4>
					</div>
				</div>
				<div class="row">

					<div class="col-sm-6 portfolio-item text-center">
						<p>Température actuelle</p>
						<div class="GaugeMeter"
							data-total="<%=multiprise.getMax_temperature()%>"
							data-used="<%=environnement.getTemperature()%>"
							data-text="<%=environnement.getTemperature()%>"
							data-label="&deg;C" data-width="8" data-theme="Green-Gold-Red"
							data-size="200"></div>
					</div>
					<div class="col-sm-6 portfolio-item text-center">
						<p>Taux d'humidité</p>
						<div class="GaugeMeter"
							data-percent="<%=environnement.getHumidite()%>" data-label="%"
							data-width="8" data-size="200"></div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- About Section -->
	<section class="success" id="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Configuration</h2>
					<hr class="star-light">
				</div>
			</div>
			<div class="row">
				<form role="form" id="formConfig">
					<input type="hidden" name="operation" value="save" />

					<div class="col-lg-16 text-center">
						<input type="hidden" name="mac" value="<%=multiprise.getMac()%>" />
						<%
							for (int i = 0; i < prises.size(); i++) {
						%>

						<div class="<%=prises.size() < 3 ? "col-lg-6" : "col-lg-4"%>">
							<label>Prise <%=i + 1%> :
							</label> <input data-toggle="toggle" type="checkbox" data-onstyle="info"
								name="etat_<%=i + 1%>" data-offstyle="danger"
								<%=prises.get(i).isAllume() ? "checked" : ""%>> <input
								type="hidden" name="id_<%=i + 1%>"
								value="<%=prises.get(i).getId()%>" />
						</div>
						<%
							}
						%>
					</div>
					<div class="row">
						<div class="col-lg-12 text-center"
							style="margin-top: 50px; margin-bottom: 20px;">
							<p>Les seuils de températures et d'humidités :</p>
						</div>
					</div>
					<div class="col-lg-8 col-lg-offset-2 text-center">
						<div class="row control-group">
							<label class="col-sm-4 control-label">Température :</label>
							<div class="col-sm-2">
								<input type="number" class="form-control" name="min_temp"
									id="min_temp" placeholder="Min"
									value="<%=multiprise.getMin_temperature()%>" />
							</div>
							<div class="col-sm-2">
								<input type="number" class="form-control" name="max_temp"
									id="max_temp" placeholder="Max"
									value="<%=multiprise.getMax_temperature()%>" />
							</div>
						</div>
					</div>
					<div class="col-lg-8 col-lg-offset-2 text-center">
						<div class="row control-group">
							<label class="col-md-4 control-label">Humidité :</label>
							<div class="col-sm-2">
								<input type="number" class="form-control" name="min_humd"
									id="min_humd" placeholder="Min"
									value="<%=multiprise.getMin_humidite()%>" />
							</div>
							<div class="col-sm-2">
								<input type="number" class="form-control" name="max_humd"
									id="max_humd" placeholder="Max"
									value="<%=multiprise.getMax_humidite()%>" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 text-center"
							style="margin-top: 50px; margin-bottom: 20px;">
							<p>Les données personnelles :</p>
						</div>
					</div>

					<%
						if (multiprise.getContact().size() > 0) {
							for (int i = 0; i < multiprise.getContact().size(); i++) {
					%>
					<div class="col-lg-12 details_contact">
						<div class="row control-group">
							<label class="col-md-2 control-label text-center">Email :</label>
							<div class="col-lg-4">
								<input type="email" class="form-control" name="email[]"
									id="email_<%=multiprise.getContact().get(i).getId()%>"
									placeholder="Email"
									value="<%=multiprise.getContact().get(i).getMail()%>"
									readonly="readonly" />
							</div>
							<label class="col-md-2 control-label text-center">Téléphone
								:</label>
							<div class="col-lg-2">
								<input type="text" class="form-control" name="telephone[]"
									id="telephone_<%=multiprise.getContact().get(i).getId()%>"
									placeholder="Téléphone"
									value="<%=multiprise.getContact().get(i).getTelephone()%>"
									readonly="readonly" />
							</div>
							<div class="col-xs-3 col-lg-2">
								<a class="btn btn-danger btn-sm rmContact confirm"
									id="<%=multiprise.getContact().get(i).getId()%>" href="#"><i
									class="fa fa-trash-o" style="color: white"></i></a>
								<%
									if (i == 0) {
								%>
								<a class="btn btn-info btn-sm addContact" href="#"><i
									class="fa fa-plus-circle" style="color: white"></i></a>
								<%
									}
								%>
							</div>
						</div>
					</div>
					<%
						}
						} else {
					%>
					<div class="col-lg-12 details_contact">
						<div class="row control-group">
							<label class="col-md-2 control-label text-center">Email :</label>
							<div class="col-lg-4">
								<input type="email" class="form-control" name="email[]"
									id="email_0" placeholder="Email" value="" />
							</div>
							<label class="col-md-2 control-label text-center">Téléphone
								:</label>
							<div class="col-lg-2">
								<input type="text" class="form-control" name="telephone[]"
									id="telephone_0" placeholder="Téléphone" value="" />
							</div>
							<div class="col-xs-3 col-lg-2">
								<a class="btn btn-danger btn-sm rmContact confirm" id="0"
									href="#"><i class="fa fa-trash-o" style="color: white"></i></a>

								<a class="btn btn-info btn-sm addContact" href="#"><i
									class="fa fa-plus-circle" style="color: white"></i></a>
							</div>
						</div>
					</div>
					<%
						}
					%>

					<div class="col-lg-8 col-lg-offset-2 text-center">
						<input type="submit" class="btn btn-lg btn-outline"
							value="Enregistrer" />
					</div>

				</form>
			</div>
		</div>
	</section>

	<!-- Bilan Section -->
	<section id="bilan">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Suivi Conso*</h2>
					<hr class="star-primary">
				</div>
			</div>
			<div class="row">
				<%
					for (int k = 0; k < prises.size(); k++) {
				%>
				<div
					class="<%=prises.size() < 3 ? "col-lg-6" : "col-lg-4"%> text-center">
					<h4>
						Prise
						<%=k + 1%></h4>
					<%
						for (int j = 0; j < suivi.get(prises.get(k).getId()).size(); j++) {
					%>
					<p>
						Allumée durant <strong><%=suivi.get(prises.get(k).getId()).get("conso")%></strong> h
					</p>
					<p>
						Soit <strong><%=(Math.round(suivi.get(prises.get(k).getId()).get("conso") * 0.20))%>
							€</strong> consommés**

						<%
						}
					%>
					
				</div>
				<%
					}
				%>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 text-center">
				<span><i>*Consommation du <%=dateFormat.format(cal.getTime())%>
						au <%=dateFormat.format(now)%></i></span> <br /> <span><i>**Sur
						la base de 0,20 € / heures</i></span>
			</div>
		</div>
		</div>
	</section>

	<!-- Historique Section -->
	<section id="historique" class="success2">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Historique</h2>
					<hr class="star-primary">
				</div>
			</div>
			<!--<form method="post" role="form" action="ShowCharts" id="formShow">  -->
			<form method="post" role="form" action="ShowCharts" id="formShow">
				<input type="hidden" name="operation" value="show" /> <input
					type="hidden" name="mac" value="<%=multiprise.getMac()%>" />

				<div class="row">
					<div class="col-lg-6 text-center col-lg-offset-3">
						<div id="reportrange" class="pull-right"
							style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; width: 100%">
							<i class="fa fa-calendar"></i>&nbsp; <span></span> <b
								class="caret"></b>
						</div>
						<input type="hidden" id="periode" name="periode" value="" />
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6 text-center col-lg-offset-3">
						<div class="form-group">
							<select class="form-control" name="datas" id="datas">
								<option value="" selected disabled>Veuillez
									séléctionner les données à afficher</option>
								<option value="temperature">Température</option>
								<option value="humidite">Humidité</option>
								<%
									for (int i = 0; i < multiprise.getPrises().size(); i++) {
								%>
								<option value="etat_<%=multiprise.getPrises().get(i).getId()%>">Etat
									Prise
									<%=i + 1%></option>
								<%
									}
								%>
							</select>
						</div>
					</div>
				</div>
				<div class="col-lg-8 col-lg-offset-2 text-center">
					<input type="submit" class="btn btn-lg btn-success" value="Valider" />
				</div>
			</form>
		</div>
	</section>
	<section id="graphique" style="display: none">
		<div id="graphique_container" class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Graphiques</h2>
					<hr class="star-primary">
				</div>
			</div>
			<div class="row">
				<input type="hidden" id="datas_charts" value="" />
				<div class="col-lg-12 text-center">
					<div id="chartdiv"></div>
				</div>
			</div>
		</div>
	</section>

	<!-- Footer -->
	<footer class="text-center">
		<div class="footer-below">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">Copyright &copy; LUM Energie 2016</div>
				</div>
			</div>
		</div>
	</footer>

	<!-- jQuery -->
	<script type="text/javascript" src="Vue/js/jquery.js"></script>
	<script type="text/javascript" src="Vue/js/moment.js"></script>


	<!-- Charts -->
	<script src="./Vue/js/amcharts.js"></script>
	<script src="./Vue/js/serial.js"></script>
	<script src="./Vue/js/light.js"></script>
	<script src="Vue/js/chart.responsive.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script type="text/javascript" src="Vue/js/bootstrap.min.js"></script>

	<!-- <script type="text/javascript" src="Vue/js/bootstrap-datetimepicker.min.js"></script> -->
	<script type="text/javascript" src="Vue/js/daterangepicker.js"></script>
	<script type="text/javascript" src="Vue/js/fr.js"></script>

	<script
		src="https://gitcdn.github.io/bootstrap-toggle/2.2.0/js/bootstrap-toggle.min.js"></script>
	<script src="Vue/js/jquery.validate.min.js"></script>
	<!-- humidite -->
	<script src="./Vue/js/gaugeMeter-2.0.0.min.js"></script>
	<script src="./Vue/js/jquery.cookie.js"></script>
	<script src="./Vue/js/jquery.confirm.min.js"></script>

	<script src="Vue/js/site.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="./Vue/js/classie.js"></script>
	<script src="./Vue/js/cbpAnimatedHeader.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="./Vue/js/freelancer.js"></script>

	<!-- humidite -->
	<script src="./Vue/js/gaugeMeter-2.0.0.min.js"></script>

</body>

</html>
