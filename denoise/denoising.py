import sys
sys.path.append('lib')
import AVPreprocess as avp
import librosa
import scipy.io.wavfile as wavfile
import argparse
import numpy as np

from keras.models import model_from_json

parser = argparse.ArgumentParser()
parser.add_argument('path', type=str)

args = parser.parse_args()

def data_read(path, mode):
    with open(path, mode) as f:
        data, sr = librosa.load(path, sr=None)

    return data, sr
print (args.path)
mix_data, mix_sr = data_read(args.path, "rb")

fft_size = 1024
step_size = fft_size // 3

n_mels = 40 # number of mel frequency
start_freq = 0.0
end_freq = 8000.0

mel_mix_data = avp.time_to_mel(mix_data, mix_sr,fft_size, n_mels, step_size)
D_X = avp.real_imag_expand(mel_mix_data)
G_max = np.max(D_X)
with open('model2.json','r') as f:
    loaded_model_json = f.read()
denoise_model = model_from_json(loaded_model_json)
denoise_model.load_weights("model2.h5")
print("Loaded model from disk")

gain = denoise_model.predict(D_X) * G_max
M_gain = gain[:,::2]+1j*gain[:,1::2]
F_gain = avp.mel2freq(M_gain,mix_sr,fft_size,n_mels)

F = F_gain * avp.stft(mix_data,fft_size,step_size)
#ratio[np.isnan(ratio)] = 0.0
print("shape of F_out:",F.shape)
T = avp.istft(F,fft_size,step_size)

Tint = T/np.max(T)*32767 * 0.1
wavfile.write("Denoise_reconstruction.wav",mix_sr,Tint.astype('int16'))
print ("EXIT")



























