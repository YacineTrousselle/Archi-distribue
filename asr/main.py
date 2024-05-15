from fastapi import FastAPI, UploadFile, File
from pydantic import BaseModel
from typing import Annotated

from audio_processor import AudioProcessor

app = FastAPI()
audio_processor = AudioProcessor()


@app.post('/speech-to-text')
async def speech_to_text(audio: Annotated[bytes, File()]):

    response = audio_processor.process(audio)
    print(response)

    return response["text"]
