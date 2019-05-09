package Networking

import MVC.Model.Game
import akka.actor.Actor


case object UpdateGame

case class spawn(username: String)

class GameActor extends Actor{
  var game: Game = new Game()

  override def receive: Receive = {
    case UpdateGame =>
      game.update(System.nanoTime())

    case spawn: spawn =>
      game.createPlayers(spawn.username)
      sender() ! GameState(game.sendJSON())

    case SendGameState =>
      sender() ! GameState(game.sendJSON())
  }
}
