import os
import active
import datetime
from os import listdir
from openpyxl import Workbook

# Create spreadsheets with data filled in so I only have to select data and graph it.

activeDirectory = active.listActiveDirectory()
files = listdir(activeDirectory)

wb = Workbook()

# grab the active worksheet
ws = wb.active

# Data can be assigned directly to cells
ws['A1'] = 42

# Rows can also be appended
ws.append([1, 2, 3])

# Python types will automatically be converted
#ws['A2'] = datetime.datetime.now()

# Save the file
wb.save(activeDirectory + "/sample.xlsx")

