package cake_pattern

object WhyCakePattern {

  trait Fooable {

    def foo() = "---FOO---"

  }

  trait Tarable {

    def tar() = "####TAR###"

  }

  // BarAndFooableAndTarable has to be a class and not just another trait
  class BarAndTarableAndFooable extends Tarable with Fooable {

    def bar() = "****BAR***" + tar() + foo()

  }

  def main(args: Array[String]): Unit = {
    val khichdi = new BarAndTarableAndFooable
    println(khichdi.bar())
  }

}