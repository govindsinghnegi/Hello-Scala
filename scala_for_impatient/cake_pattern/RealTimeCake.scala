package cake_pattern

object RealTimeCake {

  trait FooComponent {
    val fooable: Fooable
    class Fooable {
      def foo() = "---FOO---"
    }
  }

  trait TarComponent {
    val tarable: Tarable
    class Tarable {
      def tar() = "####TAR###"
    }
  }

  class BarAndTarableAndFooable {
    self: FooComponent with TarComponent =>
    def bar() = "****BAR***" + tarable.tar() + fooable.foo()

  }

  def main(args: Array[String]): Unit = {
    val khichdi = new BarAndTarableAndFooable with FooComponent with TarComponent {
      val fooable: Fooable = new Fooable // or any other impl
      val tarable: Tarable = new Tarable // or any other impl
    }
    println(khichdi.bar())

  }

}