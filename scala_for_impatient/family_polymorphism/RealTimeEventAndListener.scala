package family_polymorphism

import scala.collection.mutable.ArrayBuffer

object RealTimeEventAndListener {

  trait ListenerSupport {

    type S <: Source
    type E <: Event
    type L <: Listener

    trait Event {
      var source: S = _
    }

    trait Listener {
      def occured(event: E): Unit
    }

    trait Source {
      this: S =>
      private val listeners = new ArrayBuffer[L]
      def add(l: L) = listeners += l
      def remove(l: L) = listeners -= l
      def fire(e: E) = {
        e.source = this
        for (l <- listeners) l.occured(e)
      }
    }
  }

  class ButtonListenerSupport extends ListenerSupport {

    type S = Button
    type E = ButtonEvent
    type L = ButtonListener

    class ButtonEvent extends Event

    class ButtonListener extends Listener {
      def occured(event: ButtonEvent) = println("event object is : " + event)
    }

    class Button extends Source {
      def click() {
        fire(new ButtonEvent)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val support = new ButtonListenerSupport
    val listener = new support.ButtonListener
    val button = new support.Button
    button.add(listener)
    button.click()
  }

}