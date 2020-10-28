package higher_kinded_types

object AbstractionOverConcept {
  class Parameterized[T] { // type as a parameter
    def call(func: (Int) => Int) = func(1) // function as a parameter
    def use(l: Long) { println(l) } // value as a parameter
  }

  val p = new Parameterized[String] // pass type String as a parameter
  p.call((i: Int) => i + 1) // pass function increment as a parameter
  p.use(1L) // pass value 1L as a parameter

  abstract class Abstracted {
    type T // abstract over a type
    def call(i: Int): Int // abstract over a function
    val l: Long // abstract over value
    def use() { println(l) }
  }

  class Concrete extends Abstracted {
    type T = String // specialize type as String
    def call(i: Int): Int = i + 1 // specialize function as increment function
    val l = 1L // specialize value as 1L
  }

  val a: Abstracted = new Concrete
  a.call(1)
  a.use()
}