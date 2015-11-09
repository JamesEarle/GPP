from os import listdir
from os import walk
import sys

## Author: James Earle.
##
## Use this script to automate statistics processing. We should iterate over 
## the files generated in every run (totalling 25 files) and sum averages
## such that row 1 of every file is summed for standardfitness, adjusted, and hits.
##

# Get the active directory and list all subfolders and files. 
dirPath = '..\GeneticProgrammingPortfolio\out_files'
folders = listdir(dirPath)
activeDir = dirPath + "/" + folders[len(folders)-1]

files = listdir(activeDir)

# Each array will be size 52 at the end. Total summation will occur, divide by 25 runs for avgs.
std = []
adj = []
hit = []

# Initialize each array with the first files contents before summing the rest.
with open(activeDir + "/" + files[0]) as w:
	for line in w:
		arr = line.split("\t")
		std.append(float(arr[0]))
		adj.append(float(arr[1]))
		hit.append(int(arr[2].split('\n')[0])) # last character on line, doesn't need new line char 

# Currently the number of runs in each execution is 25, so we set a max so we only process the 
# 25 most recent run output files.
max = len(files) - 26
if max<-1:
	print("Not enough files to process")
	sys.exit(1)
else:
	print("Number of files is correct")
	for i in range(len(files)-1, max, -1): #decrement through the last 25 runs
		with open(activeDir + "/" + files[i]) as w:
			ind = 0
			for line in w:
				# Values are tab delimited in raw output files, split on this and sum.
				arr = line.split('\t')
				std[ind] += float(arr[0])
				adj[ind] += float(arr[1])
				hit[ind] += int(arr[2].split('\n')[0])
				ind += 1
				
# Finally, write all output to the summed output files as averages (over 25).
with open('stdfit.txt', 'w+') as stdFile, open('adjfit.txt', 'w+') as adjFile, open('hits.txt', 'w+') as hitFile:
	
	for i in range(0, len(std)):
		stdFile.write(str(std[i] / 25) + '\n')
		adjFile.write(str(adj[i] / 25) + '\n')
		hitFile.write(str(float(hit[i]) / 25) + '\n')
		
		# Excel building
			

# And we're happy!
print("Done!")
sys.exit(0)