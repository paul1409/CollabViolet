name := """cloud"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)
checkstyleConfigLocation := CheckstyleConfigLocation.URL("http://horstmann.com/sjsu/fall2016/cs151/hw5/checkstyle.xml")
(checkstyle in Compile) <<= (checkstyle in Compile) triggeredBy (compile in Compile)

fork in run := true