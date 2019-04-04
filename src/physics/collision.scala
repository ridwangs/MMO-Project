package physics

import scalafx.scene.shape.Circle

class collision {
  def collide(circle1: Circle, circle2: Circle): Boolean = {
    var xdistance = circle1.centerX.toDouble - circle2.centerX.toDouble
    var ydistance = circle1.centerY.toDouble - circle2.centerY.toDouble
    var sumradius = Math.sqrt(Math.pow(xdistance,2) + Math.pow(ydistance,2))
    if(sumradius < circle1.radius.value+circle2.radius.value){//Math.pow(circle1.radius.toDouble + circle2.radius.toDouble,2)){
      true
    }
    else{
      false
    }
  }
  def stayput(circle1: Circle, circle2: Circle): Unit = {
    val sumradius = circle1.radius.toDouble + circle2.radius.toDouble
    var xdistance = circle1.centerX.toDouble - circle2.centerX.toDouble
    var ydistance = circle1.centerY.toDouble - circle2.centerY.toDouble
    var length = Math.sqrt(Math.pow(xdistance, 2)+ Math.pow(ydistance, 2))
    if (collide(circle1, circle2)){
      circle1.centerX = circle2.centerX.toDouble + sumradius * (xdistance/length)
      circle1.centerY = circle2.centerY.toDouble + sumradius * (ydistance/length)
    }
  }
}
