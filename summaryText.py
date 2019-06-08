from __future__ import print_function
from lexrankr import LexRank
import sys
filePath = sys.argv[1]
probNumber = sys.argv[2]

lexrank = LexRank()

all_txt = []
with open(filePath, "r") as f:
     all_txt = f.readlines()
all_string = ""
for i in all_txt:
    all_string += i
lexrank.summarize(all_string)

#print(int(probNumber))
summaries = lexrank.probe(int(probNumber))
result = ""
#print(filePath)
#print(probNumber)
with open("summarizedText", "w") as f:
    for summary in summaries:
        result +=  summary + ".  "
    f.write(result)
    print(result)
