package MVC.Model

import MVC.Model.fruits.{apple, banana, orange}
import MVC.Model.objects.Humans
import play.api.libs.json.{JsValue, Json}
import scalafx.scene.Group
import scalafx.scene.paint.Color


import scala.collection.mutable.ListBuffer

class Game extends {

  var anyRandom: scala.util.Random = scala.util.Random
  var allHumans: Map[String, Humans] = Map()
  val fruits: List[String] = List("apple", "banana", "orange")
  var allApple: ListBuffer[apple] = ListBuffer()
  var allBanana: ListBuffer[banana] = ListBuffer()
  var allOrange: ListBuffer[orange] = ListBuffer()

  val maximumWidth = 1920
  val maximumHeight = 1020

  var timeSpawn: Double = 7
  var lastUpdateTime: Long = System.nanoTime()
  var objects: Group = new Group {}
//
//  var leftKeyHeld = false
//  var rightKeyHeld = false
//  var upKeyHeld = false
//  var downKeyHeld = false
//  var spaceKeyHeld = false

//  var keyHeld: Map[String, Boolean] = Map("leftkey" -> leftKeyHeld, "rightkey" -> rightKeyHeld, "upkey" -> upKeyHeld, "downkey" -> downKeyHeld, "spacekey" -> spaceKeyHeld)


  def createPlayers(username: String): Humans = {
    var player: Humans = new Humans
    player.shape.centerX = maximumWidth / 2
    player.shape.centerY = maximumHeight / 2
    objects.children.add(player.shape)
    allHumans = allHumans + (username -> player)
    player
  }

  def createFruits(x: String): Unit = {
    x match {
      case "apple" =>
        val apple = new apple()
        apple.shape.centerX = maximumWidth * Math.random
        apple.shape.centerY = maximumHeight * Math.random
        objects.children.add(apple.shape)
        allApple = allApple :+ apple
      case "orange" =>
        val orange = new orange()
        orange.shape.centerX = maximumWidth * Math.random
        orange.shape.centerY = maximumHeight * Math.random
        objects.children.add(orange.shape)
        allOrange = allOrange :+ orange
      case "banana" =>
        val banana = new banana()
        banana.shape.centerX = maximumWidth * Math.random
        banana.shape.centerY = maximumHeight * Math.random
        objects.children.add(banana.shape)
        allBanana = allBanana :+ banana
      case _ =>
    }
  }

//  def createComputer(player: Humans): Unit = {
//    player.shape.fill = Color.Red
//    player.shape.centerX = 0.9 * maximumWidth * Math.random()
//    player.shape.centerY = 0.9 * maximumHeight * Math.random()
//    objects.children.add(player.shape)
//    allHumans = allHumans :+ player
//  }
  def removeplayer(human1: Humans): Unit = {
  for (human <- this.allHumans.keys){
    if (allHumans(human) == human1){
      allHumans = allHumans - human
    }
  }
}
  def checkCollision(): Unit = {
    for (human1 <- this.allHumans.values) {
      for (human2 <- this.allHumans.values) {
        if (human1 != human2){
        if (human1.collide(human2)) {
          human1.stayput(human2)
        }
        if (human1.collide(human2) && human1.spaceKeyHeld) {
          val c = List(Color.Red, Color.Green, Color.Purple)
          human1.shape.fill = c(anyRandom.nextInt(3))
          if (human1.health < 0) {
            removeplayer(human1)
            human1.shape.disable() = true
            human1.shape.visible() = false
          }
        }
        }
      }
    }
  }

  def consumeFruit() {
    for (human <- this.allHumans.values) {
      for (a <- allApple) {
        if (human.collide(a)) {
          allApple.remove(allApple.indexOf(a))
          human.consumeObject(a)
          a.shape.disable() = true
          a.shape.visible() = false
        }
      }

      for (b <- allBanana) {
        if (human.collide(b)) {
          human.consumeObject(b)
          allBanana.remove(allBanana.indexOf(b))
          b.shape.disable() = true
          b.shape.visible() = false
        }
      }

      for (o <- allOrange) {
        if (human.collide(o)) {
          allOrange.remove(allOrange.indexOf(o))
          human.consumeObject(o)
          o.shape.disable() = true
          o.shape.visible() = false
        }
      }
    }
  }

  def move(): Unit = {
    for (human <- this.allHumans.values){
    if (human.leftKeyHeld) human.shape.centerX.value -= human.speed * 0.25
    if (human.rightKeyHeld) human.shape.centerX.value += human.speed * 0.25
    if (human.upKeyHeld) human.shape.centerY.value -= human.speed * 0.25
    if (human.downKeyHeld) human.shape.centerY.value += human.speed * 0.25
    }
  }

//  var h2 = new Humans
//  createComputer(h2)
//  createPlayers()


  val update: Long => Unit = (time: Long) => {
    val dt: Double = (time - lastUpdateTime) / 1000000000.0
    lastUpdateTime = time

    checkCollision()
    move()
    consumeFruit()

    timeSpawn -= dt
    if (timeSpawn < 0) {
      if (allApple.length + allBanana.length + allOrange.length >= 4) {
        timeSpawn = 5.0
      }
      else {
        createFruits(fruits(anyRandom.nextInt(3)))
        timeSpawn = 5.0
      }
    }
  }

  def sendJSON(): String = {
    var gamestate: Map[String, JsValue] = Map(
      "apples" -> Json.toJson(this.allApple.map({ apple => Json.toJson("x" -> apple.shape.centerX.toDouble, "y" -> apple.shape.centerY.toDouble, "health" -> apple.health) })),
      "bananas" -> Json.toJson(this.allBanana.map({ banana => Json.toJson("x" -> banana.shape.centerX.toDouble, "y" -> banana.shape.centerY.toDouble, "health" -> banana.health) })),
      "oranges" -> Json.toJson(this.allOrange.map({ oranges => Json.toJson("x" -> oranges.shape.centerX.toDouble, "y" -> oranges.shape.centerY.toDouble, "health" -> oranges.health) })),
      "humans" -> Json.toJson(this.allHumans.map({ case (username, player) => Json.toJson("name" -> username, "x" -> player.shape.centerX.toDouble, "y" -> player.shape.centerY.toDouble, "health" -> player.health, "speed" -> player.speed, "strength" -> player.strength) })),
      //"keymap" -> Json.toJson[Map[String, Boolean]](keyHeld),
//      "spawntime" -> Json.toJson[Double](timeSpawn),
//      "updatetime" -> Json.toJson[Double](lastUpdateTime),
      "height" -> Json.toJson[Double](maximumHeight),
      "width" -> Json.toJson[Double](maximumWidth)
    )
    Json.stringify(Json.toJson(gamestate))
  }
}
