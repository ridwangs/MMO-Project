package Networking

import MVC.Model.Game
import akka.actor.Actor

case object move
case object update
case object sendGameState
case class GameState(json: String)
case class attack(username: String)
case class eatFruit(username: String)
case class spawn(username: String)


class GameActor extends Actor{
  var game: Game = new Game()
  override def receive: Receive = {
    case update => game.update(System.nanoTime())
    case spawn: spawn => game.createPlayers(spawn.username)
    case sendGameState => sender() ! GameState(game.sendJSON())
  }
}
