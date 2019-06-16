import nltk
from nltk.tokenize import word_tokenize, sent_tokenize
from nltk.tag import pos_tag

all_sentense = ""
with open("test.tmp", "r") as f:
    all_sentense = f.readline()

token_sentence = sent_tokenize(all_sentense)
for i in range(len(token_sentence)):
    token_sentence[i] = word_tokenize(token_sentence[i])
    token_sentence[i] = pos_tag(token_sentence[i])
print (token_sentence)
with open("sentense_set.txt", "w") as f:
    using_sent = ""
    for i in token_sentence:
        for j in i:
            f.write(j[1] + " ")
        f.write("\n")