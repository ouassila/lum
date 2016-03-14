<!DOCTYPE html>
<html lang="fr">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>LUM</title>

<!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-switch.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/freelancer.css" rel="stylesheet">
<link href="css/site.css" rel="stylesheet">
<!-- <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet"> -->
<link href="css/daterangepicker.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
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
					<li class="page-scroll"><a href="#bilan">Bilan</a></li>
					<li class="page-scroll"><a href="#contact">Historique</a></li>
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
						<img class="img-responsive" src="img/logo.png" alt="LUM">
					</div>
				</div>
				<div class="row">
					<div class="intro-text">
						<span class="name">LUM</span>
						<hr class="star-light">
						<span class="skills">La multiprise connéctée</span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Portfolio Grid Section -->
	<section id="portfolio">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Etat Actuel</h2>
					<hr class="star-primary">
				</div>
			</div>
			<div class="row">
				<div class="row">
					<div class="col-lg-12 text-center" style="margin-top: 50px;">
						<h4>Détails de l'état des prises</h4>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-4 portfolio-item">
						<img src="img/portfolio/cabin.png" class="img-responsive" alt="">
						
					</div>
					<div class="col-sm-4 portfolio-item">
						<img src="img/portfolio/cake.png" class="img-responsive" alt="">
						
					</div>
					<div class="col-sm-4 portfolio-item">
						<img src="img/portfolio/circus.png" class="img-responsive"
							alt="">
						
					</div>
				</div>
				<!-- <div class="col-lg-4 col-lg-offset-4 text-center">
					<div class="col-xs-6">
						<label>Prise 1 : </label>
					</div>

					<div class="col-lg-2 col-xs-2">
						<img src="img/portfolio/ampoule.png" class="img-responsive"
							alt="prise 1">
					</div>
					<div class="col-xs-6">
						<label>Prise 2 : </label>
					</div>

					<div class="col-lg-2 col-xs-2">
						<img src="img/portfolio/ampoule.png" class="img-responsive"
							alt="prise 1">
					</div>

					<div class="col-xs-6">
						<label>Prise 3 : </label>
					</div>

					<div class="col-lg-2 col-xs-2">
						<img src="img/portfolio/ampoule.png" class="img-responsive"
							alt="prise 1">
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 text-center" style="margin-top: 50px;">
						<h4>Environnement</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 portfolio-item">
						<div class="col-lg-3 col-lg-offset-2">
							<label>Température actuelle : 5&deg;C</label>
						</div>
						<div class="col-lg-4">
							<div class="tempGauge-demo">5&deg;C</div>
						</div>
					</div>

					<div class="col-sm-12 portfolio-item">
						<div class="col-lg-3 col-lg-offset-2">
							<label>Taux d'humidité :</label>
						</div>
						<div class="GaugeMeter" data-percent="40" data-label="%"
							data-width="8"></div>
					</div>
				</div> -->
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
				<form name="sentConfig" id="configForm" novalidate>

					<div class="col-lg-16 text-center">
						<div class="col-lg-4 ">
							<label>Prise 1 : </label> <input type="checkbox" class="switch"
								name="my-checkbox" checked>
						</div>
						<div class="col-lg-4">
							<label>Prise 2 : </label> <input type="checkbox" class="switch"
								name="my-checkbox" checked>
						</div>
						<div class="col-lg-4">
							<label>Prise 3 : </label> <input type="checkbox" class="switch"
								name="my-checkbox" checked>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 text-center"
							style="margin-top: 50px; margin-bottom: 20px;">
							<p>Définir les seuils de températures et d'humidités</p>
						</div>
					</div>
					<div class="col-lg-8 col-lg-offset-2 text-center">
						<div class="row control-group">
							<label class="col-sm-4 control-label">Température :</label>
							<div class="col-sm-2">
								<input type="number" class="form-control" name="number"
									placeholder="Min" />
							</div>
							<div class="col-sm-2">
								<input type="number" class="form-control" name="number"
									placeholder="Max" />
							</div>
						</div>
					</div>
					<div class="col-lg-8 col-lg-offset-2 text-center">
						<div class="row control-group">
							<label class="col-md-4 control-label">Humidité :</label>
							<div class="col-sm-2">
								<input type="number" class="form-control" name="number"
									placeholder="Min" />
							</div>
							<div class="col-sm-2">
								<input type="number" class="form-control" name="number"
									placeholder="Max" />
							</div>
						</div>
					</div>
					<div class="col-lg-8 col-lg-offset-2 text-center">
						<a href="#" class="btn btn-lg btn-outline"> <i
							class="fa fa-download"></i> Enregistrer
						</a>
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
					<h2>Bilan du mois*</h2>
					<hr class="star-primary">
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 text-center">
					<span>Pour la période du 01/12/2015 au 01/01/2016, votre
						bénéfice est de : </span>
					<h3>10 €</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2 text-center">
					<a href="#" class="btn btn-lg btn-primary"> Calculer le bilan </a>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 text-center">
					<span><i>*Le dernier bilan a été effectué le 01/01/2016</i></span>
				</div>
			</div>
		</div>
	</section>

	<!-- Historique Section -->
	<section id="contact" class="success">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Historique</h2>
					<hr class="star-primary">
				</div>
			</div>
			<form name="sentCharts" id="chartsForm" novalidate>
				<div class="row">
					<div class="col-lg-6 text-center col-lg-offset-3">
						<div id="reportrange" class="pull-right"
							style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; width: 100%">
							<i class="fa fa-calendar"></i>&nbsp; <span></span> <b
								class="caret"></b>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6 text-center col-lg-offset-3">
						<div class="form-group">
							<select class="form-control" name="datas">
								<option value="" selected disabled>Veuillez
									séléctionner les données à afficher</option>
								<option>Température</option>
								<option>Humidité</option>
								<option>Etat Prise 1</option>
								<option>Etat Prise 2</option>
								<option>Etat Prise 3</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-lg-8 col-lg-offset-2 text-center">
					<a href="#" class="btn btn-lg btn-outline"> <i
						class="fa fa-paper-plane-o"></i> Valider
					</a>
				</div>
			</form>
		</div>
	</section>
	<section id="graphique" style="">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Graphiques</h2>
					<hr class="star-primary">
				</div>
			</div>
			<div class="row">
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
					<div class="col-lg-12">Copyright &copy; LUM 2016</div>
				</div>
			</div>
		</div>
	</footer>

	<!-- jQuery -->
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/moment.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>

	<!-- <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script> -->
	<script type="text/javascript" src="js/daterangepicker.js"></script>

	<script type="text/javascript" src="js/fr.js"></script>

	<script type="text/javascript" src="js/bootstrap-switch.min.js"></script>
	<script src="js/site.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="js/classie.js"></script>
	<script src="js/cbpAnimatedHeader.js"></script>

	<!-- Contact Form JavaScript -->
	<script src="js/jqBootstrapValidation.js"></script>
	<script src="js/contact_me.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/freelancer.js"></script>

	<!-- Temperature -->
	<script src="js/jquery.tempgauge.js"></script>

	<!-- humidite -->
	<script src="js/gaugeMeter-2.0.0.min.js"></script>

	<!-- Charts -->
	<script src="js/amcharts.js"></script>
	<script src="js/serial.js"></script>
	<script src="js/light.js"></script>

</body>

</html>
