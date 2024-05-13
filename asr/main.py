from fastapi import FastAPI, UploadFile, File
from pydantic import BaseModel

from audio_processor import AudioProcessor

app = FastAPI()
audio_processor = AudioProcessor()


@app.post('/speech-to-text')
def speech_to_text(data: bytes):
    return audio_processor.process(data)
