from __future__ import print_function
from lexrankr import LexRank
from nltk.tokenize import word_tokenize
from nltk.tag import pos_tag
import sys

filePath = sys.argv[1]
probNumber = sys.argv[2]

lexrank = LexRank()

all_txt = []
with open(filePath, "r", encoding="UTF-8") as f:
     all_txt = f.readlines()

all_string = ""
for i in all_txt:
    all_string += i
with open("sentence_set.txt", "r") as f:
    sent_analy = f.readlines()
    for i in range(len(sent_analy)):
        sent_analy[i] = sent_analy[i].split(' ')
        for j in range(len(sent_analy[i])):
            if '\n' in sent_analy[i][j]:
                sent_analy[i][j] = sent_analy[i][j].replace('\n', '')

token_all_string = pos_tag(word_tokenize(all_string))

process_str = ""
index = 0
while(len(token_all_string)):
    for i in range(len(sent_analy)):
        for j in sent_analy[i]:
            if index + 1 == len(sent_analy[i]):
                for k in range(index + 1):
                    process_str += token_all_string[k][0] + " "
                process_str = process_str.rstrip()
                process_str += ". "
                del token_all_string[0:index + 1]
                index = 0
            elif j == token_all_string[index][1]:
                index += 1

lexrank.summarize(process_str)

#print(int(probNumber))
summaries = lexrank.probe(int(probNumber))
result = ""
#print(filePath)
#print(probNumber)
with open("summarizedText", "w", encoding="UTF-8") as f:
    for summary in summaries:
        result +=  summary + ".  "
    f.write(result)
    print(result)
print("EXIT") # trigger word