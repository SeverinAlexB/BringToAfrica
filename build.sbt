import de.johoop.cpd4sbt.CopyPasteDetector._
import com.etsy.sbt.Checkstyle._

name := """play-java-intro"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

cpdSettings

checkstyleSettings

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean
)
