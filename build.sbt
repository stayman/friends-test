organization := "com.friends"
name := "friends-test"
version := "0.0.1-SNAPSHOT"
scalaVersion := "2.12.4"

val Http4sVersion = "0.17.6"
val Specs2Version = "4.0.2"
val LogbackVersion = "1.2.3"

libraryDependencies ++= Seq(
  "org.http4s"     %% "http4s-blaze-server"  % Http4sVersion,
  "org.http4s"     %% "http4s-circe"         % Http4sVersion,
  "org.http4s"     %% "http4s-dsl"           % Http4sVersion,
  "org.specs2"     %% "specs2-core"          % Specs2Version % "test",
  "ch.qos.logback" %  "logback-classic"      % LogbackVersion
)

