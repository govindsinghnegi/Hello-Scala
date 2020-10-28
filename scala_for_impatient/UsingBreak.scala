import scala.util.control.Breaks._

object UsingBreak {

  def main(args: Array[String]): Unit = {
    var sum = 0
    breakable {
      for (i <- 1 to 10) {
        sum += i
        if (sum > 5) break
      }
    }
    println("sum = " + sum)
  }

  def iterate1To5 = {
    for (i <- 1 to 10) {
      if (i > 5) break
    }
  }

}