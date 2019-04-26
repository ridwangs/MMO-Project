package MVC.Model.fruits

import MVC.Model.objects.{Humans, Inanimate_Objects}
import scalafx.scene.paint.Color

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
