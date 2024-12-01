package com.rockthejvm.part3fp

object HOFsCurrying {

  // functions can take functions as arguments and return as results: HOF (Higher Order Function)
  val aHof: (Int, (Int => Int)) => Int = (x, func) => x + 1
  val anotherHof: Int => (Int => Int) = x => (y => y + 2 * x)

  // quick exercise
  val superfunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = (x, func) => (y => x + y)

  // examples: map, flatMap, filter

  // more examples
  // f(f(...f(x))..) n times applied
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n-1, f(x))
  }

  val plusOne = (x: Int) => x + 1
  val tenThousand = nTimes(plusOne, 10000, 0)

  def nTimes_v2(f: Int => Int, n: Int): Int => Int = {
    // implementation
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimes_v2(f, n - 1)(f(x))
  }

//  val plusTenThousand_v2 = nTimes_v2(plusOne, 10000) // po(po(po(po... x10000 .... (x)))))  is not tail recursive and is not possible to create it
  val plusTenThousand_v2 = nTimes_v2(plusOne, 100) // so... StackOverflow if the argument is too big
  val tenThousand_v2 = plusTenThousand_v2(0)


  // currying = HOFs returning function instances
  val superAdder: Int => Int => Int = (x:Int) => (y:Int) => x + y
  val add3: Int => Int = superAdder(3)
  val invokeSupperAdder = superAdder(3)(100) // 103

  // curried methods = methods with multiple arg list
  def curriedFormatter(fmt: String)(x: Double): String = fmt.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f") // (x: Double) => "%4.2f.format(x)
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f") // (x: Double) => "%10.8f.format(x)


  def main(args: Array[String]): Unit = {
    println(tenThousand)
    println(tenThousand_v2)
    println(standardFormat(Math.PI))
    println(preciseFormat(Math.PI))
  }
}
