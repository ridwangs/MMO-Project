//package MVC.Controller
//
//import javafx.event.EventHandler
//import javafx.scene.input.KeyEvent
//import MVC.Model.Game
//import MVC.Model.objects.Humans
//
//class KeyboardInputs(playerKey: Game) extends EventHandler[KeyEvent] {
//  val LEFT: String
//  val RIGHT: String
//  val UP: String
//  val DOWN: String
//  val SPACE: String
//
//
//  val js = Map()
//
//  override def handle(event: KeyEvent, value: Boolean): Unit = {
//    val keyCode = event.getCode
//
//    event.getEventType.getName match {
//      case "KEY_RELEASED" => keyCode.getName match {
//        case this.LEFT => playerKey.h1.leftKeyHeld = false
//        case this.RIGHT => playerKey.h1.rightKeyHeld = false
//        case this.UP => playerKey.h1.upKeyHeld = false
//        case this.DOWN => playerKey.h1.downKeyHeld = false
//        case this.SPACE => playerKey.h1.spaceKeyHeld = false
//        case _ =>
//      }
//
//      case "KEY_PRESSED" => keyCode.getName match {
//        case this.LEFT => playerKey.h1.leftKeyHeld = true
//        case this.RIGHT => playerKey.h1.rightKeyHeld = true
//        case this.UP => playerKey.h1.upKeyHeld = true
//        case this.DOWN => playerKey.h1.downKeyHeld = true
//        case this.SPACE => playerKey.h1.spaceKeyHeld = true
//        case _ =>
//      }
//      case _ =>
//
//    }
//  }
//}
//
//class WASDInputs(player: Game) extends KeyboardInputs(player) {
//  override val LEFT: String = "A"
//  override val RIGHT: String = "D"
//  override val UP: String = "W"
//  override val DOWN: String = "S"
//  override val SPACE: String = "Space"
//}
