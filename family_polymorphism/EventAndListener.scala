package family_polymorphism

import scala.collection.mutable.ArrayBuffer

object EventAndListener {

  trait Event[S] {
    var source: S = _
  }

  trait Listener[S, E <: Event[S]] {
    def occured(event: E): Unit
  }

  trait Source[S, E <: Event[S], L <: Listener[S, E]] {
    this: S =>
    private val listeners = new ArrayBuffer[L]
    def add(l: L) = listeners += l
    def remove(l: L) = listeners -= l
    def fire(e: E) = {
      e.source = this
      for (l <- listeners) l.occured(e)
    }
  }

  class ButtonEvent extends Event[Button]

  class ButtonListener extends Listener[Button, ButtonEvent] {
    def occured(event: ButtonEvent) = println("event object is : " +event)
  }

  class Button extends Source[Button, ButtonEvent, ButtonListener] {
    def click() {
      fire(new ButtonEvent)
    }
  }

  def main(args: Array[String]): Unit = {
    val myListener = new ButtonListener
    val myButton = new Button
    myButton.add(myListener)
    myButton.click()
  }
}