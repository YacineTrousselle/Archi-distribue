FROM python:3.10

WORKDIR /app

RUN pip install --no-cache-dir --upgrade pip setuptools wheel spacy fuzzywuzzy python-Levenshtein pymongo \
    && pip install fastapi pydantic \
    && python -m spacy download fr_core_news_md

COPY *.py .

CMD ["fastapi", "run", "main.py", "--port", "80"]
