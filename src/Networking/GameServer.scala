package Networking

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}
import akka.io.{IO, Tcp}
import akka.util.ByteString
import play.api.libs.json.{JsValue, Json}


case object SendGameState
case class GameState(gameState: String)

class GameServer(gameActor: ActorRef) extends Actor{
  import Tcp._
  import context.system

  IO(Tcp) ! Bind(self , new InetSocketAddress("localhost", 8000))

  var clients: Set[ActorRef] = Set()
  var childactorMap: Map[String, ActorRef] = Map()
  var username: String = ""
  var action: String = ""
  var buffer: String = ""
  val delimiter: String = "~"

  override def receive: Receive = {
    case bound: Bound => println("Listening on port:" + bound.localAddress)

    case c: Connected =>
      this.clients = this.clients + sender()
      sender() ! Register(self)

    case PeerClosed =>
      this.clients = this.clients - sender()

    case r: Received =>
      buffer += r.data.utf8String
      while (buffer.contains(delimiter)) {
        val curr = buffer.substring(0, buffer.indexOf(delimiter))
        buffer = buffer.substring(buffer.indexOf(delimiter) + 1)
        handleMessageFromWebServer(curr)
      }

    case SendGameState =>  //gameActor ! SendGameState
      for((u, a) <- childactorMap){
        a ! SendGameState
      }

    case gs: GameState =>
      this.clients.foreach((clients: ActorRef) => clients ! Write(ByteString(gs.gameState + delimiter )))

  }

  def handleMessageFromWebServer(messageString:String):Unit = {
    val message: JsValue = Json.parse(messageString)
    val username = (message \ "username").as[String]
    val messageType = (message \ "action").as[String]

    messageType match {
      case "spawn" =>
        childactorMap = childactorMap + (username -> gameActor)
        childactorMap (username) ! spawn(username)
      case _ =>

    }
  }
}
object GameServer {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem()

    import actorSystem.dispatcher

    import scala.concurrent.duration._

    val gameActor = actorSystem.actorOf(Props(classOf[GameActor]))
    val server = actorSystem.actorOf(Props(classOf[GameServer], gameActor))

    actorSystem.scheduler.schedule(16 milliseconds, 32 milliseconds, gameActor, UpdateGame)
    actorSystem.scheduler.schedule(32 milliseconds, 32 milliseconds, server, SendGameState)
  }

}

