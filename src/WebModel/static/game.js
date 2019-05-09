var socket = io.connect({transports: ['websocket']});
socket.on('gameState', parseGameState);

let jsWidth = 1500;
let jsHeight = 730;

let canvas = document.getElementById("canvas");
let context = canvas.getContext("2d");
context.globalCompositeOperation = 'source-over';

function initializeGame(inputUsername) {
    socket.emit("register", inputUsername);
    socket.on('gameState', parseGameState);
}

function parseGameState(event) {
    const gameState = JSON.parse(event);

    canvas.setAttribute("width", jsWidth);
    canvas.setAttribute("height", jsHeight);

    let maxHeight = gameState['height']/2;
    let maxWidth = gameState['width']/2;
    let scaleX = jsWidth/(2*maxWidth);
    let scaleY = jsHeight/(2*maxHeight);

    for (let h of gameState['humans']){
        console.log("please work");
        placeHuman(h['x']*scaleX,h['y']*scaleY,'#0000FF');
    }

    if(gameState['fruits']['sizeA'] > 0) {
        for (let a of gameState['apples']) {
            console.log(a['x']);
            placeFruits(a['x'] * scaleX, a['y'] * scaleY, '#ff0000');
        }
    }

    if(gameState['fruits']['sizeB'] > 0) {
        for (let b of gameState['bananas']) {
            console.log(b['x']);
            placeFruits(b['x'] * scaleX, b['y'] * scaleY, '#ffff00');
        }
    }

    if(gameState['fruits']['sizeO'] > 0) {
        for (let o of gameState['oranges']) {
            console.log(o['x']);
            placeFruits(o['x'] * scaleX, o['y'] * scaleY, '#FFC300');
        }
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