package GUI

import model.Demo.{Humans, apple}
import javafx.scene.shape.Circle
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.scene.{shape, _}
import scalafx.scene.shape.Circle
import scalafx.scene.canvas.Canvas
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.transform.Translate

import scala.collection.immutable.Range


object gui extends JFXApp{
  var objects = new Group {}
  val width = 1920
  val height = 1080
  var human: Humans = new Humans
  var Apple: apple = new apple
  var lastUpdateTime: Long = System.nanoTime()
  val circle = new shape.Circle{
    centerX = width/2
    centerY = height/2
    radius = 32.0
    fill = Color.Yellow
    var health = human.health
    var speed = human.speed
    var strength = human.strength
  }
  val redapple = new shape.Circle{
    centerX = (1920)*Math.random()/2 + circle.centerX.toDouble
    centerY = (1080)*Math.random()/2 + circle.centerY.toDouble
    radius = 12.0
    fill = Color.Red
  }
  var orange = new shape.Circle{
    centerX = (1920)*Math.random()/2 + circle.centerX.toDouble
    centerY = (1080)*Math.random()/2 + circle.centerY.toDouble
    radius = 12.0
    fill = Color.Orange
  }
  objects.children.add(circle)
  objects.children.add(redapple)
  objects.children.add(orange)


  def keyPress(event: KeyCode): Unit = {
    event.getName match {
      case "W" => circle.translateY.value -= human.speed*0.1
      case "S" => circle.translateY.value += human.speed*0.1
      case "A" => circle.translateX.value -= human.speed*0.1
      case "D" => circle.translateX.value += human.speed*0.1
      case _ =>
    }
  }

  this.stage = new PrimaryStage{
    fullScreen = true
    this.title = "testing"
    resizable = true
    scene = new Scene(1920, 1080){
      content = List(objects)
      addEventFilter(KeyEvent.KEY_PRESSED, (event: KeyEvent)=> keyPress(event.getCode))
    }
  }

}
