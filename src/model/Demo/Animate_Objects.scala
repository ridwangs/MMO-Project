package model.Demo

abstract class Animate_Objects(var health: Double, var hunger: Double, var speed: Double, var strength: Double){
  def consumeObject(consumed: Inanimate_Objects)
  def loseHumanHP(attacker: Animate_Objects)
}
