import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import IsolationForest

# Загрузка данных
data = pd.read_csv('iot_data.csv')

# Разделение данных на тренировочные и тестовые наборы
X_train, X_test = train_test_split(data, test_size=0.2, random_state=42)

# Обучение модели Isolation Forest
model = IsolationForest(contamination=0.1)
model.fit(X_train)

# Оценка модели на тестовых данных
y_pred = model.predict(X_test)
