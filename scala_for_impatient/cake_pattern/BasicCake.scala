package cake_pattern

object BasicCake {

  trait Fooable {

    def foo(): String

  }

  trait MyFooable extends Fooable {
    def foo() = "---FOO---"
  }

  trait Tarable {

    def tar(): String

  }

  trait HisTarable extends Tarable {
    def tar() = "####TAR###"

  }

  class BarAndTarableAndFooable {

    self: Fooable with Tarable =>
    def bar() = "****BAR***" + tar() + foo()

  }
  
  // 1 more solution using constructor arguments
  class BarAndTarableAndFooable2(f: Fooable, t: Tarable){
    def bar() = "****BAR***" + t.tar() + f.foo()
  }

  def main(args: Array[String]): Unit = {
    val khichdi = new BarAndTarableAndFooable with MyFooable with HisTarable
    println(khichdi.bar())
   
  }

}