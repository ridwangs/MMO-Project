package model.Demo

import javafx.scene.paint.Color
import scalafx.scene.shape.Circle

abstract class Inanimate_Objects(var health: Int, var damage: Int) extends Object {
  def weight(): Int = {
    0
  }
  def canConsume: Boolean = {
    false
  }
  def canPickUp: Boolean = {
    true
  }
  def effect(player: Humans): Unit = {

  }
  var shape = new Circle(){
    radius = 10.0
  }
}
