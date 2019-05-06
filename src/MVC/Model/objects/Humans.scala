package MVC.Model.objects

import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

class Humans extends Animate_Objects (100, 100.0, 10, 5){
  var xcoord: Double = 0
  var ycoord: Double = 0
  var shape: Circle = new Circle {
    radius = 24.0
    fill = Color.Blue
  }

  override def consumeObject(consumedObject: Inanimate_Objects): Unit = {
    consumedObject.effect(this)
  }

  override def loseHumanHP(attacker: Animate_Objects): Unit = {
    health = health - attacker.strength
  }

}
