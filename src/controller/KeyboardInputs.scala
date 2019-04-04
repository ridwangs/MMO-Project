//package controller
//
//import GUI.gui.{downKeyHeld, leftKeyHeld, rightKeyHeld, upKeyHeld}
//import javafx.event.EventHandler
//import javafx.scene.input.KeyEvent
//import model.Demo.Humans
//
//
//
//abstract class KeyboardInputs(player: Humans) extends EventHandler[KeyEvent] {
//
//  val LEFT: String
//  val RIGHT: String
//  val UP: String
//  val DOWN: String
//
//  override def handle(event: KeyEvent): Unit = {
//    val keyCode = event.getCode
//
//    event.getEventType.getName match {
//      case "KEY_RELEASED" => keyCode.getName match {
//        case this.LEFT =>
//          player.leftKeyHeld = false
//        case this.RIGHT => player.rightKeyHeld = false
//        case this.UP => player.upKeyHeld = false
//        case this.DOWN => player.downKeyHeld = false
//        case _ =>
//      }
//
//      case "KEY_PRESSED" => keyCode.getName match {
//        case this.LEFT => player.leftKeyHeld = true
//        case this.RIGHT => player.rightKeyHeld = true
//        case this.UP => player.upKeyHeld = true
//        case this.DOWN => player.downKeyHeld = true
//        case _ =>
//      }
//      case _ =>
//
//    }
//  }
//}
//
///*abstract class MouseInputs() extends EventHandler[KeyEvent]{
//  override def handle(event: KeyEvent): Unit = {
//    val keyCode = event.getCode
//
//    event.getEventType.getName match {
//      case "MOUSE_CLICKED"
//    }
//w
//  }
//}*/
//
//
//
//class ArrowInputs(player: Humans) extends KeyboardInputs(player) {
//  override val LEFT: String = "Left"
//  override val RIGHT: String = "Right"
//  override val UP: String = "Up"
//  override val DOWN: String = "Down"
//
//}
//
//class WASDInputs(player: Humans) extends KeyboardInputs(player) {
//  override val LEFT: String = "A"
//  override val RIGHT: String = "D"
//  override val UP: String = "W"
//  override val DOWN: String = "S"
//}
