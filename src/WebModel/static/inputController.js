var keyStates = {
    "upKeyHeld": false,
    "leftKeyHeld": false,
    "downKeyHeld": false,
    "rightKeyHeld": false
};

function setState(key, toSet){
    if(keyStates[key] !== toSet){
        keyStates[key] = toSet;
        socket.emit("keyStates", JSON.stringify(keyStates));
    }
}

function handleEvent(event, toSet){
    if(event.key === "upKeyHeld" || event.key === "ArrowUp"){
        setState("upKeyHeld", toSet);
    }else if(event.key === "leftKeyHeld" || event.key === "ArrowLeft"){
        setState("leftKeyHeld", toSet);
    }else if(event.key === "downKeyHeld" || event.key === "ArrowDown"){
        setState("downKeyHeld", toSet);
    }else if(event.key === "rightKeyHeld" || event.key === "ArrowRight"){
        setState("rightKeyHeld", toSet);
    }
}

document.addEventListener("keydown", function (event) {
    handleEvent(event, true);
});

document.addEventListener("keyup", function (event) {
    handleEvent(event, false);
});
