# -*- Encoding: UTF-8 -*- #
#-*- coding:utf-8 -*-
# coding: utf-8
def run_quickstart():
    # [START speech_quickstart]
    import io
    import os
    import sys
    var1 = sys.argv[1]
    # Imports the Google Cloud client library
    # [START migration_import]
    from google.cloud import speech
    from google.cloud.speech import enums
    from google.cloud.speech import types
    # [END migration_import]

    # Instantiates a client
    # [START migration_client]
    client = speech.SpeechClient()
    # [END migration_client]

    # The name of the audio file to transcribe
    file_name = os.path.join(
        os.path.dirname(__file__),
        '.',
        var1)

    # Loads the audio into memory
    with io.open(file_name, 'rb') as audio_file:
        content = audio_file.read()
        audio = types.RecognitionAudio(content=content)

    config = types.RecognitionConfig(
        encoding=enums.RecognitionConfig.AudioEncoding.LINEAR16,
        sample_rate_hertz=44100,
        language_code='ko-KR')

    # Detects speech in the audio file
    response = client.recognize(config, audio)

    for result in response.results:
        aaaa = result.alternatives[0].transcript.encode('utf8')
        print(u'{}'.format(result.alternatives[0].transcript))
        f = open("./test.tmp", 'w')
        f.write(result.alternatives[0].transcript.encode('utf8'))
        f.close()
    # [END speech_quickstart]


if __name__ == '__main__':
    run_quickstart()