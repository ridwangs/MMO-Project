package model.testing
import model.Demo.fruits.apple
import model.Demo.{Humans, Inanimate_Objects, Monster}
import org.scalatest.FunSuite

class testing extends FunSuite{
  test("testing animate objects"){
    var humanDummy: Humans = new Humans
    var monsterDummy: Monster = new Monster
    var apple: Inanimate_Objects = new apple
    var losingHunger: Long = System.nanoTime() + 5
    //testing losehp, consumeobject, losing hunger/ hunger update, picking up inanimate objects, dying
    humanDummy.loseHumanHP(monsterDummy)
    assert(humanDummy.health == 90)
    humanDummy.consumeObject(apple)
    assert(humanDummy.health == 95)
    humanDummy.consumeObject(apple)
    humanDummy.consumeObject(apple)
    assert(humanDummy.health == 100)
//    humanDummy.hungerUpdate(losingHunger)
//    assert(humanDummy.hunger == 99)
    humanDummy.pickUp(apple)
    assert(humanDummy.inventory == List(apple))
    humanDummy.health = 0
    assert(humanDummy.die)

    //testing losehp, consumeflesh, lose hunger/hunger update, dying, enraged
    monsterDummy.loseMonsterHP(apple)
    assert(monsterDummy.health == 49)
    monsterDummy.consumeFlesh(apple)
//    monsterDummy.hungerLoss(losingHunger)
//    assert(monsterDummy.hunger == 98)
    monsterDummy.health = 20
    monsterDummy.enraged()
    assert(monsterDummy.speed == 30)
    assert(monsterDummy.strength == 15)
    monsterDummy.health = 0
    assert(monsterDummy.die)
  }
}
