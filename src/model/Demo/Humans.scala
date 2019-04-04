package model.Demo

import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

class Humans extends Animate_Objects (100, 100.0, 10, 5){
  var inventory: List[Inanimate_Objects] = List()
  var hungertime: Long = System.nanoTime()
  override def consumeObject(consumedObject: Inanimate_Objects): Unit = {
    if (consumedObject.canConsume) {
      this.health = this.health + consumedObject.health
      if (this.health > 100) {
        this.health = 100
      }
    }
  }

  var shape = new Circle {
    radius = 32.0
    fill = Color.Yellow
  }

  override def consumeFlesh(consumed: Inanimate_Objects): Unit = {

  }

  override def loseMonsterHP(attacker: Inanimate_Objects): Unit = {

  }
  override def loseHunger: Double = {
    1/5
  }
  def hungerUpdate(time:Long):Unit = {
    hunger = (time/1000000000 - hungertime/1000000000) * loseHunger
    hungertime = time
  }
  override def loseHumanHP(attacker: Animate_Objects): Unit = {
    health = health - attacker.strength
  }

  def pickUp(thing: Inanimate_Objects): Unit = {
    if (thing.canPickUp) {
      inventory = inventory :+ thing
    }
    if (inventory.length > 30) {
      println("You're Over The Inventory Limit")
    }
  }
  override def die: Boolean = {
    if (this.health <= 0){
      GameLobby.aliveHumans -= this
      true
    }
    else{
      false
    }
  }
}
