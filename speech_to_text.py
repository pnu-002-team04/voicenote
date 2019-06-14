# python -m pip install fpdf
# python -m pip install python-docx
# ref : https://pypi.org/project/google-cloud-speech/
# python -m pip install google-cloud-speech
# python -m pip install SpeechRecognition
# python -m pip install pydub
# ffmpeg download and register system path

import io
import os
from fpdf import FPDF
from docx import Document
from docx.shared import Inches
from google.cloud import speech
from google.cloud.speech import enums
from google.cloud.speech import types
from datetime import datetime
import sys

var1 = sys.argv[1]
now = datetime.now()
title = '%s-%s-%s Record File' % (now.year, now.month, now.day)


def transcribe_file(speech_file):

    client = speech.SpeechClient()

    with io.open(speech_file, 'rb') as audio_file:
        content = audio_file.read()

    audio = types.RecognitionAudio(content=content)
    config = types.RecognitionConfig(
        encoding=enums.RecognitionConfig.AudioEncoding.LINEAR16,
        sample_rate_hertz=44100,
        language_code='en-US')

    operation = client.long_running_recognize(config, audio)

    print('Waiting for operation to complete...')
    response = operation.result(timeout=90)


    for result in response.results:
        print(u'{}'.format(result.alternatives[0].transcript))
        f = open("./test.tmp", 'w')
        f.write(result.alternatives[0].transcript)
        f.close()

def speechToTextWithKey(filePath):
    import sys
    import os
    import speech_recognition as sr
    r = sr.Recognizer()
    havard = sr.AudioFile(filePath)

    #f = open("test.tmp", 'w')

    s = ""

    with havard as source:
        audio = r.record(source)
        t = str(r.recognize_google(audio))
        s += t
        
    #f.write(s)
    print(s)

def convertM4aToWav(filepath):
    import os
    import argparse
    from pydub import AudioSegment
    import sys

    formats_to_convert = ['.m4a']
    #filepath = dirpath + '/' + filename
    #filepath = var1
    (path, file_extension) = os.path.splitext(filepath)

    base = os.path.basename(filepath)
    name = os.path.splitext(base)[0]
    dirPath = path.replace(name, '')
    file_extension_final = file_extension.replace('.', '')
    if file_extension_final == "wav":
        return filepath
        
    try:
        track = AudioSegment.from_file(filepath,
                file_extension_final)
        wav_path = path + ".wav"
        print('CONVERTING: ' + str(filepath))
        file_handle = track.export(wav_path, format='wav')
        #os.remove(filepath)
        print(wav_path)
        return wav_path
    except:
        print("ERROR CONVERTING " + str(filepath))


if __name__ == '__main__':
    print(var1)
    file_name = os.path.join(
        os.path.dirname(__file__),
        '.',
        var1)
    print(file_name)
    file_name = convertM4aToWav(file_name)
    with io.open(file_name, 'rb') as audio_file:
        content = audio_file.read()
        audio = types.RecognitionAudio(content=content)
    
    #transcribe_file(file_name) # should append API key
    speechToTextWithKey(file_name)
    print("EXIT") # trigger word

