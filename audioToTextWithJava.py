#python -m pip install SpeechRecognition

import sys
import os
import speech_recognition as sr
r = sr.Recognizer()
havard = sr.AudioFile("harvard.wav")

f = open("test.tmp", 'w')

s = ""

with havard as source:
    audio = r.record(source)
    s += str(r.recognize_google(audio))
f.write(s)
print(s)
