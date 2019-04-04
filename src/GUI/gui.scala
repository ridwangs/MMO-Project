package GUI

import model.Demo.fruits.{apple, banana, orange}

import scala.util.Random
import model.Demo.Humans
//import javafx.scene.shape.Circle
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.scene.{shape}

import scalafx.scene.{Group, Scene}
import scalafx.scene.shape.{Shape, Circle}
import scalafx.scene.canvas.Canvas
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.transform.Translate

import scala.collection.immutable.Range


object gui extends JFXApp {
  var anyRandom = scala.util.Random
  var allApple: List[apple] = List()
  val fruits: List[String] = List("apple", "banana", "orange")

  var allHumans: List[Humans] = List()
  var allBanana: List[banana] = List()
  var allOrange: List[orange] = List()

  val maxWidth = 1980
  val maxheight = 1020

  var timeSpawn = 5.0
  var lastUpdateTime: Long = System.nanoTime()
  var objects = new Group {}

    def createPlayers(player: Humans): Unit = {
    player.shape.centerX = maxWidth / 2
    player.shape.centerY = maxheight / 2
    objects.children.add(player.shape)
    allHumans = allHumans :+ player
  }

  def createFruits(x: String): Unit ={
    x match {
      case "apple" => {
        val apple = new apple
        apple.shape.centerX = maxWidth*Math.random
        apple.shape.centerY = maxheight*Math.random
        objects.children.add(apple.shape)
        allApple = allApple :+ apple
      }
      case "orange" => {
        val orange = new orange
        orange.shape.centerX = maxWidth*Math.random
        orange.shape.centerY = maxheight*Math.random
        objects.children.add(orange.shape)
        allOrange = allOrange :+ orange
      }
      case "banana" => {
        val banana = new banana
        banana.shape.centerX = maxWidth*Math.random
        banana.shape.centerY = maxheight*Math.random
        objects.children.add(banana.shape)
        allBanana = allBanana :+ banana
      }
      case _ =>
    }
  }

  var h1 = new Humans
  createPlayers(h1)

 def keyPress(event: KeyCode): Unit = {
    event.getName match {
      case "W" => h1.shape.translateY.value -= h1.speed*0.1
      case "S" => h1.shape.translateY.value += h1.speed*0.1
      case "A" => h1.shape.translateX.value -= h1.speed*0.1
      case "D" => h1.shape.translateX.value += h1.speed*0.1
      case _ =>
    }
  }

  this.stage = new PrimaryStage{
    fullScreen = true
    this.title = "testing"
    resizable = true
    scene = new Scene(maxWidth, maxheight){
      content = List(objects)
      val update: Long => Unit = (time: Long) => {
        val dt: Double = (time - lastUpdateTime) / 1000000000.0
        lastUpdateTime = time

        timeSpawn -= dt
        if (timeSpawn < 0) {
          if (allApple.length + allBanana.length + allOrange.length >= 4) {
            timeSpawn
          }
          else {
            createFruits(fruits(anyRandom.nextInt(3)))
            timeSpawn = 5.0
          }
        }
      }

      addEventFilter(KeyEvent.KEY_PRESSED, (event: KeyEvent)=> keyPress(event.getCode))
      AnimationTimer(update).start()
    }
  }

}
