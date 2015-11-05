from os import listdir
from os import walk

## Author: James Earle.
##
## Use this script to automate statistics processing. We should iterate over 
## the files generated in every run (totalling 25 files) and sum averages
## such that row 1 of every file is summed for standardfitness, adjusted, and hits.
##

dirPath = '../GeneticProgrammingPortfolio/out_files'

folders = listdir(dirPath)
print(folders)

activeDir = dirPath + "/" + folders[len(folders)-1]

files = listdir(activeDir)

print(files)
print(len(files))

with open('outfile.txt', 'w+') as o:
	max = len(files) - 26
	# We want to process 25 files, also account for index offset in list
	if max<-1:
		print("Not enough files!")
	else:
		for i in range(len(files)-1, max, -1):
			#o.write(files[i] + '\n')
			with open(activeDir + "/" + files[i]) as w:
				print(w)
				for line in w:
					print(line)
		


# with open('58-04-191_out.txt') as i:
# 	with open('myOutputFile.txt', 'w+') as o:
# 		for line in i:
# 			o.write(line)
# 			print(line)