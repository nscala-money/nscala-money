import sbt._
import Keys._

import sbtrelease.ReleasePlugin.autoImport._
import sbtrelease.ReleaseStateTransformations._
import com.typesafe.sbt.pgp.PgpKeys
import PgpKeys._
import UpdateReadmePlugin.autoImport._

object Release {
  val releaseSettings: Seq[Def.Setting[_]] = Seq(
    // Adds updateReadme, commitReadme, sbt-doge crossbuild, and
    // sonatypeReleaseAll to defaults
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runClean,
      releaseStepCommandAndRemaining("+test"),
      setReleaseVersion,
      runUpdateReadme,
      commitReleaseVersion,
      releaseStepTask(commitReadme),
      tagRelease,
      releaseStepCommandAndRemaining("+publishSigned"),
      setNextVersion,
      commitNextVersion,
      releaseStepCommand("sonatypeReleaseAll"),
      pushChanges),

    releaseCrossBuild := false,
    releasePublishArtifactsAction := publishSigned.value,
    publishMavenStyle := true,

    releaseTagName       := s"releases/${(version in ThisBuild).value}",
    releaseTagComment    := s"release: ${(version in ThisBuild).value}",
    releaseCommitMessage := s"version: bump to ${(version in ThisBuild).value}"
  )

  lazy val runUpdateReadme: ReleaseStep = ReleaseStep(
    action = { st: State =>
      val extracted = Project.extract(st)
      val ref = extracted.get(thisProjectRef)
      extracted.runAggregated(updateReadme in Global in ref, st)
    }
  )

}
