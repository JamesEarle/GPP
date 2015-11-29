<html>
<head>
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/one-page-wonder.css" rel="stylesheet">
</head>
<body>
<div class="headline">
	<div class="container text-center">
		<h2 class="header-round">Genetic Programming Portfolio</h2>
	</div>
</div>
<hr>
<div class="container">
	
	<div class="container text-center">
		<h2 class="header-round">Your GPP Output</h2>
	</div>
<?php
	
	$desc = array(
		0 => array('pipe', 'r'),
		1 => array('pipe', 'w'),
		2 => array('pipe', 'w')
	);
	
	$pipes = array();
	$process = proc_open("sudo java -jar GeneticProgrammingPortfolio.jar -file filereader/filereader.params", $desc, $pipes);
	
	if(is_resource($process)) {
		fclose($pipes[1]);
		echo "<hr>";
		while ($f = fgets($pipes[2])) {
			echo "<br>";
			echo $f;
		}
		fclose($pipes[2]);
		proc_close($process);
	}
	
	echo "<hr>";
	
	$outstat = fopen("out.stat", "r");
	
	if(!$outstat) die("Cannot find out.stat");
	
	$read_condition = false;
	
	while($f = fgets($outstat)) {
		if($read_condition || strpos($f, "Run") !== false) {
			echo $f, "<br>";	
			$read_condition = true;	
		}
	}
?>
</div>
<hr>
<div class="container text-center">
	<p>Copyright &copy; James Earle 2015</p>
</div>
<br>
</body>
</html>