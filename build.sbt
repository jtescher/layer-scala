name := """layer-scala"""

version := "0.0.1"

scalaVersion := "2.11.7"

val akkaVersion = "2.4.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % "2.2.4" % Test
)

// Test coverage configuration
coverageMinimum := 100
coverageFailOnMinimum := true
