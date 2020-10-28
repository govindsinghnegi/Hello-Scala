package closureTest

class Foo {

  def exec(f: (String) => Unit, s: String) {
    f(s)
  }
}