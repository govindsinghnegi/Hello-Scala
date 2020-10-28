package implicits

object ImplicitFunction {

  class Fraction(num: Int, den: Int) {

    val n: Int = num
    val d: Int = den

    def *(f: Fraction): Fraction = {
      new Fraction(this.n * f.n, this.d * f.d)
    }

    override def toString() = "num = " + num + " and den = " + den
  }

  def main(args: Array[String]): Unit = {

    implicit def intToFraction(x: Int) = new Fraction(x, 1)

    implicit def fractionToInt(f: Fraction) = f.d

    val frac1 = new Fraction(2, 3)
    val frac2 = new Fraction(2, 3)
    println(frac1 * frac2)
    println(2 * frac1)
    println(frac1 * 2)
    /**
     * intended: val fileContent = new File("abc.txt").read()
     * using implicit function...
     * class RichFile(file: File){
     *  def read = Source.fromFile(file).mkString
     * }
     *
     * implicit def fileToRichFile(file: File) = new RichFile(file)
     *
     * val fileContent = new File("abc.txt").read()
     */
  }
}