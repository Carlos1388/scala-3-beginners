package com.rockthejvm.part3fp

object MapFlatMapFilterFor {

  // standard list
  val aList = List(1, 2, 3) // a LList as we built it [1] -> [2] -> [3] -> Nil
  val firstElement = aList.head
  val restOfElements = aList.tail

  // map
  val anIncrementedList = aList.map(_ + 1)

  // filter
  val onlyOdd = aList.filter(_ % 2 != 0)

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  val aFlatMapList = aList.flatMap(toPair) // [1,2,2,3,3,4]

  /* Exercise */
  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white", "red")

  // All the possible combinations of all the elements of this list in the format "1a - black"
  val combinations = numbers.flatMap(number => chars.flatMap(char => colors.map(color => s"$number$char - $color")))

  // for comprehension is a more readable form of the above === is IDENTICAL to flatMap + map chains
  val combinationsFor = for {// extra features
    // we can filter to subsets, eq to   val combinations = numbers.filter(_ % 2 == 0).flatMap(number => chars.flatMap(char => colors.map(color => s"$number$char - $color")))
    // not true really, filter is not used, when decomposing fors, compiler uses 'withFilter'
    number <- numbers if number % 2 == 0 // generator
    char <- chars
    color <- colors
  } yield s"$number$char - $color" // an EXPRESSION
  // this is not iteration, is an expression that decomposes to the above flatMap + maps

  // for-comprehensions with Unit
  // if foreach

  /** Exercises
   * 1- LList supports for comprehensions? if not, add what's missing
   * 2- A small collection of AT MOST ONE element - Maybe[A]
   *    - map
   *    - flatMap
   *    - filter
   * */
  import com.rockthejvm.practice.*
  val lSimpleNumbers: LList[Int] = Cons(1, Cons(2, Cons(3, Empty())))
  // map flatmap, filter
  val incrementedNumbers = lSimpleNumbers.map(_ + 1)
  val filteredNumbers = lSimpleNumbers.filter(_ % 2 == 0)
  val toPairLList: Int => LList[Int] = (x: Int) => Cons(x, Cons(x + 1, Empty()))
  val flatMappedNumbers = lSimpleNumbers.flatMap(toPairLList)

  // map + flatMap = for comprehensions, but we cannot filter because compiler uses withFilter for filtering, we would have to implement it
  val combinationNumbers = for {
    number <- lSimpleNumbers
    char <- Cons('a', Cons('b', Cons('c', Empty())))
  } yield s"$number - $char"


  def main(args: Array[String]): Unit = {
    combinations.foreach(println)
    //same as
    for {
      num <- numbers
    } println(num)

    combinationsFor.foreach(println)
    println(combinations eq combinationsFor)
    println(combinations == combinationsFor)
    println(combinations equals combinationsFor)

    println(combinationNumbers)
  }
}
