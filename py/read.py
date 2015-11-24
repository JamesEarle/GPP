from os import listdir
from os import walk
import sys, argparse

## Author: James Earle.
##
## Use this script to automate statistics processing. We should iterate over 
## the files generated in every run (totalling 25 files) and sum averages
## such that row 1 of every file is summed for standardfitness, adjusted, and hits.
##

parser = argparse.ArgumentParser()

parser.add_argument('numfiles', metavar='N', type=int, nargs='+', help='integer, representing number of files to be processed')
parser.add_argument('--sum', dest='parse', action='store_const', const=sum, default=min, help='')
args = parser.parse_args()

numfiles = args.parse(args.numfiles)

# Get the active directory and list all subfolders and files. 
dirPath = '..\GeneticProgrammingPortfolio\out_files'
folders = listdir(dirPath)
activeDir = dirPath + "/" + folders[len(folders)-1]

files = listdir(activeDir)

# Total summation will occur, divide by 25 runs for avgs.
std = []
adj = []
hit = [] 

# If there are not enough files in the directory, then we will not try to parse them.
max = len(files) - (int(numfiles) + 1)
if max<-1:
	print("Not enough files to process")
	sys.exit(1)
else:
	# Initialize each array with the first files contents before summing the rest.
	with open(activeDir + "/" + files[len(files) - 1]) as w:
		for line in w:
			arr = line.split("\t")
			std.append(float(arr[0]))
			adj.append(float(arr[1]))
			hit.append(int(arr[2].split('\n')[0])) 
			# last character on line, doesn't need new line char
			
	print("Number of files is correct")
	for i in range(len(files) - 2, max, -1): # Decrement through the last 25 runs
		with open(activeDir + "/" + files[i]) as w:
			ind = 0
			for line in w:
				# Values are tab delimited in raw output files, split on this and sum.
				arr = line.split('\t')
				if(ind < len(std)):
					std[ind] += float(arr[0])
					adj[ind] += float(arr[1])
					hit[ind] += int(arr[2].split('\n')[0])
					ind += 1
				else:
					ind = ind
					# Really shouldn't be reaching this case... possible java problem.
				
# Finally, write all output to the summed output files as averages (over 25).
with open('stdfit.txt', 'w+') as stdFile, open('adjfit.txt', 'w+') as adjFile, open('hits.txt', 'w+') as hitFile:
	
	for i in range(0, len(std)):
		stdFile.write(str(std[i] / numfiles) + '\n')
		adjFile.write(str(adj[i] / numfiles) + '\n')
		hitFile.write(str(float(hit[i]) / numfiles) + '\n')

# And we're happy!
print("Done!")
sys.exit(0)