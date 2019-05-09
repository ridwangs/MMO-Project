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

  def createPlayers(username: String): Unit = {
    val player: Humans = new Humans
    player.shape.centerX = 0.9 * maximumWidth * Math.random
    player.shape.centerY = 0.9 * maximumHeight * Math.random
    objects.children.add(player.shape)
    allHumans = allHumans + (username -> player)
    println(allHumans)
  }

  def createFruits(x: String): Unit = {
    x match {
      case "apple" =>
        val apple = new apple()
        apple.shape.centerX = 0.9 * maximumWidth * Math.random
        apple.shape.centerY = 0.9 * maximumHeight * Math.random
        objects.children.add(apple.shape)
        allApple = allApple :+ apple
      case "orange" =>
        val orange = new orange()
        orange.shape.centerX = 0.9 * maximumWidth * Math.random
        orange.shape.centerY = 0.9 * maximumHeight * Math.random
        objects.children.add(orange.shape)
        allOrange = allOrange :+ orange
      case "banana" =>
        val banana = new banana()
        banana.shape.centerX = 0.9 * maximumWidth * Math.random
        banana.shape.centerY = 0.9 * maximumHeight * Math.random
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

  def removeP(username: String): Unit = {
        allHumans = allHumans - username
  }

  def checkHealth(): Unit={
    for ((u,p) <- allHumans) {
      if (p.health < 0) {
        removeP(u)
      }
    }
  }

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
            human1.attack(human2)
          }
          if (human1.collide(human2) && human1.spaceKeyHeld) {
            val c = List(Color.Red, Color.Green, Color.Purple)
            human1.shape.fill = c(anyRandom.nextInt(3))
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

  def move(username: String, keymap: Map[String, Boolean]): Unit = {
    if (allHumans.contains(username)){
    if (keymap("leftKeyHeld")) allHumans(username).shape.centerX.value -= allHumans(username).speed
    if (keymap("rightKeyHeld")) allHumans(username).shape.centerX.value += allHumans(username).speed
    if (keymap("upKeyHeld")) allHumans(username).shape.centerY.value -= allHumans(username).speed
    if (keymap("downKeyHeld")) allHumans(username).shape.centerY.value += allHumans(username).speed
    }
  }

//  var h2 = new Humans
//  createComputer(h2)
//  createPlayers()

  val update: Long => Unit = (time: Long) => {
    val dt: Double = (time - lastUpdateTime) / 1000000000.0
    lastUpdateTime = time
    checkCollision()
    consumeFruit()
    checkHealth()

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
      "fruits" -> Json.toJson(Map("sizeA" -> allApple.length, "sizeB" -> allBanana.length, "sizeO" -> allOrange.length)),
      "apples" -> Json.toJson(allApple.map({ apple => Json.toJson(Map("x" -> apple.shape.centerX.toDouble, "y" -> apple.shape.centerY.toDouble) )})),
      "bananas" -> Json.toJson(allBanana.map({ banana => Json.toJson(Map("x" -> banana.shape.centerX.toDouble, "y" -> banana.shape.centerY.toDouble)) })),
      "oranges" -> Json.toJson(allOrange.map({ oranges => Json.toJson(Map("x" -> oranges.shape.centerX.toDouble, "y" -> oranges.shape.centerY.toDouble)) })),
      "humans" -> Json.toJson(this.allHumans.map({ case (username, player) => Json.toJson(Map(
        "name" -> Json.toJson(username),
        "x" -> Json.toJson(player.shape.centerX.toDouble),
        "y" -> Json.toJson(player.shape.centerY.toDouble))) })),
      "height" -> Json.toJson[Double](maximumHeight),
      "width" -> Json.toJson[Double](maximumWidth)
    )
    Json.stringify(Json.toJson(gamestate))
  }
}
