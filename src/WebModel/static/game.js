var socket = io.connect({transports: ['websocket']});


let username;
let bg;
let jsPlayer;

let jsWidth = 1500;
let jsHeight = 730;


var b;
var fruits = ["Apple", "Orange", "Banana"];
var f = [];
var m = [];
var i = 0;

var canvas = document.getElementById("canvas");
var context = canvas.getContext("2d");
context.globalCompositeOperation = 'source-over';
canvas.setAttribute("width", jsWidth);
canvas.setAttribute("height", jsHeight);


function initializeGame(inputUsername) {
    username = inputUsername;
    console.log(username);
    socket.emit("register", username);
    socket.on('gameState', parseGameState);
}

function parseGameState(event) {
    const gameState = JSON.parse(event);
    let maxHeight = gameState['height']/2;
    let maxWidth = gameState['width']/2;
    let scaleX = jsWidth/(2*maxWidth);
    let scaleY = jsHeight/(2*maxHeight);

    for (let h of gameState['humans']){
        console.log("please work");
        placeHuman(h['x']*scaleX,h['y']*scaleY,'#0000FF');
    }

    for (let a of gameState['apples']){
        placeFruits(a['x']*scaleX,a['y']*scaleY,'#ffff00');
    }

    for (let b of gameState['bananas']){
        placeFruits(b['x']*scaleX,b['y']*scaleY,'#ff0000');
    }

    for (let o of gameState['oranges']){
        placeFruits(o['x']*scaleX,o['y']*scaleY,'#FFC300');
    }
}

function placeHuman(x, y, color) {
    context.fillStyle = color;
    context.beginPath();
    context.arc(x ,
        y ,
        24,
        0,
        2 * Math.PI);
    context.fill();
    context.strokeStyle = 'black';
    context.stroke();
}

function placeFruits(x, y, color) {
    context.fillStyle = color;
    context.beginPath();
    context.arc(x ,
        y ,
        10,
        0,
        2 * Math.PI);
    context.fill();
    context.strokeStyle = 'black';
    context.stroke();
}