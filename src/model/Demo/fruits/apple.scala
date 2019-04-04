package model.Demo.fruits

import model.Demo.{Humans, Inanimate_Objects}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

class apple extends Inanimate_Objects(5, 1){
  override def canConsume: Boolean = {
    true
  }

  override def canPickUp: Boolean = {
    true
  }

  shape.fill = Color.Red

  override def effect(player: Humans): Unit = {
    player.health += this.health
  }

}
