package com.rockthejvm.practice

import scala.annotation.tailrec

// singly linked list
// contains nodes with values
// [1,2,3] = [1] -> [2] -> [3] -> |
//abstract class LList {
//  def head: Int
//  def tail: LList
//  def isEmpty: Boolean
//  def add(element: Int): LList
//
//  override def toString: String = super.toString
//}
//
//class Empty extends LList {
//  override def head: Int = throw new NoSuchElementException
//  override def tail: LList = throw new NoSuchElementException
//  override def isEmpty: Boolean = true
//  override def add(element: Int): LList = new Cons(element, this)
//
//  override def toString: String = "[]"
//}
//
////class Cons(value: Int, next: LList) extends LList { // cons means "constructor"
//  //override def head: Int = value
//  //override def tail: LList = next
//class Cons(override val head: Int, override val tail: LList) extends LList { // trick for not having to override them every time
//  override def isEmpty: Boolean = false
//  override def add(element: Int): LList = new Cons (element, this)
//
//  override def toString: String = {
//    @tailrec
//    def concatenateElements(remainder: LList, acc: String): String =
//      if (remainder.isEmpty) acc
//      else concatenateElements(remainder.tail, s"$acc, ${remainder.head}")
//
//    s"[${concatenateElements(this.tail, s"$head")}]"
//  }
//}


// version 2, after lesson 17
// version 3 after lesson 18

trait Predicate[T] {
  def test(element: T): Boolean
}

class EvenPredicate extends Predicate[Int] {
  override def test(element: Int): Boolean = element % 2 == 0
}


trait Transformer[A, B] {
  def transform(value: A): B
}

class Doubler extends Transformer[Int, Int] {
  override def transform(value: Int): Int = value * 2
}

class DoublerList extends Transformer[Int, LList[Int]] {
  override def transform(value: Int): LList[Int] = new Cons[Int](value, new Cons[Int](value +1, new Empty[Int]))
}


abstract class LList[A] {
  def head: A

  def tail: LList[A]

  def isEmpty: Boolean

  def add(element: A): LList[A] = new Cons(element, this)

  override def toString: String = super.toString

  // concatenation
  infix def ++(anotherList: LList[A]): LList[A]

  def map[B](transformer: Transformer[A, B]): LList[B]
  def filter(predicate: Predicate[A]): LList[A]
  def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B]
}

case class Empty[A]() extends LList[A] { // having empty of case class means all empties are the same
  override def head: A = throw new NoSuchElementException

  override def tail: LList[A] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def toString: String = "[]"

  override infix def ++(anotherList: LList[A]): LList[A] = anotherList

  def map[B](transformer: Transformer[A, B]): LList[B] = new Empty()

  def filter(predicate: Predicate[A]): LList[A] = this

  def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] = Empty()
}

//class Cons(value: Int, next: LList) extends LList { // cons means "constructor"
//override def head: Int = value
//override def tail: LList = next
case class Cons[A](override val head: A, override val tail: LList[A]) extends LList[A] { // trick for not having to override them every time
  override def isEmpty: Boolean = false

  override def toString: String = {
    @tailrec
    def concatenateElements(remainder: LList[A], acc: String): String =
      if (remainder.isEmpty) acc
      else concatenateElements(remainder.tail, s"$acc, ${remainder.head}")

    s"[${concatenateElements(this.tail, s"$head")}]"
    }

  override infix def ++(anotherList: LList[A]): LList[A] = {
    Cons[A](head, tail ++ anotherList)
  }

  override def map[B](transformer: Transformer[A, B]): LList[B] = {
    Cons[B](transformer.transform(head), tail.map(transformer))
  }

  override def filter(predicate: Predicate[A]): LList[A] = {
    if (predicate.test(head)) Cons[A](head, tail.filter(predicate))
    else tail.filter(predicate)
  }

  override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] = {
    transformer.transform(head) ++ tail.flatMap(transformer)
  }
}


/** EXERCISE: LList Extension - - - - Lesson 18 */

/**
 * 1- Generic trait Predicate[T] with a method test(T) => Boolean
 * 2- Generic trait Transformer[A, B] with a method transform(A) => B
 * 3- LList:
 *  - map(transformer: Transformer[A, B]) => Llist[B]
 *  - filter(predicate: Predicate[A]) => Llist[A]
 *  - flatMap(transformer from A to LList[B]) => LList[B]
 */

object LList {
  def find[A](list: LList[A], predicate: Predicate[A]): A = {
    if (list.isEmpty) throw new NoSuchElementException()
    else if (predicate.test(list.head)) list.head
    else find[A](list.tail, predicate)
  }
}

object LListTest {
  def main(args: Array[String]): Unit = {

    val empty = new Empty[Int]()
//
//    println(empty)
//    println(empty.isEmpty)
//
    val first3Numbers = Cons(1,Cons(2, Cons(3, empty)))
//
//    println(first3Numbers)
//
//    val first3numbers_v2 = empty.add(1).add(2).add(3) // when added, numbers are first in list
//
//    println(first3numbers_v2)
//    println(first3numbers_v2.isEmpty)
//
//    val someStrings = new Cons("dog", new Cons("cat", new Empty))
//
//    println(someStrings)

    // map testing
     val evenPredicate = new Predicate[Int] {
       override def test(element: Int): Boolean =
         element % 2 == 0
     }

     val doubler = new Transformer[Int, Int] {
       override def transform(value: Int): Int = value * 2
     }

     val numbers_doubled = first3Numbers.map(doubler)
     println(numbers_doubled)

     val doubledList = first3Numbers.map(new DoublerList)
     println(doubledList)

    // filter testing
    val onlyEvenNumbers = first3Numbers.filter(new EvenPredicate)
    println(onlyEvenNumbers)

    val flattedDoubled = first3Numbers.flatMap(new DoublerList)
    println(flattedDoubled)

    // find test
    val isfourPredicate = new Predicate[Int] {
      override def test(element: Int): Boolean = element == 4
    }
//    println(LList.find(first3Numbers, isfourPredicate)) // exception
    println(LList.find(first3Numbers, evenPredicate)) // 2
  }
}