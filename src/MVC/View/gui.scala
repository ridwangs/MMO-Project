package MVC.View

import MVC.Controller.WASDInputs
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import javafx.scene.input.KeyEvent
import MVC.Model.Game
import scalafx.scene.image.{Image, ImageView}

object gui extends JFXApp {


  var g = new Game()
  this.stage = new PrimaryStage{
    fullScreen = true
    this.title = "Clash of Titans"
    scene = new Scene(g.maximumWidth, g.maximumHeight){
      val bg = new Image("MVC/View/pbg.png")
      val view = new ImageView(bg)
      view.setFitWidth(g.maximumWidth)
      content = List(view,g.objects)
      addEventFilter(KeyEvent.ANY, new WASDInputs(g))
      AnimationTimer(g.update).start()
    }
  }

}
