name := "chimeroom"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     

libraryDependencies += "mysql" % "mysql-connector-java" % "5.5.49"

play.Project.playJavaSettings
