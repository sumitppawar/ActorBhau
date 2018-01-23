package com.actorbhau.actores

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.actorbhau.message.SetRequest
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}

class DbCrudHandlerSpec extends FunSpecLike with Matchers
  with BeforeAndAfterEach {
  implicit val actorSystem = ActorSystem()
  describe("DbCrudHandler") {
    describe("given SetRequest") {
      it("should place key/value in map") {
        val actorRef = TestActorRef(new DbCrudHandler())
        val key = "G01172498"
        val name = "Sumit Pawar"
        val setRequest = SetRequest(key, name)
        actorRef ! setRequest
        val actor = actorRef.underlyingActor
        actor.map.get(key) should equal(Some(name))
      }
    }
  }
}
