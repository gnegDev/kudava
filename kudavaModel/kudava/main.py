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

@app.route("/api/analyze", methods=["POST"])
def analyzePacket():
    packet = request.json
    print(packet)
    uuid = packet["uuid"]
    # print(uuid)
    response = {"uuid": uuid, "analysis_result": "OK", "timestamp": datetime.now().isoformat()}
    print(response)
    return response

if __name__ == '__main__':
    app.run(port=5050, debug=True)
