package implicits

object ImplicitParameter {
  
  case class Delimiter(left: String, right: String)
  
  def main(args: Array[String]): Unit = {
    def quotes(what: String)(implicit delim: Delimiter) = delim.left + what + delim.right
    def strQuotes(what: String)(implicit left: String, right: String) = left + what + right
    val delim = Delimiter("<<", ">>")
    println(quotes("baba")(delim))
    println(strQuotes("dada")("<<", ">>"))
  }
}