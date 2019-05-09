//package MVC.View
//
////import MVC.Controller.WASDInputs
//import scalafx.animation.AnimationTimer
//import scalafx.application.JFXApp
//import scalafx.application.JFXApp.PrimaryStage
//import scalafx.scene.Scene
//import javafx.scene.input.KeyEvent
//import MVC.Model.Game
//import io.socket.client.{IO, Socket}
//import io.socket.emitter.Emitter
//import scalafx.scene.image.{Image, ImageView}
//
//
//
//class HandleMessagesFromPython() extends Emitter.Listener {
//
//
//  override def call(objects: Object*): Unit = {
//  }
//}
//
//
//
//object gui extends JFXApp {
//  val maximumWidth = 1920
//  val maximumHeight = 1020
//
//  var socket: Socket = IO.socket("http://localhost:60000/")
//  socket.connect()
//
//  socket.emit("register", "gui1")
//
//
//  document.addEventHandler(KeyEvent.KEY_RELEASED, (event: KeyEvent) => {
//    /* Code here is executed when the user edits the text   */
//    socket.emit("move", document.text.value)
//  })
//  socket.on("move", new HandleMessagesFromPython)
//
//  this.stage = new PrimaryStage{
//    fullScreen = true
//    this.title = "Clash of Titans"
//    scene = new Scene(maximumWidth, maximumHeight){
//
//      val bg = new Image("MVC/View/pbg.png")
//      val view = new ImageView(bg)
//      view.setFitWidth(maximumWidth)
//      content = List(view,g.objects)
////      addEventFilter(KeyEvent.ANY, new WASDInputs(g))
//      AnimationTimer(g.update).start()
//    }
//  }
//
//}
