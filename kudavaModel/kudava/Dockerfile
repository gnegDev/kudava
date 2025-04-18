FROM python:3.14-rc
LABEL authors="gnegDev"

COPY docker_requirements.txt .
COPY . .

RUN pip3 install --upgrade pip -r docker_requirements.txt

ENTRYPOINT ["python3", "main.py"]