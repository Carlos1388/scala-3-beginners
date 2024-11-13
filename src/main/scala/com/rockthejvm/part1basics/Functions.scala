package com.rockthejvm.part1basics

object Functions {

  // def Name(args: Type): ReturnType = {Implementation}  Implementation is ALWAYS 1 expression
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  // invoking
  val functInvocation = aFunction("Scala", 99999)

  def aNoArgFunction(): Int = {345}

  def aParameterLessFunction: Int = 345  // two different styles of defining no args functions

  // recursive functions
  def stringConcat(str: String, n: Int): String = {if (n == 0) "" else if (n <= 1) str else str + stringConcat(str, n-1)}

  val str3 = stringConcat("Scala", 3)

  // in Scala NO LOOPS, when we need loops then use recursion


  // void functions
  def aVoidFunction(aString: String): Unit =
    println(aString)

  // you can 'in theory' create functions that have both side effect and meaningful value ( or functions with side effect)
  def computeDoubleStringWithSideEffect(aString: String): String = {
    aVoidFunction(aString) // Unit, side effect
    aString + aString // meaningful value
  } // this style of code is discouraged in functional programming
  // in general, every side effect is discouraged

  /** REASONS
   *  produce unwanted values
   *  make code difficult to read on large codebases
   */

    // bigger functions to wrap smaller functions
    def aBiggerFunction(n: Int): Int = {
      def aSmallerFunction(a: Int, b: Int): Int = a + b
      aSmallerFunction(n, n+1)
    }


  /**
   * Exercises
   * 1 - Grreting Function
   * 2 - Factorial function, with 0 as return if neg arg
   * 3 - Fibonacci
   *      fib(1) == fib(2)
   *      fib(3) = fib(2) + fib(1)
   *
   * 4 - Test if a number is prime
   */

  def Greetings(name: String, age: Int): String = s"Hi, my name is $name and I am $age years old"

  def Factorial(n: Int): Int = if (n < 0) 0 else if (n==0) 1 else n * Factorial(n-1)

  def Fibonacci(n: Int): Int = if (n < 3) 1 else Fibonacci(n-1) + Fibonacci(n-2)

  def TestPrime(n: Int): Boolean = { // casi
    def PrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && PrimeUntil(t - 1)

    PrimeUntil(n / 2)
  }

  def main(args: Array[String]): Unit = {
    // println(str3)
    // println(TestPrime(15))
    // println(TestPrime(10))
    // println(TestPrime(13))
    // println(TestPrime(3))
  }

}
