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
<div class="container text-center">

	<div class="container">
		<h2 class="header-round">About Me</h2>
		
		<div class="container text-center">
			<img style="border-radius:50%;margin: 15px;padding: 0px;border: 2px solid rgb(51, 51, 51);" src="img/portrait-350x350.jpg" alt="">
		</div>

		<p class="lead">
			Hi! My name is James Earle a Computer Science student from Brock 
			University, in St. Catharines, Ontario. This website 
			acts as a portal into the latest research for GPP, my
			undergraduate thesis focusing the applications of 
			Genetic Programming as a tool to forecast stock prices
			using symbolic regression. Here you can see graphs of
			different output, the various parameters, or the latest
			changes in the project. If you want to read more about 
			my other experiences, check out <a href="http://www.jamesearle.ca" target="_blank">my personal website.</a>
			Feel free to email me at <a href="mailto:je11zi@brocku.ca">je11zi@brocku.ca</a>
			or check out the homepage of my supervisor
			<a href="http://sandcastle.cosc.brocku.ca/~bross/">Brian Ross.</a>
		</p>
	</div>

	<hr>

	<div class="container">
		<h2 class="header-round">About GPP</h2>

		<div class="container text-center">
			<img style="margin:15px" src="img/11-25-2015_sp500.png" alt="">
		</div>

		<p class="lead">
			Genetic Programming (GP) is a powerful technique in artificial intelligence 
			that creates a <emph>population</emph> of candidate solutions to a given problem, 
			and then "evolves" that population to reach a good approximation of an optimal solution. 
			In an attempt to forecast stock prices based on historical data, we use symbolic regression of
			a target function, being the above training data. This data represents the S&amp;P 500 Index daily
			values for a period of 10 years (2005-2015). 
		</p>
		<p class="lead">
			The GP system uses <a href="https://cs.gmu.edu/~eclab/projects/ecj/">ECJ</a>
			an extensive Java library that implements the bulk of the tree-based GP definitions. 
			By adding multithreading to this library, computation times were reduced 35-50%. It is expected
			that it will be further reduced in the data sampling patterns to be adjusted for this reason.
			Statistical values regarding fitness are output into a generated file for reach run, and then a 
			Python script aggregates these 20 files of 3 columns each into 3 averaged files. Currently, these
			averaged files are just brought into excel for graphing, but in the future I intend to automate the 
			graphing process, as well as updating the information displayed below automatically on executions.
		</p>
		<p class="lead">
			In the below executions, we've experimented with only the number of generations in a single run of the GP system,
			as well as the population size. It is clear that the population size of 1000 is advantageous, and that an increased
			number of generations does yield some benefits, but only marginally so and at an high cost due to computation times.
		</p>
	</div>
	<div class="container text-center">
		<table style="width:100%">
			<th>Pop Size</th>
			<th>Generations</th>
			<th>Crossover</th>
			<th>Mutation</th>
			<th>Reproduction</th>
			<th>Min/Max Depth</th>
			<th>Hit Radius</th>
			<th>Graph Key</th>
			<th>Runtime</th>
			<th>Runs</th>
			<tr>
				<td>1000</td>
				<td>51</td>
				<td>0.9</td>
				<td>0.08</td>
				<td>0.02</td>
				<td>2, 6</td>
				<td>2.5%</td>
				<td><span>A</span></td>
				<td>14m10s</td>
				<td>20</td>
			</tr>
			<tr>
				<td>500</td>
				<td>51</td>
				<td>0.9</td>
				<td>0.08</td>
				<td>0.02</td>
				<td>2, 6</td>
				<td>2.5%</td>
				<td><span>B</span></td>
				<td>9m30s</td>
				<td>20</td>
			</tr>
			<tr>
				<td>1000</td>
				<td>101</td>
				<td>0.9</td>
				<td>0.08</td>
				<td>0.02</td>
				<td>2, 6</td>
				<td>2.5%</td>
				<td><span>C</span></td>
				<td>31m16s</td>
				<td>20</td>
			</tr>
			<tr>
				<td>500</td>
				<td>101</td>
				<td>0.9</td>
				<td>0.08</td>
				<td>0.02</td>
				<td>2, 6</td>
				<td>2.5%</td>
				<td><span>D<span></td>
				<td>17m4s</td>
				<td>20</td>
			</tr>
		</table>
	</div>
	<div class="container text-center">
		<img style="margin:15px" src="img/11-25-2015_std.png" alt="">
		<img style="margin:15px" src="img/11-25-2015_adj.png" alt="">
		<img style="margin:15px" src="img/11-25-2015_hit.png" alt="">
	</div>

