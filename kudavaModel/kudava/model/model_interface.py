import pandas as pd
import joblib

# Загрузка объектов


def predict_attack(new_data):
    preprocessor = joblib.load('preprocessor.pkl')
    model = joblib.load('attack_classifier.pkl')
    le = joblib.load('label_encoder.pkl')

    new_data = map_data(new_data)

    # Преобразование входных данных
    processed_data = preprocessor.transform(new_data)

    # Предсказание
    prediction = model.predict(processed_data)

    # Обратное преобразование меток
    return le.inverse_transform(prediction)

def map_data(data):
    new_data = pd.DataFrame({
        'dur': [0.5],
        'proto': [data['protocol']],
        'service': [data['service']],
        'spkts': [data['spkts']],
        'dpkts': [data['dpkts']],
        'sbytes': [data['sbytes']],
        'dbytes': [data['dbytes']],
        'ct_src_dport_ltm': [data['src_port']],
        'ct_dst_sport_ltm': [data['dst_port']]
    })
    return new_data
# new_data = pd.DataFrame({
#     'dur': [0.5],
#     'proto': ['tcp'],
#     'service': ['http'],
#     'spkts': [10],
#     'dpkts': [8],
#     'sbytes': [512],
#     'dbytes': [256],
#     'ct_src_dport_ltm': [80],
#     'ct_dst_sport_ltm': [443]
# })
#
# result = predict_attack(new_data)
# print(f"Predicted attack: {result[0]}")