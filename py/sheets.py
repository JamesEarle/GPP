import os
import active
import datetime
from os import listdir
from openpyxl import Workbook

# Create spreadsheets with data filled in so I only have to select data and graph it.

wb = Workbook()
ws = wb.active

ws['A1'] = "DJIA"
ws['B1'] = "BEST_TRAIN"
ws['C1'] = "BEST_TEST"
ws['D1'] = "ENS_AVG"
ws['E1'] = "ENS_MED"
ws['F1'] = "ENS_MIN"
ws['G1'] = "ENS_MAX"

activeDirectory = active.listActiveDirectory()
files = listdir(activeDirectory)

input = "D:/Development/GitHub/GPP/GeneticProgrammingPortfolio/input/DJIA/djia_close_12-16.txt"
input_arr = []

for line in open(input, 'r'):
    input_arr.append(float(line))

for x in range(0, len(input_arr)):
    ws['A' + str(x+2)] = input_arr[x]

# Save the file
wb.save(activeDirectory + "/sample.xlsx")

