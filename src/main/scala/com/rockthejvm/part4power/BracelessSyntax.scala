package com.rockthejvm.part4power

object BracelessSyntax {

  // starting in Scala 3 a new syntax without braces was introduced

  // if - expressions
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  // java-style
  val anIfExpression_v2 =
    if (2 > 3) {
      "bigger"
    }
    else {
      "smaller"
    }

  // compact style
  val anIfExpression_v3 =
    if (2 > 3) "bigger"
    else "smaller"

  // scala 3
  val anIfExpression_v4 =
    if 2 > 3 then
      "bigger" // higher indentation than the if part, that eliminates the ( ) and everything between 'then' and 'else' is like a codeblock
    else
      "smaller"

  val anIfExpression_v5 =
    if 2 > 3 then
      val result = "bigger" // this is
      result                        // a code-block
    else
      val result = "smaller" // this is
      result                         // another code-block

  // scala 3 one-lines version
  val anIfExpression_v6 = if 2 > 3 then "bigger" else "smaller"



  // for-comprehensions
  val aForComprehension = for {
    n <- List(1,2,3)
    s <- List("white", "black")
  } yield s"$n$s"

  // scala 3 style
  val aForComprehension_v2 =
    for
      n <- List(1,2,3)
      s <- List("white", "black")
    yield s"$n$s" // or
    //s"$n$s"


  // pattern matching without braces
  val meaningOfLife = 42
  val aPatternMatching = meaningOfLife match
    case 1 => "the one"
    case 2 => "the two"
    case _ => "anything else"

  // methods without braces
  def computeMeaningOfLife(n: Int): Int =
    val partialResult = 40


    // as much blank lines as we like


    partialResult + 2 // without braces this still is valid scala, it is considered as part of the code-block
  // (difficult to read without braces)

  // class definition with significant indentation
  class Animal: // we have to add : to tell the compiler the following indentation is for the class we're defining
    def eat(): Unit =
      println("I'm eating")

    def grow(): Unit =
      println("I'm growing")

  // this above is valid scala class
  // but if we have a huge class and is not clear where the class definition ends, there is an 'end' token
  end Animal

  // we can add an 'end' token for everything else that can use significant indentation as substitute of braces { }
  // for, if, match, methods, classes, traits, enums, objects

  // anonymous classes
  val aSpecialAnimal = new Animal {
    override def eat(): Unit = println("I'm special")
  }

  val aSpecialAnimal_v2 = new Animal:
    override def eat(): Unit = println("I'm special")


  // indentation = strictly larger indentation
  // 3 spaces + 2 tabs > 2 spaces + 2 tabs
  // 3 spaces + 2 tabs > 3 spaces + 1 tabs
  // 3 tabs + 2 spaces ?? 2 tabs + 3 spaces ====> so indent with spaces or tabs, don't mix



  def main(args: Array[String]): Unit = {
    println(anIfExpression_v5)
    println(computeMeaningOfLife(1))
  }
}
