## Author: James Earle.
##
## Use this script to automate statistics processing. We should iterate over 
## the files generated in every run (totalling 25 files) and sum averages
## such that row 1 of every file is summed for standardfitness, adjusted, and hits.
##

import plotly.plotly as py
import plotly.graph_objs as go

py.sign_in('roland23', 'plxezgfw1z')

x_arr = []
std_arr = []
i = 0

with open("stdfit.txt") as s:
	for line in s:
		std_arr.append(float(line))
		x_arr.append(i)
		i += 1
		
trace = go.Scatter(
	x = x_arr, 
	y = std_arr,
	name = 'Standardized Fitness',
	line = dict(
		color = ('blue'),
		width = 4
	)
)

plot_url = py.plot([trace], filename='line-plot')
print(plot_url)

