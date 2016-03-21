import com.typesafe.sbt.site.util.SiteHelpers
import com.typesafe.sbt.SbtGhPages.GhPagesKeys._
import sbtunidoc.Plugin.UnidocKeys._

// **************************************
// Tutorial: edit the following variables

val USER = "denisrosset"
val REPO = "fizz"
val NAME = "fizz"
val ORG = "org.fizzorg"

val APIDIR = "latest/api"
val TUTDIR = "_tut"

// derived info
lazy val GITURL = s"git@github.com:$USER/$REPO.git"
lazy val SCMINFO = ScmInfo(url(s"https://github.com/$USER/$REPO"), s"scm:git:$GITURL")
lazy val APIURL = s"http://$USER.github.io/$REPO/$APIDIR"

// **************************************

// custom keys used by sbt-site
lazy val tutorialSubDirName = settingKey[String]("Website tutorial directory")
lazy val apiSubDirName = settingKey[String]("Unidoc API directory")

// root project

lazy val fizz = project.in(file("."))
  .settings(moduleName := "fizz")
  .settings(commonSettings)
  .settings(noPublishSettings) // the root project is not published,
  .aggregate(core, docs)       // aggregates the two projects
  .dependsOn(core)             // but there is no code in the `docs` project to depend upon

// docs project
// Customize: enable/disable tut, unidoc

lazy val docs = (project in file("docs"))
  .settings(moduleName := "fizz-docs")
  .settings(commonSettings)
  .settings(noPublishSettings)   // the docs module is not published
  .settings(tutConfig)           // enable tut
  .settings(unidocConfig)        // enable unidoc
  .settings(siteConfig)          // parameters for sbt-site and sbt-ghpages
  .dependsOn(core)

lazy val core = project.in(file("core"))
  .settings(moduleName := "fizz-core")
  .settings(commonSettings:_*)

lazy val commonSettings = Seq(
  name := NAME,
  organization := ORG,
  scalaVersion := "2.11.8",
  scmInfo := Some(SCMINFO),
  apiURL := Some(url(APIURL)), // enable external projects to link to our Scaladoc
  scalacOptions in (Compile, doc) := (scalacOptions in (Compile, doc)).value.filter(_ != "-Xfatal-warnings")
) ++ doctestConfig             // enable doctest

// General configuration of sbt-site and sbt-ghpages

lazy val siteConfig = ghpages.settings ++ Seq(
  siteMappings ++= Seq(
    file("CONTRIBUTING.md") -> "contributing.md",
    file("LICENSE.md") -> "license.md"
  ),
  ghpagesNoJekyll := false,
  git.remoteRepo := GITURL,
  includeFilter in makeSite := "*.html" | "*.css" | "*.png" | "*.jpg" | "*.gif" | "*.js" | "*.swf" | "*.yml" | "*.md"
)

// Disable publishing

lazy val noPublishSettings = Seq(
  publish := (),
  publishLocal := (),
  publishArtifact := false
)

// *******************************************************************
// To use Doctest, append the settings below to all concerned projects

// Customize: the Scalatest version

lazy val doctestConfig = doctestSettings ++ Seq(
  doctestTestFramework := DoctestTestFramework.ScalaTest, // opinion: we default to Scalatest
  // the following two lines specify an explicit Scalatest version and tell sbt-doctest to
  // avoid importing new dependencies
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.0-M7" % "test",
    "org.scalacheck" %% "scalacheck" % "1.12.5" % "test"
  ),
  doctestWithDependencies := false
)

// **************************************************************
// To use Unidoc, append the settings below to the `docs` project

// Customize: the `inProjects(...)` list
// Customize: remove hierarchy diagram generation if Graphviz is not installed

lazy val unidocConfig = unidocSettings ++ Seq(
  apiSubDirName := APIDIR,
  // sbt-site will use the generated documentation
  addMappingsToSiteDir(mappings in (ScalaUnidoc, packageDoc), apiSubDirName),
  // projects to include
  unidocProjectFilter in (ScalaUnidoc, unidoc) := inProjects(core),
  // enable automatic linking to the external Scaladoc of our own managed dependencies
  autoAPIMappings := true,
  scalacOptions in (ScalaUnidoc, unidoc) ++= Seq(
    // we want warnings to be fatal (on broken links for example)
    "-Xfatal-warnings", 
    // link to source code, yes that's an euro symbol
    "-doc-source-url", scmInfo.value.get.browseUrl + "/tree/master€{FILE_PATH}.scala",
    "-sourcepath", baseDirectory.in(LocalRootProject).value.getAbsolutePath,
    // generate type hierarchy diagrams, runs graphviz
    "-diagrams"
  )
)


// ***********************************************************
// To use Tut, append the settings below to the `docs` project

lazy val tutConfig = tutSettings ++ Seq(
  tutorialSubDirName := TUTDIR,
  addMappingsToSiteDir(tut, tutorialSubDirName),
  tutScalacOptions ~= (_.filterNot(Set("-Ywarn-unused-import", "-Ywarn-dead-code")))
)
