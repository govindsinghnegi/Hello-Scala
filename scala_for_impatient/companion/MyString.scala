package companion

// companion object as class factory
class MyString(private val base: String) {

  private var extra: String = ""
  override def toString: String = base + " " + extra
}

object MyString {

  def apply(base: String) = new MyString(base)
  def apply(base: String, extra: String) = {
    val temp = new MyString(base)
    // accessing private member of companion class
    temp.extra = extra
    temp
  }
}