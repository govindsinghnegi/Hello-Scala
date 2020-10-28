package higher_kinded_types

object BoxMapping {

  trait Functor[T[_]] {
    def map[A, B](a: T[A])(f: A => B): T[B]
  }

  import java.util.{ List => JList }
  implicit object JavaListConverter extends Functor[JList] {
    import scala.collection.JavaConverters._
    def map[A, B](a: JList[A])(f: A => B): JList[B] =
      (for (a <- a.asScala) yield f(a)).asJava
  }

  case class Box[A](a1: A, a2: A)

  implicit object BoxConverter extends Functor[Box] {
    def map[A, B](a: Box[A])(f: A => B): Box[B] = Box(f(a.a1), f(a.a2))
  }

  def describeHashes[A, T[_]: Functor](a: T[A]) = implicitly[Functor[T]].map(a)(a => a.hashCode())

  def describeToStrings[A, T[_]: Functor](a: T[A]) = implicitly[Functor[T]].map(a)(a => a.toString())

  case class Holder(i: Int)

  def main(args: Array[String]): Unit = {

    println("Holder(1).toString : " + Holder(1))
    println("Holder(1).hashCode : " + Holder(1).hashCode())

    val jList: JList[Holder] = {
      val l = new java.util.ArrayList[Holder]()
      l add Holder(1); l add Holder(2); l add Holder(3)
      l
    }

    val list = describeHashes(jList)
    println(list)

    val myBox = Box[Holder](Holder(4), Holder(5))
    val box2 = describeToStrings(myBox)
    println(box2)
  }
}