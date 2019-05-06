var socket = io.connect({transports: ['websocket']});
socket.on('gameState', parseGameState);

let updatetime;
let spawntime;
let maxWidth = 1500;
let maxHeigth = 730;

function parseGameState(event) {
     var gameState = JSON.parse(event);

     for (let h of gameState['humans']){
         createHuman(h['x'],h['y'],h['health'],h['speed'],h['strength']);
     }

    for (let a of gameState['apples']){
        createFruit("Apple",a['x'],a['y'],a['health']);
    }

    for (let b of gameState['bananas']){
        createFruit("Banana",b['x'],b['y'],b['health']);
    }

    for (let o of gameState['oranges']){
        createFruit("Orange",o['x'],o['y'],o['health']);
    }
    updatetime = gameState['updatetime'];
    spawntime = gameState['spawntime'];

}


function createHumans(x,y,health, speed, strength){
    let h = new createHuman(x,y,health, speed, strength);
    h.shape("Blue");
}

function createFruits(t,x,y,health){
    let f = new createFruit(t,x,y, health);
    f.show();
}






var blob;
var b;

var fruits = ["Apple", "Orange", "Banana"];
var f = [];


function initializeGame(inputUsername) {
    username = inputUsername;

    var html = "";

    socket.emit("register", username);
}

function setup() {
    createCanvas(1500, 730);
    b = new createHuman(100,100, 100,5,5);
    blob = new Blob(0, 0, 64);
    for (var i = 0; i < 10; i++) {
        var p = Math.floor(Math.random()*3);
        var x = random(-width/2,width/2);
        var y = random(-height/2,height/2);
        f[i] = new createFruit(fruits[p], x,y, 5);
    }
}

function draw() {
    background(0);
    translate(width/2, height/2);
    translate(-b.position.x, -b.position.y);
    for (var i = f.length-1; i >=0; i--) {
        f[i].show();
    }
    b.shape("Blue");
    blob.show();




}
