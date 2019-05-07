package Networking

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.io.UdpConnected.Disconnected
import akka.io.{IO, Tcp}
import akka.util.ByteString

class GameServer(gameActor: ActorRef) extends Actor{
  import Tcp._
  import context.system

  IO(Tcp) ! Bind(self , new InetSocketAddress("localhost", 8000))
  var clients: Set[ActorRef] = Set()
  var delimiter: String = "~"
  var childactorMap: Map[String, ActorRef] = Map()
  var username: String = ""
  var action: String = ""
  override def receive: Receive = {
    case bound: Bound => println("Listening on port:" + bound.localAddress)
    case connected:Connected =>
      this.clients + sender()
      sender() ! Register(self)
    case closed: Disconnected =>
      this.clients - sender()
    case messageReceived: Received =>
      var username: String = messageReceived.data.utf8String
      val childActor: ActorRef = context.actorOf(Props(classOf[GameActor], username))
      action match{
        case "spawn" =>
          childActor ! spawn
        case "update" =>
          childActor ! update
        case "sendGameState" =>
          childActor ! sendGameState
    }
    case sendGameState =>
      gameActor ! sendGameState
    case gs: GameState =>
      this.clients.foreach((clients: ActorRef) => clients ! Write(ByteString(gs.json + delimiter )))

  }
}
object GameServer {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem()

    import actorSystem.dispatcher

    import scala.concurrent.duration._

    val gameActor = actorSystem.actorOf(Props(classOf[GameActor], "yjgfj"))
    val server = actorSystem.actorOf(Props(classOf[GameServer], gameActor))

    actorSystem.scheduler.schedule(16.milliseconds, 32.milliseconds, gameActor, update)
    actorSystem.scheduler.schedule(32.milliseconds, 32.milliseconds, server, sendGameState)
  }

}

