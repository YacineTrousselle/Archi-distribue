from fastapi import FastAPI
from pydantic import BaseModel

from spacy_processor import SpacyProcessor


class Processed(BaseModel):
    action: str
    song_scores: list | None = None

app = FastAPI()
nlp = SpacyProcessor()


@app.get("/process")
def process(data: str) -> Processed:
    return nlp.process(data)
