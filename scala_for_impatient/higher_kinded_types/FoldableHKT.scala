package higher_kinded_types

object FoldableHKT {

  trait Foldable[T[_]] {
    def foldLft[A, B](as: T[A], z: B, f: (B, A) => B): B
  }

  class ListFoldable extends Foldable[List] {
    def foldLft[A, B](as: List[A], z: B, f: (B, A) => B): B = as.foldLeft(z)(f)
  }

  def main(args: Array[String]): Unit = {
    val sumOf3 = 1 + 2 + 3
    println("using simple arith : " + sumOf3)
    val sumOf3UsingList = List(1, 2, 3).foldLeft(0)(_ + _)
    println("using list abstraction : " + sumOf3UsingList)

    def sumOf[T[_]](ns: T[Int])(implicit ff: Foldable[T]) = ff.foldLft(ns, 0, (x: Int, y: Int) => x + y)
    implicit val listFoldable = new ListFoldable
    println("using functor abstraction : " + sumOf(List(1, 2, 3)))
  }
}