package model.fruits

import model.objects.{Humans, Inanimate_Objects}
import scalafx.scene.paint.Color

class banana extends Inanimate_Objects(5, 1){
  override def canConsume: Boolean = {
    true
  }

  override def canPickUp: Boolean = {
    true
  }

  shape.fill = Color.Yellow

  override def effect(player: Humans): Unit = {
    player.strength += this.health
  }
}
