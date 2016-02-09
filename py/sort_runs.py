import os
import sys
import active
import operator
from os import listdir

## Author: James Earle.
##
## This script parses the output files from 20 separate GPP runs
## and creates a file, 'sorted_inds.txt' displaying all the runs 
## ordered by fitness in terms of their performance on testing data.
## We don't want to graph all of them, so we will not automate that 
## process. Rather, we'd like to hand pick the best, and then 1-2 
## that are average.

# Init an empty dictionary
best_of_run_inds = {}

# outPath = 'docs-img'
# outFolders = listdir(outPath)
# outDayDir = outPath + "/" + outFolders[len(outFolders) - 1]
# outDayFolders = listdir(outDayDir)
# outHourDir = outDayDir + "/" + outDayFolders[len(outDayFolders) - 1]

# Append run number to the beginning of this pattern
pattern = "_best_ind_tree.txt"

# List and search through all the files.
allRunFiles = listdir(active.listActiveDirectory())

# Iterate the 20 necessary files  
for x in range(0, 20):
    patternWithRunNumber = str(x) + pattern
    if((patternWithRunNumber) in allRunFiles):
        with open(outHourDir + "/" + patternWithRunNumber, 'r') as file:
            std_test_fitness = file.readline() # it is the first line in the file
            # Add a float representation to the dictionary. Use string slicing.
            std_test_fitness = float(std_test_fitness[5:len(std_test_fitness)-1])
            best_of_run_inds[patternWithRunNumber] = std_test_fitness;

# Python cannot sort Dictionaries, as they are inherently unsorted. 
# To work around this, the below code returns a list of sorted tuples.
best_of_run_inds_sorted = sorted(best_of_run_inds.items(), key=operator.itemgetter(1))

# Write the results out to the new file in a human-readable manner.
with open(outHourDir + "/sorted_inds.txt", "w") as out:
    for x in range(0, len(best_of_run_inds_sorted)):
        out.write(str(best_of_run_inds_sorted[x]) + "\n")
