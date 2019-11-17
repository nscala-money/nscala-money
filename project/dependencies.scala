import sbt._
import Keys._

object Dependencies {
  // Versions
  object V {
    val jodaMoney   = "1.0.1"
    val jodaConvert = "2.2.1"
    val play        = "2.6.2"
    val scalacheck  = "1.11.6"
  }

  // Libraries
  val jodaMoney   = "org.joda"          %  "joda-money"   % V.jodaMoney
  val jodaConvert = "org.joda"          %  "joda-convert" % V.jodaConvert
  val playJson    = "com.typesafe.play" %% "play-json"    % V.play        % "provided"
  val scalacheck  = "org.scalacheck"    %% "scalacheck"   % V.scalacheck  % "test"

  // Projects
  val commonDependencies   = Seq(scalacheck)
  val coreDependencies     = Seq(jodaMoney, jodaConvert)
  val playJsonDependencies = Seq(playJson)
}
