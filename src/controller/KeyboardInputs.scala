//package controller
//
//import javafx.event.EventHandler
//import javafx.scene.input.KeyEvent
//import model.Demo.Humans
//
//
//
//
//abstract class KeyboardInputs(player: Humans) extends EventHandler[KeyEvent] {
//  val LEFT: String
//  val RIGHT: String
//  val UP: String
//  val DOWN: String
//
//  override def handle(event: KeyEvent): Unit = {
//    val keyCode = event.getCode
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
///*abstract class MouseInputs() extends EventHandler[KeyEvent]{
//  override def handle(event: KeyEvent): Unit = {
//    val keyCode = event.getCode
//
//    event.getEventType.getName match {
//      case "MOUSE_CLICKED"
//    }
//
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
//
//  if(player.leftKeyHeld){
//    player.shape.translateX.value -= player.speed*0.1
//  }
//
//  if(player.rightKeyHeld){
//    player.shape.translateX.value += player.speed*0.1
//  }
//
//  if(player.upKeyHeld){
//    player.shape.translateY.value -= player.speed*0.1
//  }
//
//  if(player.downKeyHeld){
//    player.shape.translateY.value += player.speed*0.1
//  }
//
//}
//
//class WASDInputs(player: Humans) extends KeyboardInputs(player) {
//  override val LEFT: String = "A"
//  override val RIGHT: String = "D"
//  override val UP: String = "W"
//  override val DOWN: String = "S"
//}
