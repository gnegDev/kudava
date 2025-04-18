from datetime import datetime

from flask import Flask, request
from model.model_interface import predict_attack

app = Flask(__name__)

@app.route("/api/ping")
def ping():
    return "pong"

@app.route("/api/analyze", methods=["POST"])
def analyzePacket():
    # print("AAA")
    packet = request.json
    # print(packet)

    predictedAttackType = predict_attack(packet)[0]

    response = {"uuid": packet["uuid"], "analysis_result": predictedAttackType, "timestamp": datetime.now().isoformat()}
    # print(response)
    return response

if __name__ == '__main__':
    app.run("0.0.0.0", debug=True, port=5050)