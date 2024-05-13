FROM python:3.10

WORKDIR /app

COPY requirements.txt .

RUN pip install --no-cache-dir --upgrade -r requirements.txt \
    && pip install fastapi uvicorn

COPY *.py .

CMD ["fastapi", "run", "main.py", "--port", "80"]
