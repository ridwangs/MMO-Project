//package controller
//
//import javafx.event.EventHandler
//import javafx.scene.input.KeyEvent
//
//
//
//abstract class KeyboardInputs(player: Player) extends EventHandler[KeyEvent] {
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
//        case this.LEFT => player.leftReleased()
//        case this.RIGHT => player.rightReleased()
//        case this.UP => player.upReleased()
//        case this.DOWN => player.downReleased()
//        case _ =>
//      }
//
//      case "KEY_PRESSED" => keyCode.getName match {
//        case this.LEFT => player.leftPressed()
//        case this.RIGHT => player.rightPressed()
//        case this.UP => player.upPressed()
//        case this.DOWN => player.downPressed()
//        case _ =>
//      }
//      case _ =>
//
//    }
//  }
//}
//
//abstract class MouseInputs() extends EventHandler[KeyEvent]{
//  override def handle(event: KeyEvent): Unit = {
//    val keyCode = event.getCode
//
//    event.getEventType.getName match {
//      case "MOUSE_CLICKED"
//    }
//
//  }
//}
//
//
//
//class ArrowInputs(player: Player) extends KeyboardInputs(player) {
//  override val LEFT: String = "Left"
//  override val RIGHT: String = "Right"
//  override val UP: String = "Up"
//  override val DOWN: String = "Down"
//
//}
//
//class WASDInputs(player: Player) extends KeyboardInputs(player) {
//  override val LEFT: String = "A"
//  override val RIGHT: String = "D"
//  override val UP: String = "W"
//  override val DOWN: String = "S"
//}
