package view

import javafx.scene.input.MouseEvent
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.{Group, Scene}
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.image.{Image, ImageView}
import scalafx.animation.AnimationTimer
import javafx.scene.transform.Translate

object Background extends JFXApp {
  var lastUpdateTime: Long = System.nanoTime()
  val windowWidth: Double = 1600
  val windowHeight: Double = 1000

  val characterWidth: Double = 70
  val characterHeight: Double = 70

  val zLocX: Double = 500
  val zLocY: Double = 200
  val zSpeed: Double = 1.5

  val hLocX: Double = 1500
  val hLocY: Double = 550

  var sceneGraphics: Group = new Group {}
  var allZombies: List[ImageView] = List()
  var allHumans: List[ImageView] = List()

  def createZombie(centerX: Double, centerY: Double): Unit = {
    val newZombie = new Image("view/zombie.png")
    val viewZombie = new ImageView(newZombie)
    viewZombie.setX(centerX)
    viewZombie.setY(centerY)
    viewZombie.setFitHeight(characterHeight)
    viewZombie.setFitWidth(characterWidth)
    sceneGraphics.children.add(viewZombie)
    allZombies = viewZombie :: allZombies
  }

  def createHuman(centerX: Double, centerY: Double): Unit ={
    val human = new Image("view/human.png")
    val viewHuman = new ImageView(human)
    viewHuman.setFitHeight(characterHeight)
    viewHuman.setFitWidth(characterWidth)
    viewHuman.setX(hLocX)
    viewHuman.setY(hLocY)
    sceneGraphics.children.add(viewHuman)
    allHumans = viewHuman :: allHumans
  }

  stage = new PrimaryStage {
    title = "Humans VS Zombies"
    fullScreen = true
      scene = new Scene(windowWidth, windowHeight) {
        val bg = new Image("view/bg.jpg")
        val view = new ImageView(bg)
        view.setFitHeight(1200)
        view.setFitWidth(2000)
        val human = new Image("view/human.png")
        val h = new ImageView(human)
        h.setFitHeight(characterHeight)
        h.setFitWidth(characterWidth)
        h.setX(windowWidth/2)
        h.setY(windowHeight/2)

        for (i <- 0 to 4) {
          createZombie(Math.random() * windowWidth, Math.random() * windowHeight)
        }

        onMouseMoved = (event: MouseEvent) => {
          x_=(windowWidth/2-event.x)
          y_=(windowHeight/2-event.y)
          h.setX(event.x)// - 0.5 * characterWidth)
          h.setY(event.y)// - 0.5 * characterHeight)
        }

//
//        h.setTranslateX(-h.getX)
//        h.setTranslateY(-h.getY)



        val update: Long => Unit = (time: Long) => {
          val dt: Double = (time - lastUpdateTime) / 1000000000.0
          lastUpdateTime = time

          h.getTranslateX
          h.getTranslateY
          x_=(h.getX)
          y_=(h.getY)




          for (z <- allZombies) {
            var dx = h.getX - z.getX
            var dy = h.getY - z.getY
            var dist = Math.sqrt(dx * dx + dy * dy)
            z.setX(z.getX + dx / dist * zSpeed)
            z.setY(z.getY + dy / dist * zSpeed)
          }


        }

        content = List(view, h, sceneGraphics)
        addEventHandler(MouseEvent.MOUSE_CLICKED, (event: MouseEvent) => createZombie(event.getX, event.getY))
        AnimationTimer(update).start()
      }
  }
}