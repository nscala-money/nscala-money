import sbt._
import Keys._

object CrossVersions {
  def coreUnmanagedDirs(version: String, sourceDir: File): Seq[File] = 
    minorVersion(version) match {
      case m if m <= 9  => Seq(sourceDir / "scala29")
      case m if m >= 10 => Seq(sourceDir / "scala210")
    }

  def minorVersion(version: String): Int = CrossVersion.partialVersion(version).get._2
}
