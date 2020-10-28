

object FunctionsUsage {

  def moneyTransfer(amount: Double, providerFee: Double => Double): Double = {
    amount + 1 + providerFee(amount)
  }

  def getStrategy(enoughEnergy: Boolean) = {
    if (enoughEnergy)
      (energy: Double) => "We are going to attack with damage " + energy
    else
      (energy: Double) => "We are going to reflect damage " + energy / 2
  }

  def main(args: Array[String]): Unit = {

    println(moneyTransfer(100, m => m / 10))
    val returnedFunction = getStrategy(true)
    println(returnedFunction(15.0))
  }
}