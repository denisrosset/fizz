//import com.typesafe.sbt.SbtSite.SiteKeys._
import com.typesafe.sbt.site.util.SiteHelpers
import com.typesafe.sbt.SbtGhPages.GhPagesKeys._
//import sbtunidoc.Plugin.UnidocKeys._

// **************************************
// Tutorial: edit the following variables

val REPO = "git@github.com:denisrosset/fizzdoc.git"
val NAME = "fizzdoc"
val ORG = "org.fizzorg"

// **************************************

lazy val tutorialSubDirName = settingKey[String]("Website tutorial directory")

lazy val buildSettings = Seq(
  name := "fizzdoc",
  organization := ORG,
  scalaVersion := "2.11.8"
)

//lazy val fizzdocDoctestSettings = Seq(
//  doctestWithDependencies := false
//) ++ doctestSettings

lazy val commonSettings = Seq(
  scalacOptions ++= commonScalacOptions,
  scalacOptions in (Compile, doc) := (scalacOptions in (Compile, doc)).value.filter(_ != "-Xfatal-warnings")
) ++ warnUnusedImport

lazy val commonJvmSettings = Seq(
  testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oDF")
// currently sbt-doctest doesn't work in JS builds, so this has to go in the
// JVM settings. https://github.com/tkawachi/sbt-doctest/issues/52
) //++ fizzdocDoctestSettings

lazy val fizzdocSettings = buildSettings ++ commonSettings ++ commonJvmSettings

lazy val docSettings = Seq(
  autoAPIMappings := true,
  //  site.addMappingsToSiteDir(mappings in (ScalaUnidoc, packageDoc), "api"),
  tutorialSubDirName := "_tut",
  addMappingsToSiteDir(tut, tutorialSubDirName),
  ghpagesNoJekyll := false,
  siteMappings += file("CONTRIBUTING.md") -> "contributing.md",
/*  scalacOptions in (ScalaUnidoc, unidoc) ++= Seq(
    "-Xfatal-warnings",
    "-doc-source-url", scmInfo.value.get.browseUrl + "/tree/master€{FILE_PATH}.scala",
    "-sourcepath", baseDirectory.in(LocalRootProject).value.getAbsolutePath,
    "-diagrams"
  ),*/
  git.remoteRepo := REPO,
  includeFilter in makeSite := "*.html" | "*.css" | "*.png" | "*.jpg" | "*.gif" | "*.js" | "*.swf" | "*.yml" | "*.md"
)

lazy val docs = (project in file("docs"))
  .settings(moduleName := "fizzdoc-docs")
  .settings(fizzdocSettings)
  .settings(noPublishSettings)
//  .settings(unidocSettings)
  .settings(ghpages.settings)
  .settings(docSettings)
  .settings(tutSettings)
  .settings(tutScalacOptions ~= (_.filterNot(Set("-Ywarn-unused-import", "-Ywarn-dead-code"))))
  .settings(commonJvmSettings)
  .dependsOn(core)

lazy val fizzdoc = project.in(file("."))
  .settings(moduleName := "fizzdoc")
  .settings(fizzdocSettings)
  .settings(noPublishSettings)
  .aggregate(core, docs)
  .dependsOn(core)

lazy val core = project.in(file("core"))
  .settings(moduleName := "fizzdoc-core")
  .settings(fizzdocSettings:_*)
  .settings(commonJvmSettings:_*)

////////////////////////////////////////////////////////////////////////////////////////////////////
// Base Build Settings - Should not need to edit below this line. 

lazy val noPublishSettings = Seq(
  publish := (),
  publishLocal := (),
  publishArtifact := false
)

lazy val commonScalacOptions = Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:experimental.macros",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yinline-warnings",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture"
)

lazy val warnUnusedImport = Seq(
  scalacOptions ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 10)) =>
        Seq()
      case Some((2, n)) if n >= 11 =>
        Seq("-Ywarn-unused-import")
    }
  },
  scalacOptions in (Compile, console) ~= {_.filterNot("-Ywarn-unused-import" == _)},
  scalacOptions in (Test, console) <<= (scalacOptions in (Compile, console))
)
