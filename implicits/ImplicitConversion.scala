package implicits

object ImplicitConversion {

  def main(args: Array[String]): Unit = {

    // def smaller[T](a: T, b: T) = if (a > b) b else a // does not work, as T is unknown
    def smaller[T](a: T, b: T)(implicit order: T => Ordered[T]) = if (order(a) > b) b else a

    println(smaller(6, 4))
    println(smaller("aab", "bab"))
  }
}