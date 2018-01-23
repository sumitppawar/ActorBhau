package com.actorbhau.actores

import akka.actor.Actor
import akka.event.Logging
import com.actorbhau.message.SetRequest

import scala.collection.mutable.HashMap

class DbCrudHandler() extends Actor {
  val map = new HashMap[String, Any]()
  val log = Logging(context.system, this)

  override def receive = {
    case SetRequest(key, value) =>
      log.info(s"Recieved setRequest with key $key, value $value")
      map.put(key, value)
    case _ =>
      log.info("Invalid message for DbCrudHandler")
  }
}
