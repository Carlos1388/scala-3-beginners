package com.rockthejvm.part3fp

object AnonymousFunctions {

  // instances of FunctionN
  val doubler: Int => Int = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  }

  // lambdas (in scala, anonymous function instances)
  val doubler_v2: Int => Int = (x: Int) => x * 2 // identical to doubler
  val adder: (Int, Int) => Int = (x: Int, y: Int) => x + y

  // zero arguments functions
  def justDoSmth: () => Int = () => 45
  val anInvocation = justDoSmth()

  // alt syntax with curly braces
  val stringToInt = { (str: String) =>
    // implementation: code block
    str.toInt
  }

  // type inference, compiler can also infer argument types
  // but if I specify types of arguments on left, args on right are not necessary to specify
  val doubler_v3: Int => Int = x => x * 2
  val another_adder: (Int, Int) => Int = (x, y) => x + y

  // Scala tends to prefer more compact way of writing things
  // shortest lambdas
  val doubler_v4: Int => Int = _ * 2 // short way to say x => x * 2, _ means 'the first argument'
  val adder_v3: (Int, Int) => Int = _ + _ // the compiler figures out which underscore goes with which argument
  // each underscore is a different argument

  /**
   * Exercises
   * 1- Replace all the function instances in LList with lambdas
   * 2- Rewrite the suppermult function with lambdas (from WhatsAFunction)
   *
   */




  def main(args: Array[String]): Unit = {
    println(justDoSmth) // com.rockthejvm.part3fp.AnonymousFunctions$$$Lambda$3/317574433@21a06946
    println(justDoSmth()) // 45 (function invocation)
  }
}
