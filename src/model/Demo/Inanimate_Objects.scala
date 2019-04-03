package model.Demo

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
}
