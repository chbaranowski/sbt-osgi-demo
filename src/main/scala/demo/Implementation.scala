package demo.internal

import org.osgi.framework._
import aQute.bnd.annotation.component._
import demo.api.Printer
import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Activate
import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Deactivate
import aQute.bnd.annotation.component.Reference

class SysoutPrinter extends Printer {

  def print(text : String) = println(text)

}

// Bundle Activator Demo class
class Activator extends BundleActivator {

  val printer = new SysoutPrinter()

  override def start(context: BundleContext) {
    printer.print("Start OSGi Bundle")
  }

  override def stop(context: BundleContext) {
    printer.print("Stop OSGi Bundle")
  }

}

// DS Annotation Demo
@Component
class DeclarativeServicePrinter extends Printer {
	
	val printer = new SysoutPrinter()
  
    def print(text : String) = printer.print(text)
  
}

@Component
class Client {
  
  var printer : Printer = null
  
  @Reference
  def setPrinter(printer : Printer) = this.printer = printer
  
  def unsetPrinter(printer : Printer) = this.printer = null
  
  @Activate
  def start = printer.print("Start DS Client Component")
  
  @Deactivate
  def stop = printer.print("Stop DS Client Component")
  
}