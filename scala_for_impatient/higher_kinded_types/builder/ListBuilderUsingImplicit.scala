package higher_kinded_types.builder

object ListBuilderUsingImplicit {

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

  implicit val listBF = new BuilderFactory[List] {
    def create[A] = new ListBuilder
  }

  case class Singleton[T](elem: T) {
    def map[X, C[_]](f: T => X)(implicit bf: BuilderFactory[C]): C[X] = {
      val builder = bf.create[X]
      builder.add(f(elem))
      builder.build()
    }
  }

  def main(args: Array[String]): Unit = {
    println(Singleton(1.34).map(_.toString))
  }

}