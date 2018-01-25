package com.dbservice

import akka.actor.{ActorSystem, Props}
import com.dbservice.actor.DbActor

object Main extends App {
  val actorSystem = ActorSystem("dbservice")
  val actorRef = actorSystem.actorOf(Props(classOf[DbActor]), name = "DbActor")
}
