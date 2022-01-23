import sbt.Keys.scalacOptions

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

val Http4sVersion  = "0.23.6"
val CirceVersion   = "0.14.1"
val ZioVersion     = "1.0.13"
val ZioCatzVersion = "3.2.9.0"
val CatsVersion    = "2.7.0"
libraryDependencies ++= Seq(
  "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s"      %% "http4s-circe"        % Http4sVersion,
  "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
  "io.circe"        %% "circe-generic"       % CirceVersion,
  "dev.zio"         %% "zio"                 % ZioVersion,
  "dev.zio"         %% "zio-test"            % ZioVersion,
  "dev.zio"         %% "zio-interop-cats"    % ZioCatzVersion,
  "org.typelevel"   %% "cats-core"           % CatsVersion
)

lazy val root = (project in file("."))
  .settings(
    name := "http4sPlusZIO",
   // scalacOptions += "-Ypartial-unification"
)
