from datetime import datetime

from flask import Flask, request, jsonify
# from sklearn.externals import joblib

app = Flask(__name__)
# model = joblib.load('model.pkl')
#
# @app.route('kudava/api/predict', methods=['POST'])
# def predict():
#     data = request.get_json(force=True)
#     prediction = model.predict([data['features']])
#
#     return jsonify({'prediction': prediction.tolist()})

@app.route("/api/ping")
def ping():
    return "pong"

@app.route("/api/analyze", methods=["POST"])
def analyzePacket():
    # print("AAA")
    packet = request.json
    # print(packet)
    uuid = packet["uuid"]
    # print(uuid)
    response = {"uuid": uuid, "analysis_result": "OK", "timestamp": datetime.now().isoformat()}
    # print(response)
    return response

if __name__ == '__main__':
    app.run("0.0.0.0", debug=True, port=5050)