<?php
	
	$rules = ['.jpg', '.JPG', '.png'];
	$files = scandir('img');

	foreach($files as $f) {
		foreach($rules as $r) {
			if(strpos($f, $r)) {
				echo "";
			}
		}
	}
?>
</div>
<footer>
	<div class="container">
		<h2 class="header-round text-center">Parameter Definitions and Information</h2>
		<hr>
		<ul>
			<li>
				<p class="lead">
					<span>Population Size</span> - Initially randomly generated using 
					<a href="http://cswww.essex.ac.uk/staff/poli/gp-field-guide/51ConstructingtheInitialPopulation.html">Koza's Ramped Half-and-Half</a> approach,
					this is the number of candidate solutions that exist at any time in a single generation. They will breed according
					to the set crossover and mutation rates
				</p>
			</li>
			<li>
				<p class="lead">
					<span>Number of Generations</span> - This is the maximum number of generations that a run is allowed to execute for before being halted. If an ideal solution 
					is found prior to this (having a fitness close enough to 0) it will end prematurely. This is rarely the case when considering larger complex functions 
					like what is examined above.
				</p>
			</li>
			<li>
				<p class="lead">
					<span>Crossover Rate</span> - This is the rate at which crossover evolution occurs between two "parent" nodes in the population to create a new "child" node. As we are 
					using a tree-based representation for the problem set, we do a standard crossover at two randomly selected nodes, swapping their subtrees. This provides us with
					always syntactically correct trees according to the defined language, as well as an effective crossover method.
				</p>
			</li>
			<li>
				<p class="lead">
					<span>Mutation Rate</span> - The rate at which individuals are mutated at random. A node will be selected and randomly altered. This provides an explorative parameter to the 
					problem set, as if were to prematurely converge on a suboptimal solution due to high fitness between generations in crossover, mutation will help climb out.
				</p>
			</li>
			<li>
				<p class="lead">
					<span>Reproduction Rate</span> - This acts as a way to not alter a candidate solution in any way. It will not evolve or mutate, but will be brought into the next generation of 
					individuals at a certain probability completely unchanged. 
				</p>
			</li>
			<li>
				<p class="lead">
					<span>Min/Max Depth</span> - This defines the minimum and maximum tree depths for tree initialization. This uses
					Koza's Ramped Half and Half approach. Note the maximum tree depth for the execution is set to 17.
				</p>	
			</li>
			<li>
				<p class="lead">
					<span>Hit Radius</span> - Although not directly involved in the calculation of fitness, a hit radius is used to evaluate
					when a candidate approximates the target function to a certain degree. It is a useful metric for human interpretation of 
					performance, and usually falls around 1-3% of the maximum value in the target function.
				</p>
			</li>
			<li>
				<p class="lead">
					<span>Runtime</span> - We will keep track of execution times to measure improved efficiency in our sampling methods as well 
					as the expected improvement in efficiency associated with threading the GP system. 
				</p>
			</li>
			<li>
				<p class="lead">
					<span>Number of Runs</span> - To avoid the possibility of a good single run skewing the results and interpretation of what a "good"
					GP system is comprised of, we will average all results across a number of runs, usually between 20-30.
				</p>
			</li>
		</ul>
	</div>
</footer>
<?php 
	if(isset($_POST['button-submit'])) {
		echo "She's set, baby";
		$out = [];
		echo exec("runit", $out);
		
		echo count($out);
	}
?>
<hr>
<div class="container text-center">
	<div id="the-button" class="container tiny">
		<form method="POST" action="run.php">
			<div class="form-group">
				<button id="run-button" type="submit" class="btn btn-default push-the-button">Don't do it</button>
			</div>
			<input type="hidden" name="button-submit" value=true>
		</form>
	</div>
</div>
<hr>
<div class="container text-center">
	<p>Copyright &copy; James Earle 2015</p>
</div>
<br>

<script type="text/javascript">

var button = document.getElementById("run-button");
var bg = document.getElementById("the-button");

button.addEventListener("mouseout", function() {
	button.textContent = "Don't do it";	
	bg.style.background="";
}, true);

button.addEventListener("mouseover", function() {
	button.textContent = "JUST DO IT";	
	bg.style.background="url('img/shia.jpg')";
	bg.style.backgroundPosition="center";
	bg.style.backgroundSize="200px 100px";
	
}, true);
</script>

</body>

</html>
