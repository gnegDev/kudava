from flask import Flask, request, jsonify
from sklearn.externals import joblib

app = Flask(__name__)
model = joblib.load('model.pkl')

@app.route('kudava/api/predict', methods=['POST'])
def predict():
    data = request.get_json(force=True)
    prediction = model.predict([data['features']])

    return jsonify({'prediction': prediction.tolist()})

if __name__ == '__main__':
    app.run(port=5000, debug=True)
