import sbt._
import Keys._

import de.heikoseeberger.sbtheader.AutomateHeaderPlugin
import de.heikoseeberger.sbtheader.HeaderPlugin.autoImport._
import de.heikoseeberger.sbtheader.license.Apache2_0

import Dependencies.commonDependencies

object Common {
  val commonSettings: Seq[Def.Setting[_]] = Seq(
    scalaVersion := "2.11.8",
    crossScalaVersions := Seq("2.11.8"),

    initialCommands in console += "import com.github.nscala_money.money.Imports._\n",

    scalacOptions ++= scalacOptionsVal(scalaVersion.value),
    scalacOptions in (Compile, doc)    ++= docScalacOptionsVal,
    // -Ywarn-unused-imports breaks the console, so remove it
    scalacOptions in (Compile, console) := (scalacOptions in (Compile, console)).value filterNot {
      _ == "-Ywarn-unused-import"
    },
    scalacOptions in (Test, console)    := (scalacOptions in (Compile, console)).value,

    // release options
    organization    := "com.github.nscala-money",
    pomExtra        := pomExtraVal,
    pomPostProcess  := pomPostProcessVal,
    credentials    ++= credentialsVal,

    // keep headers updated
    headers         := headersVal,

    // dependencies
    libraryDependencies ++= commonDependencies
  )

  def MoneyProject(name: String): Project = MoneyProject(name, file(name))

  def MoneyProject(name: String, file: File): Project = (
    Project(name, file)
    settings(commonSettings:_*)
    enablePlugins(AutomateHeaderPlugin)
  )

  def minorVersion(version: String): Int = CrossVersion.partialVersion(version).get._2

  def scalacOptionsVal(version: String): Seq[String] =
    Seq(
      9  -> Seq("-unchecked", "-deprecation"),
      10 -> Seq("-feature", "-language:implicitConversions", "-language:higherKinds"),
      11 -> Seq("-Ywarn-unused", "-Ywarn-unused-import")
    ).collect{
      case (m, opts) if minorVersion(version) >= m => opts
    }.flatten

  val docScalacOptionsVal = {
    val gitHash = Process("git rev-parse HEAD").lines.head
    Seq(
      "-doc-source-url", s"https://github.com/nscala-money/nscala-money/tree/${gitHash}€{FILE_PATH}.scala"
    )
  }

  val pomExtraVal: xml.NodeBuffer = (
    <url>https://github.com/nscala-money/nscala-money</url>
      <licenses>
        <license>
          <name>Apache</name>
          <url>http://www.opensource.org/licenses/Apache-2.0</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
    <scm>
      <url>git@github.com:nscala-money/nscala-money.git</url>
      <connection>scm:git:git@github.com:nscala-money/nscala-money.git</connection>
    </scm>
    <developers>
      <developer>
        <id>drbild</id>
        <name>David R. Bild</name>
        <url>https://github.com/drbild</url>
      </developer>
    </developers>
  )

  /* strip test deps from pom */
  import scala.xml._
  import scala.xml.transform._
  lazy val pomPostProcessVal = { node: Node =>
    def stripIf(f: Node => Boolean) = new RewriteRule {
      override def transform(n: Node) = if (f(n)) NodeSeq.Empty else n
    }
    val stripTestScope = stripIf(n => n.label == "dependency" && (n \ "scope").text == "test")
    new RuleTransformer(stripTestScope).transform(node)(0)
  }

  val credentialsVal: Seq[Credentials] = {
    val realm    = "Sonatype Nexus Repository Manager"
    val host     = "oss.sonatype.org"
    val cred = for {
      username <- scala.util.Try(sys.env("NEXUS_USERNAME")).toOption
      password <- scala.util.Try(sys.env("NEXUS_PASSWORD")).toOption
    } yield Credentials(realm, host, username, password)
    cred.toList
  }

  val headersVal = Map(
    "scala" -> Apache2_0("2015-2016", "David R. Bild", "*")
  )

}
