package companion

// companion object as static methods
class Account {
  
  val id: Int = Account.autoIncrement
  var balance: Int = 0
  def deposit(amount: Int) = balance = balance + amount
}

object Account {
  
  private var nextNumber: Int = 0
  def autoIncrement: Int = { nextNumber = nextNumber + 1; nextNumber }
  def instanceMethod: String = "an instance method"
}