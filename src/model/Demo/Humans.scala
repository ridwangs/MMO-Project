package model.Demo

import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

class Humans extends Animate_Objects (100, 100.0, 10, 5){
  var inventory: List[Inanimate_Objects] = List()

  var shape = new Circle {
    radius = 24.0
    fill = Color.Blue
  }


  override def consumeObject(consumedObject: Inanimate_Objects): Unit = {
    if (consumedObject.canConsume) {
      this.health = this.health + consumedObject.health
      if (this.health > 100) {
        this.health = 100
      }
    }
  }

  override def loseHumanHP(attacker: Animate_Objects): Unit = {
    health = health - attacker.strength
  }
}
