package com.rockthejvm.part3fp

object WhatsAFunction {

  // FP : functions are 'first-class' citizens
  // we operate with functions as with every other values
  // JVM  was built for java == canonical OO language, no function as first-class citizen

  trait MyFunction[A, B] {
    def apply(arg: A): B
  }

  val doubler = new MyFunction[Int, Int] {
    override def apply(arg: Int): Int = arg * 2
  }

  val meaningOfLife = 42
  val meaningDoubled = doubler(meaningOfLife) // doubler.apply(meaningOfLife), but because of apply method, we can call doubler as a function
  // I created the concept of the function

  // this trait is also prebuilt with some function types
  val doublerStd = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 * 2
  }

  val meaningDoubled_v2 = doublerStd(meaningOfLife)

  // al functions are instances of FunctionX with apply methods
  val adder = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  val anAddition = adder(2, 67)

  val aThreeArgFunction = new Function4[Int, String, Double, Boolean, Int] {
    override def apply(v1: Int, v2: String, v3: Double, v4: Boolean): Int = 34
  }

  // we can also write FunctionX's another way
  // compiler says Function4[Int, String, Double, Boolean, Int] == (Int, String, Double, Boolean) => Int
  // so this is another way of writing FunctionX


  /**
   * Exercises
   * 1 - Define a function instance that takes 2 strings and concatenates them
   * 2 - Explore LList, figure out if Predicate and Transformer can be expressed as functions, how?? what code would we need to change to remove them and replace with functions?
   * 3 - Define a functions which takes an int as an argument and returns ANOTHER function as a result
   */

   // 1-
  val concatStrings = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = s"$v1$v2"
  }

  // 2-
  // yes, Predicate[T] are equivalent to a function from T to a boolean, Function1[T, Boolean]
  // and Transformer[A, B] trait is equivalent to a function from A to B, Function1[A, B]

  // 3-
//  def multiplier(n: Int) = {
//    new Function1[Int, Int] {
//      override def apply(v1: Int) = v1 * n
//    }
//  }
  val suppermult = new Function[Int, Function1[Int, Int]] {
    override def apply(x: Int): Int => Int = new Function1[Int, Int] {
      override def apply(y: Int): Int = x * y
    }
  }

  val mult2 = suppermult(2)

  val amultiplication = mult2(7) // 14
  // currying
  val amultiplication_v2 = suppermult(3)(7) // 21

  // function values != methods



  def main(args: Array[String]): Unit = {
    println(amultiplication)
    println(amultiplication_v2)
  }
}
