from flask import Blueprint, render_template

dashboard_controller = Blueprint("dashboard_controller", __name__, template_folder="../static/dashboard")
@dashboard_controller.route("/dashboard")
def render_main_page():
    return render_template("dashboard.html")
