## Author: James Earle.
##
## Use this script to automate statistics processing. We should iterate over 
## the files generated in every run (totalling 25 files) and sum averages
## such that row 1 of every file is summed for standardfitness, adjusted, and hits.
##

import plotly.plotly as py
import plotly.graph_objs as go
import bs4 as bs
from urllib import parse
import urllib.request
import os
from os import listdir
import sys

py.sign_in('roland23', 'plxezgfw1z')

x_arr = []

hit_arr = []
std_arr = []
adj_arr = []
i = 0

outPath = 'docs-img'
outFolders = listdir(outPath)
outDayDir = outPath + "/" + outFolders[len(outFolders) - 1]
outDayFolders = listdir(outDayDir)
outHourDir = outDayDir + "/" + outDayFolders[len(outDayFolders) - 1]

with open(outHourDir + "/hits.txt") as h, open(outHourDir + "/stdfit.txt") as s, open(outHourDir + "/adjfit.txt") as a:
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

#### Hits Plot ####
plot_url1 = py.plot({
	"data": [trace0],
	"layout": { "title" : "Hits" }
	}, filename='line-plot')

url = str(plot_url1) + "/hits.png"

webFile = urllib.request.urlopen(url)
localFile = open(outHourDir + "/hits.png", 'wb')
localFile.write(webFile.read())
webFile.close()
localFile.close()
#### #### #### ####

#### Stds Plot ####
plot_url1 = py.plot({
	"data": [trace1],
	"layout": { "title" : "Standardized Fitness" }
	}, filename='line-plot')

url = str(plot_url1) + "/standardized-fitness.png"

webFile = urllib.request.urlopen(url)
localFile = open(outHourDir + "/standardized-fitness.png", 'wb')
localFile.write(webFile.read())
webFile.close()
localFile.close()
#### #### #### ####

#### Adjs Plot ####
plot_url1 = py.plot({
	"data": [trace2],
	"layout": { "title" : "Adjusted Fitness" }
	}, filename='line-plot')

url = str(plot_url1) + "/adjusted-fitness.png"

webFile = urllib.request.urlopen(url)
localFile = open(outHourDir + "/adjusted-fitness.png", 'wb')
localFile.write(webFile.read())
webFile.close()
localFile.close()
#### #### #### ####