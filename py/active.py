import os
from os import listdir

def listActiveDirectory():
    # Take a look at all files in the default out directory
    outPath = 'D:/Development/GitHub/GPP/GeneticProgrammingPortfolio/docs-img'
    # List every folder there
    outFolders = listdir(outPath)
    # Select the LAST folder, being the most recent day of rus
    outDayDir = outPath + "/" + outFolders[len(outFolders) - 1]
    # List all subfolders of that days runs.
    outDayFolders = listdir(outDayDir)
    # Select the LAST folder, being the most recent executions.
    outHourDir = outDayDir + "/" + outDayFolders[len(outDayFolders) - 1]
    return outHourDir