import de.johoop.cpd4sbt.CopyPasteDetector._
import de.johoop.cpd4sbt.Language._
import de.johoop.cpd4sbt.ReportType._
import de.johoop.findbugs4sbt.FindBugs._
import de.johoop.findbugs4sbt.ReportType._
import com.etsy.sbt.Checkstyle._
import de.johoop.jacoco4sbt._
import JacocoPlugin._

name := """BringToAfrica"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

jacoco.settings

parallelExecution      in jacoco.Config := false

jacoco.outputDirectory in jacoco.Config := file("target/jacoco")

jacoco.reportFormats   in jacoco.Config := Seq(XMLReport("utf-8"), HTMLReport("utf-8"))

jacoco.excludes        in jacoco.Config := Seq("views*", "*Routes*", "controllers*routes*", "controllers*Reverse*", "controllers*javascript*", "controller*ref*")

cpdSettings

cpdLanguage := Java

cpdReportType := Simple

cpdMinimumTokens := 30

cpdSkipDuplicateFiles := true

findbugsSettings

findbugsReportType := Some(FancyHtml)

findbugsReportPath := Some(crossTarget.value / "findbugs" / "report.html")

checkstyleSettings

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean
)

libraryDependencies += "postgresql" % "postgresql" % "9.1-901-1.jdbc4"

libraryDependencies += "org.mindrot" % "jbcrypt" % "0.3m"
