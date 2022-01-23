package hello1

import cats.data.Kleisli
import org.http4s.{HttpRoutes, Request, Response}
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._
import org.http4s.server.blaze._
import zio._
import zio.interop.catz._
import zio.interop.catz.implicits._
import zio.Task

object Hello1 extends App {
  val server = ZIO.runtime[ZEnv]
    .flatMap { implicit rts =>
      BlazeServerBuilder[Task]
        .bindHttp(8080, "localhost")
        .withHttpApp(Hello1Service.service)
        .serve
        .compile
        .drain
    }

  override def run(args: List[String]) =
    server.exitCode
}

object Hello1Service {
  private val dsl = Http4sDsl[Task]
  import dsl._

  val service: Kleisli[Task, Request[Task], Response[Task]] = HttpRoutes.of[Task] {
      case GET -> Root => Ok("Hello")
    }.orNotFound
}
