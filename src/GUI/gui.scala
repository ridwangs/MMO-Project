package GUI

import model.Demo.fruits.{apple, banana, orange}

import scala.util.Random
import model.Demo.{Humans, Inanimate_Objects}
import physics.collision
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
  var collision: collision = new collision
  var anyRandom = scala.util.Random
  val fruits: List[String] = List("apple", "banana", "orange")

  var allHumans: List[Humans] = List()
  var allApple: List[apple] = List()
  var allBanana: List[banana] = List()
  var allOrange: List[orange] = List()
  var allFruit: List[Inanimate_Objects] = List()

  val maxWidth = 1920
  val maxheight = 1080

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
        apple.shape.fill = Color.Red
        objects.children.add(apple.shape)
        allApple = allApple :+ apple
        allFruit = allFruit :+ apple
      }
      case "orange" => {
        val orange = new orange
        orange.shape.centerX = maxWidth*Math.random
        orange.shape.centerY = maxheight*Math.random
        orange.shape.fill = Color.Orange
        objects.children.add(orange.shape)
        allOrange = allOrange :+ orange
        allFruit = allFruit :+ orange
      }
      case "banana" => {
        val banana = new banana
        banana.shape.centerX = maxWidth*Math.random
        banana.shape.centerY = maxheight*Math.random
        banana.shape.fill = Color.Yellow
        objects.children.add(banana.shape)
        allBanana = allBanana :+ banana
        allFruit = allFruit :+ banana
      }
      case _ =>
    }
  }

  var h1 = new Humans
  createPlayers(h1)

 def keyPress(event: KeyCode): Unit = {
    event.getName match {
      case "W" => h1.shape.translateY.value -= h1.speed*0.5
      case "S" => h1.shape.translateY.value += h1.speed*0.5
      case "A" => h1.shape.translateX.value -= h1.speed*0.5
      case "D" => h1.shape.translateX.value += h1.speed*0.5
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
        for (things <- allFruit){
          if(collision.collide(allHumans.head.shape, things.shape) == true){
            collision.stayput(allHumans.head.shape, things.shape)
          }
        }
      }

      addEventFilter(KeyEvent.KEY_PRESSED, (event: KeyEvent)=> keyPress(event.getCode))
      AnimationTimer(update).start()
    }
  }

}
