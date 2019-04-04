package GUI

import model.Demo.fruits.{apple, banana, orange}

import model.Demo.Humans
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage

import scalafx.scene.{Group, Scene}
import javafx.scene.input.{KeyCode, KeyEvent}


object gui extends JFXApp {
  var anyRandom = scala.util.Random
  var allApple: List[apple] = List()
  val fruits: List[String] = List("apple", "banana", "orange")

  var allHumans: List[Humans] = List()
  var allBanana: List[banana] = List()
  var allOrange: List[orange] = List()

  val maximumWidth = 800
  val maximumHeight = 600

  var leftKeyHeld = false
  var rightKeyHeld = false
  var upKeyHeld = false
  var downKeyHeld = false

  var timeSpawn = 7.0
  var lastUpdateTime: Long = System.nanoTime()
  var objects = new Group {}

    def createPlayers(player: Humans): Unit = {
    player.shape.centerX = maximumWidth / 2
    player.shape.centerY = maximumHeight / 2
    objects.children.add(player.shape)
    allHumans = allHumans :+ player
  }

  def createFruits(x: String): Unit ={
    x match {
      case "apple" => {
        val apple = new apple
        apple.shape.centerX = maximumWidth*Math.random
        apple.shape.centerY = maximumHeight*Math.random
        objects.children.add(apple.shape)
        allApple = allApple :+ apple
      }
      case "orange" => {
        val orange = new orange
        orange.shape.centerX = maximumWidth*Math.random
        orange.shape.centerY = maximumHeight*Math.random
        objects.children.add(orange.shape)
        allOrange = allOrange :+ orange
      }
      case "banana" => {
        val banana = new banana
        banana.shape.centerX = maximumWidth*Math.random
        banana.shape.centerY = maximumHeight*Math.random
        objects.children.add(banana.shape)
        allBanana = allBanana :+ banana
      }
      case _ =>
    }
  }

  var h1 = new Humans
  createPlayers(h1)

  def keyPress(event: KeyEvent): Unit = {
    event.getCode.getName match {
      case "W" => upKeyHeld = true
      case "S" => downKeyHeld = true
      case "A" => leftKeyHeld = true
      case "D" => rightKeyHeld = true
      case _ =>
    }
  }

  def keyRelease(event: KeyEvent): Unit = {
    event.getCode.getName match {
      case "W" => upKeyHeld = false
      case "S" => downKeyHeld = false
      case "A" => leftKeyHeld = false
      case "D" => rightKeyHeld = false
      case _ =>
    }
  }

  this.stage = new PrimaryStage{
    fullScreen = true
    this.title = "testing"
    resizable = true
    scene = new Scene(maximumWidth, maximumHeight){
      content = List(objects)
      addEventFilter(KeyEvent.KEY_PRESSED, (event: KeyEvent)=> keyPress(event))
      addEventFilter(KeyEvent.KEY_RELEASED, (event: KeyEvent)=> keyRelease(event))
      val update: Long => Unit = (time: Long) => {
        val dt: Double = (time - lastUpdateTime) / 1000000000.0
        lastUpdateTime = time

        if(leftKeyHeld) h1.shape.translateX.value -= h1.speed*0.1
        if(rightKeyHeld) h1.shape.translateX.value += h1.speed*0.1
        if(upKeyHeld) h1.shape.translateY.value -= h1.speed*0.1
        if(downKeyHeld) h1.shape.translateY.value += h1.speed*0.1

        timeSpawn -= dt
        if (timeSpawn < 0) {
          if (allApple.length + allBanana.length + allOrange.length >= 4) {
            timeSpawn = 7.0
          }
          else {
            createFruits(fruits(anyRandom.nextInt(3)))
            timeSpawn = 5.0
          }
        }
      }
      AnimationTimer(update).start()
    }
  }

}
