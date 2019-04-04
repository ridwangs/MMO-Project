package GUI

import model.Demo.fruits.{apple, banana, orange}
import model.Demo.Humans
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Group, Scene}
import javafx.scene.input.KeyEvent
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.shape.Circle

import scala.collection.mutable.ListBuffer

object gui extends JFXApp {
  var anyRandom = scala.util.Random
  var allHumans: ListBuffer[Humans] = ListBuffer()
  val fruits: List[String] = List("apple", "banana", "orange")
  var allApple: ListBuffer[apple] = ListBuffer()
  var allBanana: ListBuffer[banana] = ListBuffer()
  var allOrange: ListBuffer[orange] = ListBuffer()

  val maximumWidth = 1980
  val maximumHeight = 1020

  var leftKeyHeld = false
  var rightKeyHeld = false
  var upKeyHeld = false
  var downKeyHeld = false

  var timeSpawn = 7.0
  var lastUpdateTime: Long = System.nanoTime()
  var objects: Group = new Group {}

  def createPlayers(player: Humans): Unit = {
    player.shape.centerX = maximumWidth / 2
    player.shape.centerY = maximumHeight / 2
    objects.children.add(player.shape)
    allHumans = allHumans :+ player
  }
  def createComputer(player: Humans): Unit ={
    player.shape.centerX = maximumWidth*(Math.random())
    player.shape.centerY = maximumHeight*(Math.random())
    objects.children.add(player.shape)
    allHumans = allHumans :+ player
  }

  def createFruits(x: String): Unit ={
    x match {
      case "apple" =>
        val apple = new apple()
        apple.shape.centerX = maximumWidth*Math.random
        apple.shape.centerY = maximumHeight*Math.random
        objects.children.add(apple.shape)
        allApple = allApple :+ apple
      case "orange" =>
        val orange = new orange()
        orange.shape.centerX = maximumWidth*Math.random
        orange.shape.centerY = maximumHeight*Math.random
        objects.children.add(orange.shape)
        allOrange = allOrange :+ orange
      case "banana" =>
        val banana = new banana()
        banana.shape.centerX = maximumWidth*Math.random
        banana.shape.centerY = maximumHeight*Math.random
        objects.children.add(banana.shape)
        allBanana = allBanana :+ banana
      case _ =>
    }
  }

  var h1 = new Humans
  createPlayers(h1)

  var h2 = new Humans
  createComputer(h2)

  def collide(circle1: Circle, circle2: Circle): Boolean = {
    var xdistance = circle1.centerX.value - circle2.centerX.value
    var ydistance = circle1.centerY.value - circle2.centerY.value
    var sumradius = Math.sqrt(xdistance*xdistance + ydistance*ydistance)
    sumradius < circle1.radius.value+circle2.radius.value
  }
  def stayput(circle1: Circle, circle2: Circle): Unit = {
    val sumradius = circle1.radius.toDouble + circle2.radius.toDouble
    var xdistance = circle1.centerX.toDouble - circle2.centerX.toDouble
    var ydistance = circle1.centerY.toDouble - circle2.centerY.toDouble
    var length = Math.sqrt(Math.pow(xdistance, 2)+ Math.pow(ydistance, 2))
    circle1.centerX = circle2.centerX.toDouble + sumradius * (xdistance/length)
    circle1.centerY = circle2.centerY.toDouble + sumradius * (ydistance/length)
  }

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

        if(collide(h1.shape,h2.shape )){
          stayput(h1.shape, h2.shape)
        }


        for(a<- allApple){
          if(collide(h1.shape,a.shape)){
          //  remF(allApple.,allApple.indexOf(a))
            allApple.remove(allApple.indexOf(a))
            h1.consumeObject(a)
            a.shape.disable
            a.shape.visible = false
          }
        }

        for(b<- allBanana){
          if(collide(h1.shape,b.shape)){
            h1.consumeObject(b)
            allBanana.remove(allBanana.indexOf(b))
            b.shape.disable
            b.shape.visible = false
          }
        }

        for(o<- allOrange){
          if(collide(h1.shape,o.shape)){
            allOrange.remove(allOrange.indexOf(o))
            h1.consumeObject(o)
            o.shape.disable() = true
            o.shape.visible() = false
          }
        }


       if(leftKeyHeld) h1.shape.centerX.value -= h1.speed*0.25
        if(rightKeyHeld) h1.shape.centerX.value += h1.speed*0.25
        if(upKeyHeld) h1.shape.centerY.value -= h1.speed*0.25
        if(downKeyHeld) h1.shape.centerY.value += h1.speed*0.25

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
