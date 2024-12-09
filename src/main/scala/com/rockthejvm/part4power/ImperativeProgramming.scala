package com.rockthejvm.part4power

object ImperativeProgramming {

  val meaningOfLife = 42
  // meaningOfLife = 43 // illegal

  // mutable variable
  var aVariable = 99
  aVariable = 100 // if I want to reassign

  // compiler is able to associate type for a variable at first seen, so I cannot reassign variables to another type
  // I can't do aVariable = "Scala"

  aVariable += 10 // aVariable = aVariable + 10

  // aVariable++ is illegal in Scala


  //  loops
  def testLoop(): Unit = {
    var i = 0
    while (i < 10) {
      println(s"Counter is at $i")
      i += 1
    } // this will work
  }

  // but this is not recommended:
  // loops make code difficult to read in large codebases
  // and in critical environments, you don't want that. Also debugging becomes more difficult
  // In distributed systems, which is where Scala shines, you can get a lot of errors running imperative programming code
  // because of making it vulnerable to concurrency problems (e.g. synchronization)

  // So... use imperative programming when it's for a good reason ( interactions with Java libraries, performance-critical applications)

  val anExpression = aVariable += 10 // modifying a variable is Unit type, so the value of the expression is 'modifying a value to add 10'
  val aLoop = while (aVariable < 100) {
    println("counting...")
    aVariable += 1
  } // this is also Unit, the value is the act of looping


  def main(args: Array[String]): Unit = {
    testLoop() // this works as expected
  }
}
