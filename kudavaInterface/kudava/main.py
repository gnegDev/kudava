from flask import Flask

from controller.dashboard_controller import dashboard_controller


app = Flask(__name__)

app.register_blueprint(dashboard_controller)

if __name__ == "__main__":
    app.run("0.0.0.0", debug=True, port=5000)