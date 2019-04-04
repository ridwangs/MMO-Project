package model.Demo.fruits

import model.Demo.{Humans, Inanimate_Objects}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle


class orange extends Inanimate_Objects(2, 1){
  override def canConsume: Boolean = {
    true
  }

  override def canPickUp: Boolean = {
    true
  }
  shape.fill = Color.Orange
  override def effect(player: Humans): Unit = {
    player.speed += this.health
  }
}
