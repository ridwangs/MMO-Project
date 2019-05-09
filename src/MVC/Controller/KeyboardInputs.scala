//package MVC.Controller
//
//import javafx.event.EventHandler
//import javafx.scene.input.KeyEvent
//import MVC.Model.Game
//import MVC.Model.objects.Humans
//import io.socket.client.Socket
//
//class KeyboardInputs(socket: Socket) extends EventHandler[KeyEvent] {
//  var keys: Map[String, Boolean] = Map(
//    "leftKeyHeld" -> false,
//    "rightKeyHeld" -> false,
//    "upKeyHeld" -> false,
//    "downKeyHeld" -> false,
//    "spaceKeyHeld" -> false
//  )
//
//  override def handle(event: KeyEvent): Unit = {
//    val keyCode = event.getCode
//
//    event.getEventType.getName match {
//      case "KEY_RELEASED" => keyCode.getName match {
//        case "A" => keys("leftKeyHeld") = false
//        case "D" => keys("rightKeyHeld") = false
//        case "S" => keys("downKeyHeld") = false
//        case "W" => keys("upKeyHeld") = false
//        case "Space" => keys("spaceKeyHeld") = false
//        case _ =>
//      }
//
//      case "KEY_PRESSED" => keyCode.getName match {
//        case "A" => keys("leftKeyHeld") = true
//        case "D" => keys("rightKeyHeld") = true
//        case "S" => keys("downKeyHeld") = true
//        case "W" => keys("upKeyHeld") = true
//        case "Space" => keys("spaceKeyHeld") = true
//        case _ =>
//      }
//      case _ =>
//
//    }
//  }
//  socket.emit("keyStates", keys)
//}
//
////class WASDInputs(player: Game, username: String) extends KeyboardInputs(player, username) {
////  override val LEFT: String = "A"
////  override val RIGHT: String = "D"
////  override val UP: String = "W"
////  override val DOWN: String = "S"
////  override val SPACE: String = "Space"
////}
