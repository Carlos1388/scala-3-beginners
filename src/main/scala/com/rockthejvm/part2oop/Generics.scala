package com.rockthejvm.part2oop

object Generics {

  // reusing code

  //abstract class MyList {
    //def head: Int
    //def tail: Int
  //}

  //class Empty extends MyList {
    //override def head: Int = throw new NoSuchElementException()
    //override def tail: Int = throw new NoSuchElementException()
  //}

  //class NoEmpty(override val head: Int, override val tail: Int) extends MyList


  // this code is reusable for every type of data, not only Int.
  // a solution is to copy and paste for another type of data (e.g. Strings)
  // not a good solution

  // the other solution is to make this class more general, e.g. instead of Int use Any, so that the code is applicable for every type
  // since every type is child of Any
  // the problem is that when I want to access data inside the collection, the datatype is Any, that doesn't have any implemented methods
  // is useless. We've lost what is called 'type safety'

  // buuut we can add type argument to the class
  // because we want to be able to make classes for every type, and don't throw errors in the process, we don't want to lose
  // the type safety

  abstract class MyList[A] { // "generic" list
    def head: A
    def tail: MyList[A]
  }

  class Empty[A] extends MyList[A] {
    override def head: A = throw new NoSuchElementException
    override def tail: MyList[A] = throw new NoSuchElementException
  }

  class NoEmpty[A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  // so this is correct
  val listOfInt = NoEmpty(1, new NoEmpty(2, new Empty))
  val listOfStrings = NoEmpty("Scala", new NoEmpty("Java", new Empty))

  val firstNumber = listOfInt.head
  val adding = firstNumber + 3 // this is now possible, because firstNumber is inferred to be Int

  // but this will work with any type under MyList

  // better to specify type of each
  //val listOfInt = NoEmpty[Int](1, new NoEmpty[Int](2, new Empty[Int]))


  // In Scala, we have multiple generic types
  trait MyMap[K, V] // K and V is a convention, Key and Value

  // companion object for MyList
  object MyList {
    def from2Elements[A](elem1: A, elem2: A): MyList[A] =
      new NoEmpty[A](elem1, new NoEmpty[A](elem2, new Empty[A]))
  }

  // calling methods
  val first2Numbers = MyList.from2Elements[Int](1,2) // from the arguments the compiler infers A
  val first2Numbers_v2 = MyList.from2Elements(1, 2)
  val first2Numbers_v3 = new NoEmpty(1, new NoEmpty(2, new Empty))

  /** Exercise
   * genericsize the LList from before
   * */

    // result in LList



  def main(args: Array[String]): Unit = {

  }
}
