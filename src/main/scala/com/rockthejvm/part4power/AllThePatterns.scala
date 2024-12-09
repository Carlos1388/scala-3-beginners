package com.rockthejvm.part4power

import com.rockthejvm.practice.*

object AllThePatterns {

  object MySingleton
  // 1 - constants
  val someValue: Any = "Scala"
  val constants = someValue match {
    case 42 => "a number"
    case "Scala" => "THE Scala"
    case true => "a Boolean"
    case MySingleton => "a singleton object"
  }

  // 2 - match anything == match literally anything
  val matchAnythingVar = someValue match {  // we can match to any expression, and evaluate in the variable something, that can
    //be used in right hand side
    case something => s"I've matched anything, It's $something"
  }

  // when the value of the matched expression doesn't matter, we can use underscore _
  val matchAnything = someValue match {
    case _ => "I can match anything at all"
  }

  // 3 - tuples
  val aTuple = (1,4)
  val matchTuple = aTuple match {
    case (1,somethingElse) => s"A tuple with 1 and $somethingElse"
    case (somethingElse,2) => s"A tuple with 2 as second field"
  }

  // Pattern - matching structures can be nested
  val nestedTuple = (1,(2,3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) => "A nested tuple..."
  }

  // 4 - case classes
  val aList: LList[Int] = Cons(1, Cons(2, Empty()))
  val matchLList = aList match
    case Empty() => "An empty list"
    case Cons(head, Cons(_, tail)) => s"A non empty list starting with $head"

  val anOption: Option[Int] = Option(2)
  val matchOption = anOption match {
    case None => "An empty option"
    case Some(value) => s"Non empty: $value"
  }

  // 5 - list patterns
  val aStandardList = List(1,2,3,42)
  val matchstdList = aStandardList match {
    case List(1,_,_,_) => "list with 4 elements, first is 1"
    case List(1,_*) => "list with 1 as start"
    case List(1,2,_) :+ 42 => "List ending in 42 and starting with 1 and 2"
    case head :: tail => s"list starting with $head and ending with $tail"
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val matchedType = unknown match {
    case anInt: Int => s"I matched an Int, I can add 2 to it: ${anInt + 2}"
    case aString: String => s"I matched a String"
    case _: Double => s"I matched a Double I don't care about"
  }

  // 7 - name binding
  val bindingNames = aList match {
    case Cons(head, rest @ Cons(_, tail)) => s"a non-empty list starting with $head and ending with $rest"
  }

  // 8 - chained patterns
  /* pseudocode (imperative coding)
  switch(myValue) {
    case 1:
    case 2:
      run some code <-- this code runs for cases 1 and 2
  }
   */
  // (multi-match)
  val multimatch = aList match {
    case Empty() | Cons(0, _) => "An empty list to me"
    case _ => "anything else"
  }

  // 9 - if guards for filtering
  val secondElementSpecial = aList match
    case Cons(_, Cons(specialElement, _)) if specialElement > 5 => s"Second element is big enough"
    case _ => "anything else"


  /*
  Example
   */

  val aSimpleInt = 45
  val isEven_bad = aSimpleInt match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  // not correct? The following does the same, and has the same cases, maybe PM should be for more than 1 case or returning
  // values that aren't on the pattern matched
  val isEven_v2 = aSimpleInt % 2 == 0

  /*
  Exercise (and a trick)
   */
  val numbers = List(1,2,3,4)
  val numbersmatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfInts: List[Int] => "a list of numbers"
  }

  def main(args: Array[String]): Unit = {
    println(numbersmatch)
    /*
    PM runs at runtime
    - reflection infers actual type of expressions at runtime
    - generic types are erased at runtime in order to make the code compatible with old JVMs where generics didn't exist
        List[String] => List
        List[Int] => List
        Function1[Int, String] => Function1
       so at runtime, this conversion is done, and 'numbers' is just a list without the type, same for List[String]
       that is converted to List type. Then matching is in the first case
       If second case was the first, as List[Int] is converted to List, matching would be there
     */
  }
}
