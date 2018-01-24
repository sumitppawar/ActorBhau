package com.actorbhau.actores

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout

import scala.concurrent.duration._
import org.scalatest.{FunSpecLike, Matchers}
import akka.pattern.ask

import scala.concurrent.Await

class PongActorSpec extends FunSpecLike with Matchers  {
  val system = ActorSystem()
  val pongActor = system.actorOf(Props(classOf[PongActor]))
  implicit val timeout = Timeout(10 seconds)

  describe("PongActor") {
    it("should respond with Ping") {
      val futureRreply = pongActor ? "Pong"
      val reply = Await.result(futureRreply, 1 seconds)
      reply should equal("Ping")
    }

    it("should fail on Unknown message") {
      val futureRreply = pongActor ? "Unknown"
      intercept[Exception] {
        Await.result(futureRreply, 1 seconds)
      }
    }
  }
}
