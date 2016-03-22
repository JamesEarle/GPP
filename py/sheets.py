import os
import active
import datetime
from os import listdir
from openpyxl import Workbook

def ensembleToSpreadsheet(filename, col, offset, ws):
    arr = []
    for line in open(filename, 'r'):
        arr.append(float(line))
    for x in range(0, len(arr)):
        ws[col + str(x + offset)] = arr[x]

# Create spreadsheets with data filled in so I only have to select data and graph it.
wb = Workbook()
ws = wb.active

ws['A1'] = "DJIA"
ws['B1'] = "ENS_AVG"
ws['C1'] = "ENS_MED"
ws['D1'] = "ENS_MIN"
ws['E1'] = "ENS_MAX"

activeDirectory = active.listActiveDirectory()
files = listdir(activeDirectory)

input = "D:/Development/GitHub/GPP/GeneticProgrammingPortfolio/input/DJIA/djia_close_12-16.txt"
input_arr = []

# Read and write Dow Jones Index
for line in open(input, 'r'):
    input_arr.append(float(line))
for x in range(0, len(input_arr)):
    ws['A' + str(x+2)] = input_arr[x]

# avg = []
# for line in open(activeDirectory + "/ensemble-avg.txt", "r"):
#     avg.append(float(line))
# for x in range(0, len(avg)):
#     ws['B' + str(x+698)] = avg[x]

ensembleToSpreadsheet(activeDirectory + "/ensemble-avg.txt", 'B', 698, ws)
ensembleToSpreadsheet(activeDirectory + "/ensemble-med.txt", 'C', 698, ws)
ensembleToSpreadsheet(activeDirectory + "/ensemble-min.txt", 'D', 698, ws)
ensembleToSpreadsheet(activeDirectory + "/ensemble-max.txt", 'E', 698, ws)


# Save the file
wb.save(activeDirectory + "/sample.xlsx")

