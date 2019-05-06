var socket = io.connect({transports: ['websocket']});
socket.on('gameState', parseGameState);

function parseGameState(event) {
     var gameState = JSON.parse(event);
/*
     for (){

     }
*/

}









var blob;
var b;

var blobs = [];
var zoom = 1;
var fruits = ["Apple", "Orange", "Banana"];
var f = [];


function initializeGame(inputUsername) {
    username = inputUsername;

    var html = "";

    socket.emit("register", username);
}

function setup() {
    //   var parsed = JSON.parse(jsonString)

    createCanvas(1500, 730);
    b = new createHuman(100,100, 100,5,5);
    blob = new Blob(0, 0, 64);
    for (var i = 0; i < 10; i++) {
        var p = Math.floor(Math.random()*3);
        var x = random(-width/2,width/2);
        var y = random(-height/2,height/2);
        f[i] = new createFruit(fruits[p], x,y);
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
