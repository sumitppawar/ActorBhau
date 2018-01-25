package com.dbservice.actor

import akka.actor.Actor
import akka.actor.Status.Failure
import akka.event.Logging
import com.message.msgs.{GetValueRequest, SetValueRequest, ValueNotFoundException}

import scala.collection.mutable._

case class DbActor() extends Actor {
  val map = HashMap[String, String]()
  val logger = Logging(context.system, this)

  override def receive = {
    case SetValueRequest(k,v) => {
      map.put(k,v)
      logger.info(s"Saving key $k and value $v")
      sender() !  "Ok"
    }
    case GetValueRequest(k)  => {
      logger.info(s"Processing GetValueRequest with key $k")
      map.get(k) match {
        case Some(v) =>  sender() ! v
        case None => {
          logger.info(s"While Processing GetValueRequest value not found for $k")
          sender() ! Failure(ValueNotFoundException(s"Value not found for key $k"))
        }
      }
    }
    case _ => {
      logger.info("Invalid message.")
      sender() ! Failure(new Exception("Invalid message"))
    }
  }
}
