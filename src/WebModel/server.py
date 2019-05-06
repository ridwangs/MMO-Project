import json
import socket
from threading import Thread
from random import randint

from flask import Flask, send_from_directory, request, render_template
from flask_socketio import SocketIO

import eventlet

eventlet.monkey_patch()


app = Flask(__name__)
socket_server = SocketIO(app)




@app.route('/')
def index():
    return send_from_directory('static', 'index.html')


@app.route('/game', methods=["POST", "GET"])
def game():
    if request.method == "POST":
        username = request.form.get('username')
    else:
        username = "guest" + str(randint(0, 100000))
    return render_template('game.html', username=username)


@app.route('/<path:filename>')
def static_files(filename):
    return send_from_directory('static', filename)

if __name__ == "__main__":
    socket_server.run(app, port=8080)
