import akka.actor.{ActorSystem, Props}
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.dbservice.actor.DbActor
import com.message.msgs.{GetValueRequest, SetValueRequest, ValueNotFoundException}
import org.scalatest.{FunSpecLike, Matchers}
import akka.pattern.ask

import scala.concurrent.Await
import scala.concurrent.duration._

class DbActorSpec extends FunSpecLike with Matchers {
  implicit val timeout = Timeout(2 seconds)
  implicit val actorSystem = ActorSystem()

  describe("DbActorSpec") {
    it("should save key/value") {
      val actorRef = TestActorRef(DbActor())
      val setRequest = SetValueRequest("G01172498", "Sumit Pawar")
      actorRef ! setRequest
      val actor = actorRef.underlyingActor
      actor.map.get("G01172498") should equal(Some("Sumit Pawar"))
    }

    it("should return ValueNotFoundException if value is not present") {
      val actorRef = actorSystem.actorOf(Props(classOf[DbActor]))
      val futureReply = actorRef ? GetValueRequest("G01172498")
      intercept[ValueNotFoundException] {
        Await.result(futureReply, 2 seconds)
      }
    }

    it("should reponse with Exeption for invalid Msg") {
      val actorRef = actorSystem.actorOf(Props(classOf[DbActor]))
      val futureReply = actorRef ? "Unknown"
      intercept[Exception] {
        Await.result(futureReply, 2 seconds)
      }
    }
  }

}