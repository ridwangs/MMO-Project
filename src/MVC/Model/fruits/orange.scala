package MVC.Model.fruits

import MVC.Model.objects.{Humans, Inanimate_Objects}
import scalafx.scene.paint.Color


class orange extends Inanimate_Objects(5){
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
