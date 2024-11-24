package com.rockthejvm.part1basics
import scala.annotation.tailrec
object Recursion {

  // "repetition" == recursion
  //@tailrec // if uncommented, compiler will show there's no tailrec
  def sumUntil(n: Int): Int = {
    if (n <= 0) 0
    else n + sumUntil(n - 1)
  }

  def sumUntil_v2(n: Int): Int = {
    @tailrec // evaluates if there's tailrec recursion
    def sumUntilTailrec(x: Int, accumulator: Int): Int =
      if (x <= 0) accumulator
      else sumUntilTailrec(x-1, accumulator + x) // TAIL recursion == recursive call occurs LAST in its code path
        // on above function sumUntil last expression is n + ---
    sumUntilTailrec(n, 0)
  } // no further stack frames necessary = no risk of stack overflow

  def sumNumbersBetween(a: Int, b: Int): Int =
    if (a > b) 0
    else a + sumNumbersBetween(a+1, b)

  def sumNumbersBetween_v2(a: Int, b: Int): Int = {
    @tailrec
    def sumTailrec(currentNumber: Int, accumulator: Int): Int =
      if (currentNumber > b) accumulator // if that occurs, im done calculating the sum
      else sumTailrec(currentNumber + 1, currentNumber + accumulator)

    sumTailrec(a, 0)
  }

  /**
   * Exercises
   * 1.- concat a string n times
   * 2.- tail recursive fibonacci
   * 3.- Discussion/question: Is the isPrime function tail recursive?? (the one from the Functions lesson) If not, write it
   *
   */

  def stringConcat(s: String, n: Int): String = {
    @tailrec
    def stringTrail(k: Int, cad: String): String =
      if (k > n) cad
      else stringTrail(k + 1, s + cad)

    stringTrail(0, s)
  }

  // other solution, the one before was wrong, stringTrail must start with cad == ""
  def concatenate(s: String, n: Int): String = {
    @tailrec
    def stringTrail(k: Int, accumulator: String): String =
      if (k <= 0) accumulator
      else stringTrail(k - 1, s + accumulator)

    stringTrail(0, "")
  }

  def fibonacci(n: Int): Int  = {
    @tailrec
    def fiboTail(idx: Int, last: Int, previous: Int): Int =
      if (idx >= n) last
      else fiboTail(idx + 1, last + previous, last)

    if (n <= 2) 1
    else fiboTail(2, 1, 1)
  }

  // 3 exercise. Bring back TestPrime function as it is
  def TestPrime(n: Int): Boolean = { // casi
    def PrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && PrimeUntil(t - 1)

    PrimeUntil(n / 2)
  }
  // TestPrime is already Tail Recursive because of the way boolean expressiones are evaluated
  // As PrimeUntil is only evaluated if n % t != 0 == true, it is the last evaluation. In fact, the function can
  // be rewritten for more clarity as:

  def TestPrime_v2(n: Int): Boolean = { // casi
    def PrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else if (n % t == 0) false
      else PrimeUntil(t - 1) // clearly, PrimeUntil is in the tail

    PrimeUntil(n / 2)
  }


  def main(args: Array[String]): Unit = {
    //println(sumUntil(20000)) // Stack Overflow
    // then how to overcome this problem?
    //println(sumUntil_v2(20000))
    // println(stringConcat("Scala", 50000)) // OK
    //println(fibonacci(6))
  }
}
