package MVC.Model.objects

import scalafx.scene.shape.Circle

abstract class Inanimate_Objects(var health: Int, var damage: Int) extends Object {
  var xcoord: Double = 0
  var ycoord: Double = 0
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
  var shape: Circle = new Circle(){
    radius = 10.0
  }
}
