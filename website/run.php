<html>
<head>
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/one-page-wonder.css" rel="stylesheet">
</head>
<body>
<script type="text/javascript">

function scroll() {
	var o_box = document.getElementById("overflow-box");
	
	if(o_box.scrollTop < (o_box.scrollHeight - o_box.offsetHeight)) {
		o_box.scrollTop = o_box.scrollTop + 20;
	}	
}

function scrollCheck() {
	if(document.getElementById("copyright") == null) {
		scroll();
	}
}

setInterval(scrollCheck, 25);		
	
</script>
<div class="headline">
	<div class="container text-center">
		<h2 class="header-round">Genetic Programming Portfolio</h2>
	</div>
</div>
<hr>
<div class="container">
	<?php 
		// Setup params file first. 
		$crossover = floatval($_POST['crossover-rate']) == 0 ? 0.9 : floatval($_POST['crossover-rate']);
		$mutation = floatval($_POST['mutation-rate']) == 0 ? 0.1 : floatval($_POST['mutation-rate']);
		$population = intval($_POST['population']) == 0 ? 500 : intval($_POST['population']);
		$generations = intval($_POST['generations']) == 0 ? 51 : intval($_POST['generations']);
	?>
	
	<div class="container text-center">
		<h2 class="header-round">Your GPP Output</h2>
		
		<p class="lead">
			Please be patient and avoid refreshing the page. A notification will popup when the execution is complete.
		</p>
	</div>
	<div class="container">
		<p class="lead">
			<span>Interpretation of Output</span> - Fitness is a representation of how strong a match any individual candidate
			solution is to the target problem. In symbolic regression, this is commonly the squared errors between the functions.
			<span>Standardized fitness</span> is proportional to the problem sets data, and low is better as you are taking a sum of the 
			differences between the curves (target function curve and candidate solution curve). Similarly, <span>adjusted fitness</span> 
			is the same metric but normalized to a [0, 1] scale. In this case, higher is better with 1.0 being a perfect fitting
			function. <span>Hits</span> aren't used anywhere in the fitness function, and as such should be taken lightly in interpreting the data.
			They provide a convenient way to quickly approximate how good a solution is for human readers, but are subject to change 
			based on the user specified hit radius. In most cases, the hit radius is 1-3% of the max value in the target function.
		</p>
	</div>
	<div class="container text-center">
		<table style="width:100%">
			<th class="form-table">Crossover Rate</th>
			<th class="form-table">Mutation Rate</th>
			<th class="form-table">Population Size</th>
			<th class="form-table">Generations</th>
			<tr>
				<td class="form-table"><?php echo $crossover ?></td>
				<td class="form-table"><?php echo $mutation ?></td>
				<td class="form-table"><?php echo $population ?></td>
				<td class="form-table"><?php echo $generations ?></td>				
			</tr>
		</table>
	</div>
	
	<div id="overflow-box" class="overflow-box">
	<?php
		
		$data = file("website.params");
		
		foreach($data as $k => $v) {
			if(strpos($data[$k], "generations =") !== false) {
				$data[$k] = "generations = " . $generations . "\n";
			}
			
			if(strpos($data[$k], "pop.subpop.0.size") !== false) {
				$data[$k] = "pop.subpop.0.size = " . $population . "\n";
			}
			
			if(strpos($data[$k], "pop.subpop.0.species.pipe.source.0.prob") !== false) {
				$data[$k] = "pop.subpop.0.species.pipe.source.0.prob = " . $crossover . "\n";
			}
			
			if(strpos($data[$k], "pop.subpop.0.species.pipe.source.2.prob") !== false) {
				$data[$k] = "pop.subpop.0.species.pipe.source.2.prob = " . $mutation . "\n";
			}
		}
		
		$data = implode($data);
		$file = fopen("website.params", "w+");
		
		fwrite($file, $data);
		fclose($file);
	
		ob_start();
		$buffer = str_repeat(" ", 4096)."\r\n<span></span>\r\n";
		
		$desc = array(
			0 => array('pipe', 'r'),
			1 => array('pipe', 'w'),
			2 => array('pipe', 'w')
		);
		
		$pipes = array();
		$process = proc_open("sudo java -jar GeneticProgrammingPortfolio.jar -file website.params", $desc, $pipes);
		
		if(is_resource($process)) {
			fclose($pipes[1]);
			echo "<hr>";
			while ($f = fgets($pipes[2])) {
				echo $buffer, "<br>", $f;
				ob_flush();
				flush();
			}
			fclose($pipes[2]);
			proc_close($process);
		}
		
		ob_end_flush();
	?>
	<div class="container">
	<?php
		
		// out.stat file processing is interrupted by parallel executions in web browser. 
		// This results in "best of run" line not being the end of the file.  Fix this.
		// echo "<hr>";
		
		// $outstat = fopen("out.stat", "r");
		
		// if(!$outstat) die("Cannot find out.stat");
		
		// $read_condition = false;
		
		// while($f = fgets($outstat)) {
		// 	if($read_condition || strpos($f, "Run") !== false) {
		// 		echo $f, "<br>";	
		// 		$read_condition = true;	
		// 	}
		// }
		
		echo "<script type='text/javascript'>var f=true;alert('Done!');</script>";
		
	?>
	</div>
</div>
<hr>
<div class="container text-center">
	<p id="copyright">Copyright &copy; James Earle 2015</p>
</div>
<br>
</body>
</html>