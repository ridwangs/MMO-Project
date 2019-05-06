function createHuman(x,y,health, speed, strength){
    this.position = createVector(x,y);
    this.health = health;
    this.speed = speed;
    this.strength = strength;
    this.radius = 24;
    this.vel = createVector(0,0);

    this.shape = function(x) {
        switch(x) {
            case "Red":
                fill(255, 0, 0);
                ellipse(this.position.x, this.position.y,this.radius,this.radius);
                break;
            case "Blue":
                fill(0, 0, 255);
                ellipse(this.position.x, this.position.y,this.radius,this.radius);
                break;
            case "Yellow":
                fill(255, 255, 0);
                ellipse(this.position.x, this.position.y,this.radius,this.radius);
                break;
            case "Green":
                fill(0, 255, 0);
                ellipse(this.position.x, this.position.y,this.radius,this.radius);
                break;

            default:
                fill(0, 0, 255);
                ellipse(this.position.x, this.position.y,this.radius,this.radius);
                break;
        }
    };

    this.collide = function (x) {
        var objDistance = p5.Vector.dist(this.position,x.position);
        return (objDistance < this.radius + x.radius);
    };

    this.stayPut = function (p1,p2) {
        p1.position = createVector(p1.position.x,p1.position.y);
        p2.position = createVector(p2.position.x,p2.position.y);
    };

    this.attack = function (p1, p2) {
        p2.health -= p1.strength

    };
}

function createFruit(item, x, y) {
    this.value = 5;
    var damage;
    this.radius = 10;
    this.show = function () {


        switch (item) {
            case "Apple":
                fill(255, 0, 0);
                ellipse(x, y, this.radius, this.radius);
                break;
            case "Orange":
                fill(255, 165, 0);
                ellipse(x, y, this.radius, this.radius);
                break;
            case "Banana":
                fill(255, 255, 0);
                ellipse(x, y, this.radius, this.radius);
                break;
            default:
                break;

        }
    };
    this.consume = function (p) {
        switch (item) {
            case "Apple":
                p.health += this.value;
                break;
            case "Orange":
                p.speed += this.value;
                break;
            case "Banana":
                p.strength += this.value;
                break;
            default:
                break;

        }
    }
}









function Blob(x, y, r) {
    this.pos = createVector(x, y);
    this.r = r;
    this.vel = createVector(0,0);

    /* this.update = function() {
         var newvel = createVector(mouseX-width/2, mouseY-height/2);
         newvel.setMag(3);
         this.vel.lerp(newvel, 0.2);
         this.pos.add(this.vel);
     }

     this.eats = function(other) {
         var d = p5.Vector.dist(this.pos, other.pos);
         if (d < this.r + other.r) {
             var sum = PI * this.r * this.r + PI * other.r * other.r;
             this.r = sqrt(sum / PI);
             //this.r += other.r;
             return true;
         } else {
             return false;
         }
     }
 */
    this.show = function() {
        fill(255);
        ellipse(this.pos.x, this.pos.y, this.r*2, this.r*2);
    }
}