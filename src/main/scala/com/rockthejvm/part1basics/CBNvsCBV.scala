package com.rockthejvm.part1basics

object CBNvsCBV {

  // CVB = call by value = arguments are evaluated before function invocation

  def aFucntion(arg: Int): Int = {
    arg + 1
  }

  val aComputation = aFucntion(23 + 67) // 23 + 67 is evaluated before invoke

  // CBN = call by name = arguments are passed LITERALLY, they are evaluated when used inside the function at every reference

  def aByNameFunction(arg: => Int): Int = arg + 1

  val anotherComputation = aByNameFunction(23 + 67)
  // this does the following:
  // pass expression 23 + 67 down to aByNameFunction and that function puts it where arg is found
  // returns 23 + 67 + 1
  // whereas in the CBV version, 23 + 67 = 90 ---> return 90 + 1

  def printTwiceByValue(x: Long): Unit = {
    println("By Value:" + x)
    println("By Value:" + x)
  }

  def printTwiceByName(x: => Long): Unit = {
    println("By Name:" + x)
    println("By Name:" + x)
  }

  def ininfinite(): Int = 1 + ininfinite() // will produce stackOverflow

  def printFirst(x: Int, y: => Int) = println(x)

  def main(args: Array[String]): Unit = {
    //println(aComputation)
    //println(anotherComputation)

    //printTwiceByValue(System.nanoTime())  // same value will be printed twice

    //printTwiceByName(System.nanoTime()) // this time, we will print two different numbers, the expression is passed
    // without evaluation until needed IT IS IMPORTANT FOR FUTURES/OPTIONS, etc

    // if we do this the app will crash because of stack overflow
    //printFirst(ininfinite(), 42)

    // but if we do this, it wont, because ininfinite() is never evaluated, as it is CBN
    printFirst(42, ininfinite())
    // so dangerous operations can be delayed till really needed
  }
}
