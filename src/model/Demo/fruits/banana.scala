package model.Demo.fruits

import model.Demo.Inanimate_Objects
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

class banana extends Inanimate_Objects(5, 1){
  override def canConsume: Boolean = {
    true
  }

  override def canPickUp: Boolean = {
    true
  }

  shape.fill = Color.Yellow
}
