package MVC.View

import scalafx.scene.{Group, Scene}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import io.socket.client.{IO, Socket}
import io.socket.emitter.Emitter
import javafx.application.Platform
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import play.api.libs.json.{JsValue, Json}
import scalafx.scene.image.{Image, ImageView}



class HandleMessagesFromPython() extends Emitter.Listener {
  override def call(objects: Object*): Unit = {
    Platform.runLater(()=> {
      val gameState: JsValue = Json.parse(objects.apply(0).toString)

      gui.fruitsList = (gameState \ "fruits").as[Map[String, Int]]
      gui.humans = (gameState \ "humans").as[List[Map[String, JsValue]]]
      gui.apples = (gameState \ "apples").as[List[Map[String, JsValue]]]
      gui.oranges = (gameState \ "oranges").as[List[Map[String, JsValue]]]
      gui.bananas = (gameState \ "bananas").as[List[Map[String, JsValue]]]
     gui.sceneGraphics = new Group()

      for(a <- gui.humans) {
        gui.sceneGraphics.children.removeAll()
        gui.createHuman(a("x").as[Double],a("y").as[Double])
      }

      for((f,v) <- gui.fruitsList) {
        gui.sceneGraphics.children.removeAll()
        if ((f == "sizeA") && (v > 0)) {
          for (a <- gui.apples) {
            gui.createFruits(a("x").as[Double], a("y").as[Double], "a")
          }
        }

        if ((f == "sizeO") && (v > 0)) {
          gui.sceneGraphics.children.removeAll()
          for (a <- gui.oranges) {
            gui.createFruits(a("x").as[Double], a("y").as[Double], "o")
          }
        }

        if ((f == "sizeB") && (v > 0)) {
          gui.sceneGraphics.children.removeAll()
          for (a <- gui.bananas) {
            gui.createFruits(a("x").as[Double], a("y").as[Double], "b")
          }
        }
      }
    })
  }

}

object gui extends JFXApp {
  var sceneGraphics: Group = new Group {}
  val maximumWidth = 1920
  val maximumHeight = 1020
  val hRad = 24
  val fRad = 10

  var socket: Socket = IO.socket("http://localhost:60000/")
  socket.connect()

  socket.emit("register", "gui1")
  socket.on("gameState", new HandleMessagesFromPython)

  def createHuman(x: Double, y: Double): Unit ={
    val player: Circle = new Circle {
      centerX = x
      centerY = y
      radius = hRad
      fill = Color.Blue
    }
    sceneGraphics.children.add(player)
  }

  def createFruits(x: Double, y: Double, t: String): Unit ={
    val player: Circle = new Circle {
      centerX = x
      centerY = y
      radius = fRad
      if (t == "a") fill = Color.Red
      else if (t == "b") fill = Color.Yellow
      else if (t == "o") fill = Color.Orange
    }
    sceneGraphics.children.add(player)
  }

  var fruitsList: Map[String, Int] = Map()
  var humans: List[Map[String, JsValue]] = List()
  var apples: List[Map[String, JsValue]] = List()
  var oranges: List[Map[String, JsValue]] = List()
  var bananas: List[Map[String, JsValue]] = List()

  socket.on("gameState", new HandleMessagesFromPython)

  this.stage = new PrimaryStage{
    fullScreen = true
    this.title = "Clash of Titans"
    scene = new Scene(maximumWidth, maximumHeight){
      content = List(sceneGraphics)
    }
  }

}
