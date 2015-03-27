import de.johoop.cpd4sbt.CopyPasteDetector._
import com.etsy.sbt.Checkstyle._

name := """BringToAfrica"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

cpdSettings

checkstyleSettings

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean
)

libraryDependencies += "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
