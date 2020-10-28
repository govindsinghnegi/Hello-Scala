package companion

object CompanionObject {

  def main(args: Array[String]): Unit = {
    val a1 = new Account
    a1.deposit(100)
    println("acc1 id : " + a1.id + " and balance : " + a1.balance)
    val a2 = new Account
    a2.deposit(50)
    println("acc2 id : " + a2.id + " and balance : " + a2.balance)
    
    /////////////////////////////////
    
    println(MyString("radha"))
    println(MyString("radha", "swami"))
  }
}