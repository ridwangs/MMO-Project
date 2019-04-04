//package model.Demo
//
//class Monster extends Animate_Objects(50, 100, 15, 10){
//  var hungertime: Long = System.nanoTime()
//  override def consumeFlesh(consumed: Inanimate_Objects): Unit = {
//    if (consumed.health == 0){
//      this.health = this.health + 100
//    }
//  }
//
//  override def consumeObject(consumed: Inanimate_Objects): Unit = {
//
//  }
//
//  override def loseMonsterHP(attacker: Inanimate_Objects): Unit = {
//    health = health - attacker.damage
//  }
//
//  override def loseHunger: Double = {
//    2/5
//  }
//  def hungerLoss(time: Long): Unit = {
//    hunger = (time/1000000000 - hungertime/1000000000) * loseHunger
//    hungertime = System.nanoTime()
//  }
//  def enraged(): Unit = {
//    if(this.health <= 25){
//      speed = speed * 2
//      strength = strength * 1.5
//    }
//  }
//
//  override def die: Boolean = {
//    if (this.health <= 0){
//      GameLobby.aliveMonster -= this
//      true
//    }
//    else{
//      false
//    }
//  }
//
//  def consumeObject(consumed: Animate_Objects): Unit = {
//    health = health
//  }
//
//  override def loseHumanHP(attacker: Animate_Objects): Unit = {
//  }
//}