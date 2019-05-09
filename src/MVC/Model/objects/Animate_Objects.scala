package MVC.Model.objects

abstract class Animate_Objects(var health: Double, var speed: Double, var strength: Double){
  def consumeObject(consumed: Inanimate_Objects)
  def attack(attacker: Animate_Objects)
}
