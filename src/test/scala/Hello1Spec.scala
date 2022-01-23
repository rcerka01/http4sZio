import org.http4s._
import org.http4s.implicits._
import zio._
import zio.interop.catz._
import zio.test._
import zio.test.Assertion._
import TestAspect._
import Uri._
import hello1.Hello1Service

object Hello1Spec extends DefaultRunnableSpec {
  override def spec: ZSpec[_root_.zio.test.environment.TestEnvironment, Any] =
    suite("Hello 1 test") (
      testM("root returns OK") {
        for {
          response <- Hello1Service.service.run(Request[Task](Method.GET, uri"/"))
        } yield assert(response.status)(equalTo(Status.Ok))
      },
      testM("status MotFound with assertM") {
        assertM(Hello1Service.service.run(Request[Task](Method.GET, uri"/a")).map(_.status)) {
          equalTo(Status.NotFound)
        }
      },
      testM("root body returns hello") {
        val io = for {
          response: Response[Task] <- Hello1Service.service.run(Request[Task](Method.GET, uri"/"))
          body <- response.body.compile.toVector.map(_.map(_.toChar).mkString(""))
        } yield body
        assertM(io)(equalTo("Hello"))
      }
    ) @@ sequential
}
