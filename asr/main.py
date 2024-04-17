from audio_processor import AudioProcessor

if __name__ == '__main__':
    audio_processor = AudioProcessor()
    with open('test.wav', 'rb') as audio_file:
        sample = audio_file.read()
    response = audio_processor.process(sample)

    print(response)
