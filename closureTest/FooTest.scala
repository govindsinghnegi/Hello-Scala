package closureTest

object FooTest {

  def main(args: Array[String]): Unit = {
    var hello = "Hello"
    def sayHello(name: String) = println(hello +" " + name)
    val foo: Foo = new Foo
    foo.exec(sayHello, "guru")
    hello = "Hola"
    foo.exec(sayHello, "guru")
    
  }

}