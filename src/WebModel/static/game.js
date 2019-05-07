var socket = io.connect({transports: ['websocket']});
socket.on('gameState', parseGameState);

let bg;
let jsPlayer;
let maxWidth;
let maxHeight;
let jsWidth = 1500;
let jsHeight = 730;

var b;
var fruits = ["Apple", "Orange", "Banana"];
var f = [];

function initializeGame(inputUsername) {
    let username = inputUsername;
    jsPlayer = createHuman(width/2,height/2+100, 100,5,5);
    jsPlayer.shape(document.getElementById("tagColor").value);
    socket.emit("connect", username);
}

function parseGameState(event) {
     var gameState = JSON.parse(event);
     maxHeight = gameState['height']/2;
     maxWidth = gameState['width']/2;

     let scaleX = jsWidth/(2*maxWidth);
     let scaleY = jsHeight/(2*maxHeight);

     for (let h of gameState['humans']){
         createHuman(h['x']*scaleX,h['y']*scaleY,h['health'],h['speed'],h['strength']);
     }

    for (let a of gameState['apples']){
        createFruit("Apple",a['x']*scaleX,a['y']*scaleY,a['health']);
    }

    for (let b of gameState['bananas']){
        createFruit("Banana",b['x']*scaleX,b['y']*scaleY,b['health']);
    }

    for (let o of gameState['oranges']){
        createFruit("Orange",o['x']*scaleX,o['y']*scaleY,o['health']);
    }
}

function createHumans(x,y,health, speed, strength){
    let h = new createHuman(x,y,health, speed, strength);
    h.shape("");
}

function createFruits(t,x,y,health){
    let f = new createFruit(t,x,y, health);
    f.show();
}


function setup() {
    bg = loadImage('pbg.png');
    createCanvas(jsWidth, jsHeight);
    b = new createHuman(jsWidth/2,jsHeight/2, 100,5,5);
    for (var i = 1; i <=4; i++) {
        var p = Math.floor(Math.random()*3);
        var x = Math.random() * jsWidth;
        var y = Math.random() * jsHeight;
        f[i] = new createFruit(fruits[p], x,y, 5);
    }
}

function draw() {
    background(bg);
    for (var i = 1; i <=4; i++) {
        f[i].show();
    }
    b.shape("");
}
