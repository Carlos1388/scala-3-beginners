package com.rockthejvm.part1basics

object Expressions {

  // expressions are structures that can be evaluated to a value
  val meaningOfLife = 40 + 2 // expression is '40 + 2' that takes the value 42 when assigned into val meaningOfLife

  // mathematical expressions: +, -, *, /, bitwise |, &, <<, >>, >>>
  val mathExpression = 2 + 3 * 4 // first goes the mult

  // comparison expressions: <, <=, >, >=, ==, !=
  val equalityTest = 1 == 2 // 1==2 expression that returns truth value

  // boolean expressions: !, ||, &&
  val nonEqualityTest = !equalityTest

  // instructions vs expressions
  // expressions (scala) are evaluated, instructions are executed (python)
  // we think in terms of expressions

  // ifs are expressions in scala
  val aCondition = true
  val anIfExpression = if (aCondition) 45 else 99 // evaluated to a single value --> expression

  // code block, also expression, big expression. The value is the value of the last expression in the block
  val aCodeBlock = {
    val localValue = 99
    localValue + 10 // 'return' localValue + 10
  }

  /**
   * Exercise:
   *
   * responses:
   *  1 - will print 'true'  ---> ok
   *  2 - will print '42' ---> ok
   *  3 - will print the info about the print function applied to 'Scala'?? --> NO, println has endtype Unit, that prints as the value '()'
   *      so third will print '()' after printing Scala first when evaluating println("Scala")
   */

  def main(args: Array[String]): Unit = {

}
}
