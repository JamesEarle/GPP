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

hit_arr = []
std_arr = []
adj_arr = []
i = 0

with open("hits.txt") as h, open("stdfit.txt") as s, open("adjfit.txt") as a:
	for line in h:
		hit_arr.append(float(line))
		x_arr.append(i)
		i += 1
	for line in s:
		std_arr.append(float(line))
	for line in a:
		adj_arr.append(float(line))

trace0 = go.Scatter(
	x = x_arr, 
	y = hit_arr,
	name = 'Hits',
	line = dict(
		color = ('red'),
		width = 4
	)
)

trace1 = go.Scatter(
	x = x_arr, 
	y = std_arr,
	name = 'Standardized Fitness',
	line = dict(
		color = ('blue'),
		width = 4
	)
)

trace2 = go.Scatter(
	x = x_arr, 
	y = adj_arr,
	name = 'Adjusted Fitness',
	line = dict(
		color = ('orange'),
		width = 4
	)
)

plot_url1 = py.plot([trace0], filename='line-plot')
plot_url2 = py.plot([trace1], filename='line-plot')
plot_url3 = py.plot([trace2], filename='line-plot')

print(plot_url1)
print(plot_url2)
print(plot_url3)

