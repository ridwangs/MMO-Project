package model.Demo

abstract class Animate_Objects(var health: Double, var hunger: Double, var speed: Double, var strength: Double){
  def consumeObject(consumed: Inanimate_Objects)
  def consumeFlesh(consumed: Inanimate_Objects)
  def loseHunger:Double
  def loseMonsterHP(attacker: Inanimate_Objects)
  def loseHumanHP(attacker: Animate_Objects)
  def die: Boolean
}
