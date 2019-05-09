var keyStates = {
    "upKeyHeld": false,
    "leftKeyHeld": false,
    "downKeyHeld": false,
    "rightKeyHeld": false,
    "spaceKeyHeld" : false
};

function setState(key, toSet){
    if(keyStates[key] !== toSet){
        keyStates[key] = toSet;
        socket.emit("keyStates", JSON.stringify(keyStates));
    }
}

function handleEvent(event, toSet){
    if(event.key === "w" || event.key === "ArrowUp"){
        setState("upKeyHeld", toSet);
    }else if(event.key === "a" || event.key === "ArrowLeft"){
        setState("leftKeyHeld", toSet);
    }else if(event.key === "s" || event.key === "ArrowDown"){
        setState("downKeyHeld", toSet);
    }else if(event.key === "d" || event.key === "ArrowRight"){
        setState("rightKeyHeld", toSet);
    }else if(event.keyCode === 32) {
        setState("spaceKeyHeld", toSet);
    }
}

document.addEventListener("keydown", function (event) {
    handleEvent(event, true);
});

document.addEventListener("keyup", function (event) {
    handleEvent(event, false);
});
