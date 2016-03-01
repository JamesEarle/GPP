import os
import sys
import active
import statistics as stat
from os import listdir


## Author: James Earle
##
## Ensemble learning is a technique that provides multiplee
## "opinions" as to the solution of a problem. In this sense, we 
## save 20 best-of-run individuals and average their predictions
## regarding the future. This provides us with a range of interpretation.

def outlierDetection(values, temp):
    ind = 0
    while True:
        val = temp[ind]
        if(abs(val - stat.mean(temp)) > (2*stat.stdev(temp))):
            del temp[ind]
            ind += 1
        else:
            values.append(temp[ind])
            break
    
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

std_dev_values = []
avg_values = []
med_values = []
min_values = []
max_values = []

# Do aggregation for however many data points we have.
for x in range(0, len(inds[0])):
    # average = 0
    # max = -sys.maxsize - 1
    # min = sys.maxsize
    
    # Don't need min or max, instead we create a list of the 20 entries.
    # values_at_index = []
    
    temp = []
    # Iterate 20 individuals at a single index (Column Major Order)
    for y in range(0, 20):
        val = float(inds[y][x])
        temp.append(val)
        # average += val
        # max = (val if val > max else max)
        # min = (val if val < min else min)
        # values_at_index.append(val)
    
    # print(sorted(temp))
    
    ind = 0
    temp = sorted(temp)
    
    # replace this with call to outlierDetection(min_values, temp)
    # Find the min that is within 2-sigma
    while True:
        val = temp[ind]
        if(abs(val - stat.mean(temp)) > (2*stat.stdev(temp))):
            del temp[ind]
            ind += 1
        else:
            min_values.append(temp[ind])
            break
    
    ind = 0
    temp = sorted(temp, reverse=True) # Reverse
    
    # replace this with call to outlierDetection(max_values, temp)
    # Find the max that is within 2-sigma
    while True:
        val = temp[ind]
        if(abs(val - stat.mean(temp)) > (2*stat.stdev(temp))):
            del temp[ind]
            ind += 1
        else:
            max_values.append(temp[ind])
            break
            
    std_dev_values.append(stat.stdev(temp))
    avg_values.append(stat.mean(temp))
    med_values.append(stat.median(temp))
    # min_values.append(min(temp))
    # max_values.append(max(temp))
    
    # Average is currently a sum, divide by number of runs
    # average /= 20
    
    # We have to reiterate the list. Can't calculate Std Dev without average
    # sum = 0
    # for y in range(0, 20):
        # value = float(inds[y][x])
        # sum += (value - average)**2
    
    # Std. Dev. is the sum divided out by 20 runs
    # sum /= 20
    
    # print(values_at_index)
    # values_at_index = sorted(values_at_index)
    # print(values_at_index)
    
    # i = 0
    # min = values_at_index[i]
    # while(abs(min - average) > (2 * sum)):
    #     i += 1
    #     print(i)
    #     min = values_at_index[i]
    
    # i = len(values_at_index) - 1
    # max = values_at_index[i]
    # while(abs(max - average) > (2 * sum)):
    #     print(str(max) + " - " + str(average) + " > " + str(2 * sum))
    #     i -= 1
    #     max = values_at_index[i]
    
    # print("-" + str(min))
    # print("+" + str(max))
    # print(values_at_index)
    
    # std_dev_values.append(sum)
    # avg_values.append(average)
    # min_values.append(min)
    # max_values.append(max)
        
with \
open(activeDirectory + "/ensemble-avg.txt", 'w') as avg, \
open(activeDirectory + "/ensemble-min.txt", 'w') as min, \
open(activeDirectory + "/ensemble-max.txt", 'w') as max, \
open(activeDirectory + "/ensemble-std-dev.txt", "w") as std_dev:
    for x in range(0, len(avg_values)):
        std_dev.write(str(std_dev_values[x]) + "\n")
        avg.write(str(avg_values[x]) + "\n")
        min.write(str(min_values[x]) + "\n")
        max.write(str(max_values[x]) + "\n")
        