package demo.run

import java.util.ServiceLoader
import org.osgi.framework.launch.FrameworkFactory
import java.util.HashMap

/**
 * Run the OSGi framework depends on osgi-bundle task.
 * The scala based bundle must be build before.
 */
object Run extends App {
  println("Start OSGi Session...")
  
  val locator = ServiceLoader.load(classOf[FrameworkFactory]);
  val frameworkFactory = locator.iterator().next();
  val props = new HashMap[String, String]
  props.put("org.osgi.framework.storage.clean", "onFirstInit")
  props.put("felix.fileinstall.dir", "target/scala-2.9.2/")
  props.put("felix.fileinstall.filter", ".*\\.jar")
  props.put("felix.fileinstall.poll", "100")
  val framework = frameworkFactory.newFramework(props);
  framework.start();
  val context = framework.getBundleContext();
  val mvnProtocolBundle = context.installBundle(
      "http://repo1.maven.org/maven2/org/ops4j/pax/url/pax-url-mvn/1.3.5/pax-url-mvn-1.3.5.jar")
  mvnProtocolBundle.start();
  def mvn(url : String) = context.installBundle(String.format("mvn:%s", url))
  val bundles = List(
    mvn("org.apache.felix/org.apache.felix.gogo.runtime/0.8.0"),
	mvn("org.apache.felix/org.apache.felix.gogo.shell/0.8.0"),
	mvn("org.apache.felix/org.apache.felix.gogo.command/0.8.0"),
	mvn("org.apache.felix/org.apache.felix.fileinstall/3.2.6"),
	mvn("org.apache.felix/org.apache.felix.scr/1.6.2")
  )
  bundles.foreach(bundle => bundle.start())
  framework.waitForStop(0) 
  
  println("End OSGi Session...")
}