package com.actorbhau.actores

import akka.actor.Actor
import akka.actor.Status.Failure

case class PongActor() extends  Actor {
  override def receive = {
    case "Pong" => sender() ! "Ping"
    case _ => sender() ! Failure(new Exception("Invalid Pong."))
  }
}
