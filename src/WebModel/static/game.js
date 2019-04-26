// var socket = io.connect({transports: ['websocket']});
//
// setupSocket();
// ITP Networked Media, Fall 2014
// https://github.com/shiffman/itp-networked-media
// Daniel Shiffman

var blob;

var blobs = [];
var zoom = 1;

function initializeGame(inputUsername) {
    username = inputUsername;

    var html = "";

    socket.emit("register", username);
}


function setup() {

    createCanvas(1500, 730);

    blob = new Blob(0, 0, 64);
    for (var i = 0; i < 200; i++) {
        var x = random(-width,width);
        var y = random(-height,height);
        blobs[i] = new Blob(x, y, 16);
    }
}

function draw() {
    background(0);

    translate(width/2, height/2);
    var newzoom = 64 / blob.r;
    zoom = lerp(zoom, newzoom, 0.1);
    scale(zoom);
    translate(-blob.pos.x, -blob.pos.y);

    for (var i = blobs.length-1; i >=0; i--) {
        blobs[i].show();
        if (blob.eats(blobs[i])) {
            blobs.splice(i, 1);
        }
    }

    blob.show();
    blob.update();

}