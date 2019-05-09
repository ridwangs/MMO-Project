package Networking

import MVC.Model.Game
import akka.actor.Actor


case object UpdateGame

case class spawn(username: String)
case class move(username: String, keyMap: Map[String, Boolean])

class GameActor extends Actor{
  var game: Game = new Game()

  override def receive: Receive = {
    case UpdateGame =>
      game.update(System.nanoTime())
      sender() ! GameState(game.sendJSON())

    case spawn: spawn =>
      game.createPlayers(spawn.username)
      sender() ! GameState(game.sendJSON())
    case move: move =>
      game.move(move.username, move.keyMap)
    case SendGameState =>
      sender() ! GameState(game.sendJSON())
  }
}
