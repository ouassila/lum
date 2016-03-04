<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
</head>
<body>
	<?php
		try
		{
			$bdd = new PDO('mysql:host=192.168.147.115;dbname=domoteam;charset=utf8', 'test', 'toto');
		}
		catch(Exception $e)
		{
			die('Erreur : '.$e->getMessage());
		}

		print "<b>GET:</b><br />";
		print_r($_GET);
		print "<br /><br /><b>POST:</b><br />";
		print_r($_POST);

		$req = $bdd->prepare('INSERT INTO temperature(date, heure, valeur) VALUES(:date, :heure, :valeur)');
		$req->execute(array(
			'date' => $_POST['date'],
			'heure' => $_POST['heure'],
			'valeur' => $_POST['tValeur']
		));
		print "<br />---";
		print "<br />temp envoyer";

		$req = $bdd->prepare('INSERT INTO humidity(date, heure, valeur) VALUES(:date, :heure, :valeur)');
		$req->execute(array(
			'date' => $_POST['date'],
			'heure' => $_POST['heure'],
			'valeur' => $_POST['hValeur']
		));
		print "<br />---";
		print "<br />humidity envoyer";

		$req = $bdd->prepare('INSERT INTO lum1(date, heure, valeur) VALUES(:date, :heure, :valeur)');
		$req->execute(array(
			'date' => $_POST['date'],
			'heure' => $_POST['heure'],
			'valeur' => $_POST['l1Valeur']
		));
		print "<br />---";
		print "<br />lum1 envoyer";

		$req = $bdd->prepare('INSERT INTO lum2(date, heure, valeur) VALUES(:date, :heure, :valeur)');
		$req->execute(array(
			'date' => $_POST['date'],
			'heure' => $_POST['heure'],
			'valeur' => $_POST['l2Valeur']
		));
		print "<br />---";
		print "<br />lum2 envoyer";

		$req = $bdd->prepare('INSERT INTO lum3(date, heure, valeur) VALUES(:date, :heure, :valeur)');
		$req->execute(array(
			'date' => $_POST['date'],
			'heure' => $_POST['heure'],
			'valeur' => $_POST['l3Valeur']
		));
		print "<br />---";
		print "<br />lum3 envoyer";
	?>
</body>
</html>
