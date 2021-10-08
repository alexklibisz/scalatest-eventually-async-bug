import org.scalatest.concurrent.Eventually
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.freespec.AsyncFreeSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

class EventuallyAsync32xSpec extends AsyncFreeSpec with Eventually with Matchers {

  implicit override val patienceConfig = PatienceConfig(
    timeout = Span(5, Seconds),
    interval = Span(10, Millis)
  )

// This still times out, but that's fair, because we shouldn't use
// Await.result in an async suite anyways.
//  "Await with default EC" in {
//    val it = (1 to 10).iterator
//    def next(): Future[Int] = Future(it.next())
//    eventually {
//      val x = Await.result(next(), 100.millis)
//      x shouldBe 3
//    }
//  }

  "map with default EC" in {
    val it = (1 to 10).iterator
    def next(): Future[Int] = Future(it.next())
    eventually {
      next().map(_ shouldBe 3)
    }
  }

  "Await with global EC" in {
    val it = (1 to 10).iterator
    def next(): Future[Int] = Future(it.next())(ExecutionContext.global)
    eventually {
      val x = Await.result(next(), 100.millis)
      x shouldBe 3
    }
  }

  "map with global EC" in {
    val it = (1 to 10).iterator
    def next(): Future[Int] = Future(it.next())(ExecutionContext.global)
    eventually {
      next().map(_ shouldBe 3)
    }
  }

}
