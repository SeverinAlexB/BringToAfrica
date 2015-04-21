resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += Classpaths.sbtPluginReleases

// coverage plugins
addSbtPlugin("de.johoop" % "jacoco4sbt" % "2.1.4")

// static analysis
addSbtPlugin("de.johoop" % "cpd4sbt" % "1.1.5")

addSbtPlugin("de.johoop" % "findbugs4sbt" % "1.4.0")

addSbtPlugin("com.etsy" % "sbt-checkstyle-plugin" % "0.4.1")

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.8")
