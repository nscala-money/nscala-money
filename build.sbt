import Dependencies._

import Common._
import CrossVersions._
import Release._

lazy val nscalaMoney = (
  MoneyProject("nscalaMoney", file("."))
  settings(releaseSettings: _*)
  settings(
    packagedArtifacts          := Map.empty, // don't publish the default aggregate root project
    initialCommands in console += "import com.github.nscala_money.money.json.PlayImports._\n"
  )
  enablePlugins(CrossPerProjectPlugin)
  aggregate(core, playJson)
  dependsOn(core, playJson)
)

lazy val core = (
  MoneyProject("core")
  settings(
    name                                   := "nscala-money",
    crossScalaVersions                     += "2.12.2",
    libraryDependencies                   ++= coreDependencies,
    unmanagedSourceDirectories in Compile ++= coreUnmanagedDirs(scalaVersion.value,
                                                               (sourceDirectory in Compile).value)
  )
)

lazy val playJson = (
  MoneyProject("play-json")
  settings(
    name                        := "nscala-money-play-json",
    crossScalaVersions          += "2.12.2",
    libraryDependencies        ++= playJsonDependencies,
    initialCommands in console  += "import com.github.nscala_money.money.json.PlayImports._\n"
  )
  dependsOn(core)
)


