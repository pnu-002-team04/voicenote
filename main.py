from __future__ import print_function
from lexrankr import LexRank


lexrank = LexRank()

all_txt = []
with open("txt.txt", "r") as f:
     all_txt = f.readlines()
all_string = ""
for i in all_txt:
    all_string += i

lexrank.summarize(all_string)

summaries = lexrank.probe(2)
for summary in summaries:
    print(summary)