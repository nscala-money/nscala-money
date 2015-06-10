organization := "com.github.nscala-money"

sonatypeProfileName := "com.github.nscala-money"

name := "nscala-money"

publishMavenStyle := true

crossScalaVersions := Seq("2.9.3", "2.10.5", "2.11.6", "2.12.0-M1")

val unusedWarnings = "-Ywarn-unused" :: "-Ywarn-unused-import" :: Nil

scalacOptions <++= scalaVersion map { v =>
  if (v.startsWith("2.9"))
    Seq("-unchecked", "-deprecation")
  else
    Seq("-unchecked", "-deprecation", "-feature", "-language:implicitConversions", "-language:higherKinds")
}

scalacOptions ++= PartialFunction.condOpt(CrossVersion.partialVersion(scalaVersion.value)){
  case Some((2, scalaMajor)) if scalaMajor >= 11 => unusedWarnings
}.toList.flatten

Seq(Compile, Test).flatMap(c =>
  scalacOptions in (c, console) ~= {_.filterNot(unusedWarnings.toSet)}
)

def gitHashOrBranch: String = scala.util.Try(
  sys.process.Process("git rev-parse HEAD").lines_!.head
).getOrElse("master")

scalacOptions in (Compile, doc) ++= {
  Seq(
    "-sourcepath", baseDirectory.value.getAbsolutePath,
    "-doc-source-url", s"https://github.com/nscala-money/nscala-money/tree/${gitHashOrBranch}€{FILE_PATH}.scala"
  )
}

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.11.6" % "test",
  "org.joda" % "joda-money" % "0.10.0",
  "org.joda" % "joda-convert" % "1.2"
)

pomPostProcess := { node =>
  import scala.xml._
  import scala.xml.transform._
  def stripIf(f: Node => Boolean) = new RewriteRule {
    override def transform(n: Node) =
      if (f(n)) NodeSeq.Empty else n
  }
  val stripTestScope = stripIf { n => n.label == "dependency" && (n \ "scope").text == "test" }
  new RuleTransformer(stripTestScope).transform(node)(0)
}

unmanagedSourceDirectories in Compile <+= (scalaVersion, sourceDirectory in Compile){(v, dir) =>
  if(v.startsWith("2.9"))
    dir / "scala29"
  else
    dir / "scala210"
}

initialCommands in console += {
  Iterator("org.joda.money._", "com.github.nscala_money.money.Imports._").map("import "+).mkString("\n")
}

pomExtra := (
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

credentials ++= {
  val realm    = "Sonatype Nexus Repository Manager"
  val host     = "oss.sonatype.org"
  val cred = for {
    username <- scala.util.Try(sys.env("NEXUS_USERNAME")).toOption
    password <- scala.util.Try(sys.env("NEXUS_PASSWORD")).toOption
  } yield Credentials(realm, host, username, password)
  cred.toList
}
