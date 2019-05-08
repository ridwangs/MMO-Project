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
# ** Connect to Scala TCP socket server **

scala_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
scala_socket.connect(('localhost', 8000))

delimiter = "~"


def listen_to_scala(the_socket):
    buffer = ""
    while True:
        buffer += the_socket.recv(1024).decode()
        while delimiter in buffer:
            message = buffer[:buffer.find(delimiter)]
            buffer = buffer[buffer.find(delimiter) + 1:]
            get_from_scala(message)

Thread(target=listen_to_scala, args=(scala_socket,)).start()

def get_from_scala(data):
    message = json.loads(data)
    username = message["username"]
    user_socket = usernameToSid.get(username, None)
    if user_socket:
        socket_server.emit('message', data, broadcast=True)


def send_to_scala(data):
    scala_socket.sendall((json.dumps(data) + delimiter).encode())







sidToUsername = {}
usernameToSid = {}

@socket_server.on('connect')
def got_message():
    # usernameToSid[username] = request.sid
    # sidToUsername[request.sid] = username
    print(request.sid + " connected")
    message = {"username": request.sid, "action": "spawn"}
    send_to_scala(message)



@socket_server.on('disconnect')
def disconnect():
    if request.sid in sidToUsername:
        username = sidToUsername[request.sid]
        del sidToUsername[request.sid]
        del usernameToSid[username]
        print(username + " disconnected")
        message = {"username": username, "action": "disconnected"}
        send_to_scala(message)


@socket_server.on('keyStates')
def key_state(jsonKeyStates):
    print("moving")
    message = {"username": request.sid, "action": "move", "key_states": jsonKeyStates}
    send_to_scala(message)

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
    socket_server.run(app, port=60000)