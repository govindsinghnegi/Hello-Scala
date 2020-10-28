package higher_kinded_types

import scala.collection.mutable._

object FirstKindedTypes {

  trait Converters[A] {
    def map[B](x: A)(f: A => B): B
  }

  object StrConverters extends Converters[String] {
    def map[B](elem: String)(f: String => B): B = f(elem)
  }

  object IntConverters extends Converters[Int] {
    def map[B](elem: Int)(f: Int => B): B = f(elem)
  }

  case class Student(name: String, id: Int)

  object StudentConverters extends Converters[Student] {
    def map[B](elem: Student)(f: Student => B): B = f(elem)
  }

  def main(args: Array[String]): Unit = {

    // implicit method may be needed for custom object to string conversion
    // can declare this method non-implicit and still it works
    implicit def str2Student(elem: String) = Student(elem, elem.size)

    val myStr = StrConverters
    println("byaas = " + myStr.map("byaas")(x => x.size))

    val myInt = IntConverters
    println("x 2.5 = " + myInt.map(4)(x => x * 2.5))

    println(Student("dev", 1))
    val studentConv = StudentConverters
    println("student ----> string : " + studentConv.map(Student("eklavya", 4))(x => x.name))
    // "x => Student" does not work, don't know how to make an implicit declaration, that's y making explicit call to str2Student
    println("string -----> student : " + myStr.map("babruwahan")(str2Student))

    val intList = List(1, 2, 3)
    println(intList)
    val int2List = intList.map { x => x * 1.5 }
    println(int2List)
    val strList = List("a", "bb", "ccc")
    println(strList)
    val str2List = strList.map { x => x.length() }
    println(str2List)
    val studentList = strList.map {str2Student}
    println(studentList)

  }
}