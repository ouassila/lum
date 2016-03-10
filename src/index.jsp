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
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-switch.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/freelancer.css" rel="stylesheet">
<link href="css/site.css" rel="stylesheet">
<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">

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
						<span class="skills">La multi-prises connéctée</span>
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
				<div class="col-lg-8 col-lg-offset-2">
					<div class="col-sm-2">
						<h4>Prise 1</h4>
					</div>

					<div class="col-xs-2">
						<img src="img/portfolio/ampoule.png" class="img-responsive"
							alt="prise 1">
					</div>
					<div class="col-sm-2">
						<h4>Prise 2</h4>
					</div>

					<div class="col-xs-2">
						<img src="img/portfolio/ampoule.png" class="img-responsive"
							alt="prise 2">
					</div>

					<div class="col-sm-2">
						<h4>Prise 3</h4>
					</div>

					<div class="col-xs-2">
						<img src="img/portfolio/ampoule.png" class="img-responsive"
							alt="prise 3">
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 text-center">
						<h4>Environnement</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-8 col-lg-offset-4">
						<div class="tempGauge-demo">25&deg;C</div>
					</div>

					<div class="col-lg-2">
						<label>Taux d'humidit�</label>
						<div class="GaugeMeter" data-percent="40" data-label="%"
							data-width="8"></div>
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
				<div class="col-lg-4 col-lg-offset-2">
					<p>Freelancer is a free bootstrap theme created by Start
						Bootstrap. The download includes the complete source files
						including HTML, CSS, and JavaScript as well as optional LESS
						stylesheets for easy customization.</p>
				</div>
				<div class="col-lg-4">
					<p>Whether you're a student looking to showcase your work, a
						professional looking to attract clients, or a graphic artist
						looking to share your projects, this template is the perfect
						starting point!</p>
				</div>
				<div class="col-lg-8 col-lg-offset-2 text-center">
					<a href="#" class="btn btn-lg btn-outline"> <i
						class="fa fa-download"></i> Download Theme
					</a>
				</div>
			</div>
		</div>
	</section>

	<!-- Contact Section -->
	<section id="contact">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Historique</h2>
					<hr class="star-primary">
				</div>
			</div>
			<div class="col-sm-6" style="height: 75px;">
				<div class='col-md-5'>
					<div class="form-group">
						<div>Start</div>

						<div class='input-group date' id='startDate'>
							<input type='text' class="form-control" name="startDate" /> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span> </span>
						</div>
					</div>
				</div>
				<div class='col-md-5'>
					<div class="form-group">
						<div>End</div>

						<div class='input-group date' id='endDate'>
							<input type='text' class="form-control" name="org_endDate" /> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span> </span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Footer -->
	<footer class="text-center">
		<div class="footer-above">
			<div class="container">
				<div class="row">
					<div class="footer-col col-md-4">
						<h3>Location</h3>
						<p>
							3481 Melrose Place<br>Beverly Hills, CA 90210
						</p>
					</div>
					<div class="footer-col col-md-4">
						<h3>Around the Web</h3>
						<ul class="list-inline">
							<li><a href="#" class="btn-social btn-outline"><i
									class="fa fa-fw fa-facebook"></i></a></li>
							<li><a href="#" class="btn-social btn-outline"><i
									class="fa fa-fw fa-google-plus"></i></a></li>
							<li><a href="#" class="btn-social btn-outline"><i
									class="fa fa-fw fa-twitter"></i></a></li>
							<li><a href="#" class="btn-social btn-outline"><i
									class="fa fa-fw fa-linkedin"></i></a></li>
							<li><a href="#" class="btn-social btn-outline"><i
									class="fa fa-fw fa-dribbble"></i></a></li>
						</ul>
					</div>
					<div class="footer-col col-md-4">
						<h3>About Freelancer</h3>
						<p>
							Freelance is a free to use, open source Bootstrap theme created
							by <a href="http://startbootstrap.com">Start Bootstrap</a>.
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="footer-below">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">Copyright &copy; Your Website 2014</div>
				</div>
			</div>
		</div>
	</footer>

	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	<script src="js/site.js"></script>
	<script src="js/moment.js"></script>
	<script src="js/transition.js"></script>
	<script src="js/collapse.js"></script>



	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<script src="js/bootstrap-datetimepicker.min.js"></script>



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

</body>

</html>
