package MVC.Controller

import javafx.event.EventHandler
import javafx.scene.input.KeyEvent
import MVC.Model.Game
import MVC.Model.objects.Humans
import io.socket.client.Socket
import play.api.libs.json.Json


class KeyboardInputs(socket: Socket ) extends EventHandler[KeyEvent] {
  val LEFT: String = "A"
  val RIGHT: String = "D"
  val UP: String = "W"
  val DOWN: String = "S"
  val SPACE: String = "Space"

  var keys: Map[String, Boolean] = Map(
    "leftKeyHeld" -> false,
    "rightKeyHeld" -> false,
    "upKeyHeld" -> false,
    "downKeyHeld" -> false,
    "spaceKeyHeld" -> false
  )

  override def handle(event: KeyEvent): Unit = {
    val keyCode = event.getCode

    event.getEventType.getName match {
      case "KEY_RELEASED" => keyCode.getName match {
        case this.LEFT => keys = keys.updated("leftKeyHeld", false)
        case this.RIGHT => keys = keys.updated("rightKeyHeld", false)
        case this.DOWN => keys = keys.updated("downKeyHeld", false)
        case this.UP => keys = keys.updated("upKeyHeld", false)
        case this.SPACE => keys = keys.updated("spaceKeyHeld", false)
        case _ =>
      }

      case "KEY_PRESSED" => keyCode.getName match {
        case this.LEFT => keys = keys.updated("leftKeyHeld", true)
        case this.RIGHT => keys = keys.updated("rightKeyHeld", true)
        case this.DOWN => keys = keys.updated("downKeyHeld", true)
        case this.UP => keys = keys.updated("upKeyHeld", true)
        case this.SPACE => keys = keys.updated("spaceKeyHeld", true)
        case _ =>
      }
      case _ =>

    }
  }
  socket.emit("keyStates", Json.toJson(keys))
}
//
//class WASDInputs(player: Game, username: String) extends KeyboardInputs(player, username) {
//  override val LEFT: String = "A"
//  override val RIGHT: String = "D"
//  override val UP: String = "W"
//  override val DOWN: String = "S"
//  override val SPACE: String = "Space"
//}
