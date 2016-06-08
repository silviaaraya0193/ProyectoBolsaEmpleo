name := """prueba1"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
lazy val  myProject= (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"
routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
    "org.mindrot" % "jbcrypt" % "0.3m",
"com.typesafe.play" % "play-java_2.11" % "2.5.3",
        "it.innove" % "play2-pdf" % "1.5.1"

)
resolvers ++= Seq(
  "Maven Central" at "http://http://repo1.maven.org/maven2/"
)