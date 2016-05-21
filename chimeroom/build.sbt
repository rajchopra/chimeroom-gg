name := "chimeroom"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.3"

// http://mvnrepository.com/artifact/com.google.code.gson/gson
libraryDependencies += "com.google.code.gson" % "gson" % "1.7.1"

play.Project.playJavaSettings
