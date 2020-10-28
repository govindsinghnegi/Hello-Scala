package higher_kinded_types.builder

object ListBuilderSample {

  trait Builder[A, C[A]] {
    def add(elem: A): Unit
    def build(): C[A]
  }

  class ListBuilder[T] extends Builder[T, List] {
    private var myList: List[T] = List.empty
    def add(elem: T): Unit = myList ::= elem
    def build(): List[T] = myList
  }

  case class Singleton[T](elem1: T, elem2: T) {
    def map[X, C[X]](f: T => X)(builder: Builder[X, C]): C[X] = {
      builder.add(f(elem1))
      builder.add(f(elem2))
      builder.build()
    }
  }

  case class Student[T](name: T, id: T) {
    def map[X, C[X]](f: T => X)(builder: Builder[X, C]): C[X] = {
      // to make it to accept both the parameters ? 
      builder.add(f(id))
      builder.build()
    }
  }

  def main(args: Array[String]): Unit = {

    // def map(elem: Int): List[Int] = List[elem] -------> cannot directly do Int -> List(Int), need a builder

    println(Singleton(1.34, 4.11).map(_.toString)(new ListBuilder))

    println(Student("sushrut", "31").map(_.toString())(new ListBuilder))

  }

}