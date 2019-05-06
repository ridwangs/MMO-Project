package Networking

import MVC.Model.Game
import akka.actor.Actor

case object move
case object update
case object spawn
case object sendGameState
case class GameState(json: String)
case class attack(username: String)
case class eatFruit(username: String)


class GameActor(username: String) extends Actor{
  var game: Game = new Game(username)
  override def receive: Receive = {
    case update => game.update(System.nanoTime())
    case spawn => game.createPlayers()
    case sendGameState => sender() ! GameState(game.sendJSON())
  }
}
