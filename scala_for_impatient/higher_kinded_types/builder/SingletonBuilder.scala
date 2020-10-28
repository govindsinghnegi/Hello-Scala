package higher_kinded_types.builder

object SingletonBuilder {

  trait Builder[A, C[A]] {
    def add(elem: A): Unit
    def build(): C[A]
  }

  class ListBuilder[T] extends Builder[T, List] {
    private var myList: List[T] = List.empty
    def add(elem: T): Unit = myList ::= elem
    def build(): List[T] = myList
  }

  trait BuilderFactory[C[_]] {
    def create[A]: Builder[A, C]
  }

  case class Singleton[T](elem: T) {
    def map[X, C[_]](f: T => X)(implicit bf: BuilderFactory[C]): C[X] = {
      val builder = bf.create[X]
      builder.add(f(elem))
      builder.build()
    }
  }

  class SingletonBuilder[A] extends Builder[A, Singleton] {
    private var mySingl: Singleton[A] = null
    def add(elem: A): Unit = if (mySingl != null) throw new IllegalStateException else mySingl = Singleton(elem)
    def build(): Singleton[A] = mySingl
  }

  trait LowPriorityImports {
    implicit val listBF = new BuilderFactory[List] {
      def create[A] = new ListBuilder
    }
  }

  object HighPriorityImports extends LowPriorityImports {
    implicit val singletonBF = new BuilderFactory[Singleton] {
      def create[A]: Builder[A, Singleton] = new SingletonBuilder
    }
  }

  def main(args: Array[String]): Unit = {
    // w/o this import, _.toString gives compilation issue
    import HighPriorityImports._
    println(Singleton(2.53).map[String, List](_.toString))  // why String and not an Int ??
    println(Singleton(100).map(_.toString))
  }

}