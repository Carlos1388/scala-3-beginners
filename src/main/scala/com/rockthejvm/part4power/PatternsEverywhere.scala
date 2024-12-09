package com.rockthejvm.part4power

object PatternsEverywhere {

  // Other structures in Scala syntax that use PM
  // big idea #1 : catches are actually MATCHES
  val potentialFailure = try {
    // code
  } catch {
    case e: RuntimeException => "runtime excp"
    case npe: NullPointerException => "npe"
    case _ => "other exception"
  }
  // in other programming languages, match would be in the catch block


  // big idea #2: for comprehensions (generators) are based on pattern matching
  val aList = List(1,2,3,4)
  val evenNumbers = for {
    n <- aList if n % 2 == 0
  } yield 10 * n

  val tuples = List((1,2),(3,4))
  val filteredTuples = for {
    (first, second) <- tuples if first < 3
  } yield (first, second)  // same as match-case in pattern matching

  // big idea #3: new syntax (quick way of deconstructing structures)
  val aTuple = (1,2,3)
  val (a, b, c) = aTuple // python-like

  val head :: tail = tuples // this :: is also a pattern, so it will decompose the tuple


  def main(args: Array[String]): Unit = {

  }
}
