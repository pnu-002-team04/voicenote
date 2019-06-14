import os
import argparse
from pydub import AudioSegment
import sys

var1 = sys.argv[1]
formats_to_convert = ['.m4a']


#filepath = dirpath + '/' + filename
filepath = var1
(path, file_extension) = os.path.splitext(filepath)
base = os.path.basename(filepath)
name = os.path.splitext(base)[0]
dirPath = path.replace(name, '')
file_extension_final = file_extension.replace('.', '')
try:
    track = AudioSegment.from_file(filepath,
            file_extension_final)
    wav_path = path + ".wav"
    print('CONVERTING: ' + str(filepath))
    file_handle = track.export(wav_path, format='wav')
    #os.remove(filepath)
    print(wav_path)
except:
    print("ERROR CONVERTING " + str(filepath))
