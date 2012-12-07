organization := "demo.sbt.osgi"

name := "hello"

version := "1.0.0"

unmanagedBase <<= baseDirectory ( base => base / "libs" )

libraryDependencies ++= Seq(
	"org.osgi" % "org.osgi.core" % "4.3.0" % "provided"
)

osgiSettings

OsgiKeys.exportPackage := Seq("demo.api")

OsgiKeys.privatePackage := Seq("demo.internal")

OsgiKeys.importPackage := Seq(
	"sun.misc;resolution:=optional",
	"!aQute.bnd.annotation.*", 
	"*"
)

OsgiKeys.bundleActivator := Option("demo.internal.Activator")

OsgiKeys.additionalHeaders := Map(
	"Service-Component" -> "*",
	"Conditional-Package" -> "scala.*"
)