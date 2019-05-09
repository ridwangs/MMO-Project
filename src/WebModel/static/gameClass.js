// function createHuman(x,y,health, speed, strength){
//     this.position = createVector(x,y);
//     this.health = health;
//     this.speed = speed;
//     this.strength = strength;
//     this.radius = 24;
//
//     this.shape = function(x) {
//         switch(x) {
//             case "Red":
//                 fill(255, 0, 0);
//                 ellipse(this.position.x, this.position.y,this.radius,this.radius);
//                 break;
//             case "Blue":
//                 fill(0, 0, 255);
//                 ellipse(this.position.x, this.position.y,this.radius,this.radius);
//                 break;
//             case "Yellow":
//                 fill(255, 255, 0);
//                 ellipse(this.position.x, this.position.y,this.radius,this.radius);
//                 break;
//             case "Green":
//                 fill(0, 255, 0);
//                 ellipse(this.position.x, this.position.y,this.radius,this.radius);
//                 break;
//
//             default:
//                 fill(0);
//                 ellipse(this.position.x, this.position.y,this.radius,this.radius);
//                 break;
//         }
//     };
// }
//
// function createFruit(item, x, y, h) {
//     this.value = h;
//     this.radius = 10;
//     this.show = function () {
//         switch (item) {
//             case "Apple":
//                 fill(255, 0, 0);
//                 ellipse(x, y, this.radius, this.radius);
//                 break;
//             case "Orange":
//                 fill(255, 165, 0);
//                 ellipse(x, y, this.radius, this.radius);
//                 break;
//             case "Banana":
//                 fill(255, 255, 0);
//                 ellipse(x, y, this.radius, this.radius);
//                 break;
//             default:
//                 break;
//         }
//     };
// }