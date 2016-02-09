import os
import sys
import active
from os import listdir

## Author: James Earle
##
## Ensemble learning is a technique that provides multiplee
## "opinions" as to the solution of a problem. In this sense, we 
## save 20 best-of-run individuals and average their predictions
## regarding the future. This provides us with a range of interpretation.

activeDirectory = active.listActiveDirectory()
files = listdir(activeDirectory)

pattern = "_best_ind_verification.txt"

inds = []

for x in range(0, 20):
    patternWithNumber = str(x) + pattern
    if(patternWithNumber in files):
        with open(activeDirectory + "/" + patternWithNumber, 'r') as file:
            current_ind = []        
            for line in file:
                current_ind.append(line)
            inds.append(current_ind)

avg_values = []
min_values = []
max_values = []

for x in range(0, len(inds[0])):
    average = 0
    max = -sys.maxsize - 1
    min = sys.maxsize
    for y in range(0, 20):
        val = float(inds[y][x])
        average += val
        max = (val if val > max else max)
        min = (val if val < min else min)
    avg_values.append(average / 20)
    min_values.append(min)
    max_values.append(max)
        
with open(activeDirectory + "/ensemble-avg.txt", 'w') as avg, open(activeDirectory + "/ensemble-min.txt", 'w') as min, open(activeDirectory + "/ensemble-max.txt", 'w') as max:
    for x in range(0, len(avg_values)):
        avg.write(str(avg_values[x]) + "\n")
        min.write(str(min_values[x]) + "\n")
        max.write(str(max_values[x]) + "\n")
        