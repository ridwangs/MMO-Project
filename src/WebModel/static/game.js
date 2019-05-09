var socket = io.connect({transports: ['websocket']});


var canvas = document.getElementById("canvas");
var context = canvas.getContext("2d");
context.globalCompositeOperation = 'source-over';


let gameState
let username;
let bg;
let jsPlayer;
let maxWidth;
let maxHeight;
let jsWidth = 1500;
let jsHeight = 730;

var b;
var fruits = ["Apple", "Orange", "Banana"];
var f = [];
var m = [];
var i = 0;

function initializeGame(inputUsername) {
    username = inputUsername;
    console.log(username);
    // jsPlayer = createHuman(width/2,height/2+100, 100,5,5);
    // jsPlayer.shape(document.getElementById("tagColor").value);
    socket.emit("register", username);
    socket.on('gameState', parseGameState);
}

function parseGameState(event) {
     gameState = JSON.parse(event);
     maxHeight = gameState['height']/2;
     maxWidth = gameState['width']/2;
     console.log(gameState['width']);
    console.log(gameState['height']);
    console.log(gameState['humans']);

     let scaleX = jsWidth/(2*maxWidth);
     let scaleY = jsHeight/(2*maxHeight);

     // for (let h of gameState['humans']){
     //     let j = new createHuman(h['x']*scaleX,h['y']*scaleY,h['health'],h['speed'],h['strength']);
     //     j.shape("Blue");
     // }

    // for (let a of gameState['apples']){
    //     createFruits("Apple",a['x']*scaleX,a['y']*scaleY,a['health']);
    // }
    //
    // for (let b of gameState['bananas']){
    //     createFruits("Banana",b['x']*scaleX,b['y']*scaleY,b['health']);
    // }
    //
    // for (let o of gameState['oranges']){
    //     createFruits("Orange",o['x']*scaleX,o['y']*scaleY,o['health']);
    // }
}

function createHumans(x,y,health, speed, strength){
    let h = new createHuman(x,y,health, speed, strength);
    h.shape("");
}

function createFruits(t,x,y,health){
    let f = new createFruit(t,x,y, health);
    f.show();
}


function placeCircle(x, y, color, size) {
    context.fillStyle = color;
    context.beginPath();
    context.arc(x * tileSize,
        y * tileSize,
        size / 10.0 * tileSize,
        0,
        2 * Math.PI);
    context.fill();
    context.strokeStyle = 'black';
    context.stroke();
}


/*function setup() {
    bg = loadImage('pbg.png');
    createCanvas(jsWidth, jsHeight);
    b = new createHuman(jsWidth/2+150,jsHeight/2-150, 100,5,5);
   /* for (var i = 1; i <=4; i++) {
        var p = Math.floor(Math.random() * 3);
        var x = Math.random() * jsWidth;
        var y = Math.random() * jsHeight;
        f[i] = new createFruit(fruits[p], x, y, 5);


    }

    for (let h of gameState['humans']){
        m[i] = new createHuman(h['x']*scaleX,h['y']*scaleY,h['health'],h['speed'],h['strength']);
        i++;
    }

}

function draw() {
    background(bg);




    for (var j = 1; j < i; j++) {
        m[j].shape("");
    }
    // b.shape("");
}*/
