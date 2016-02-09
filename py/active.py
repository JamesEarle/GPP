import os
from os import listdir

def listActiveDirectory():
    outPath = 'D:/Development/GitHub/GPP/GeneticProgrammingPortfolio/docs-img'
    outFolders = listdir(outPath)
    outDayDir = outPath + "/" + outFolders[len(outFolders) - 1]
    outDayFolders = listdir(outDayDir)
    outHourDir = outDayDir + "/" + outDayFolders[len(outDayFolders) - 1]
    return outHourDir