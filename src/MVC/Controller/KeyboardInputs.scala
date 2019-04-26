package MVC.Controller

import javafx.event.EventHandler
import javafx.scene.input.KeyEvent
import MVC.Model.Game

abstract class KeyboardInputs(playerKey: Game) extends EventHandler[KeyEvent] {
  val LEFT: String
  val RIGHT: String
  val UP: String
  val DOWN: String
  val SPACE: String

  override def handle(event: KeyEvent): Unit = {
    val keyCode = event.getCode

    event.getEventType.getName match {
      case "KEY_RELEASED" => keyCode.getName match {
        case this.LEFT => playerKey.leftKeyHeld = false
        case this.RIGHT => playerKey.rightKeyHeld = false
        case this.UP => playerKey.upKeyHeld = false
        case this.DOWN => playerKey.downKeyHeld = false
        case this.SPACE => playerKey.spaceKeyHeld = false
        case _ =>
      }

      case "KEY_PRESSED" => keyCode.getName match {
        case this.LEFT => playerKey.leftKeyHeld = true
        case this.RIGHT => playerKey.rightKeyHeld = true
        case this.UP => playerKey.upKeyHeld = true
        case this.DOWN => playerKey.downKeyHeld = true
        case this.SPACE => playerKey.spaceKeyHeld = true
        case _ =>
      }
      case _ =>

    }
  }
}

class WASDInputs(player: Game) extends KeyboardInputs(player) {
  override val LEFT: String = "A"
  override val RIGHT: String = "D"
  override val UP: String = "W"
  override val DOWN: String = "S"
  override val SPACE: String = "Space"
}
