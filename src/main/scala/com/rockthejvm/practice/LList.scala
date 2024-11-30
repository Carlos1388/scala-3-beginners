package com.rockthejvm.practice

import scala.annotation.tailrec

abstract class LList[A] {
  def head: A

  def tail: LList[A]

  def isEmpty: Boolean

  def add(element: A): LList[A] = new Cons(element, this)

  override def toString: String = super.toString

  // concatenation
  infix def ++(anotherList: LList[A]): LList[A]

  def map[B](transformer: Function1[A, B]): LList[B]
  def filter(predicate: Function1[A, Boolean]): LList[A]
  def flatMap[B](transformer: Function1[A, LList[B]]): LList[B]
}

case class Empty[A]() extends LList[A] { // having empty of case class means all empties are the same
  override def head: A = throw new NoSuchElementException

  override def tail: LList[A] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def toString: String = "[]"

  override infix def ++(anotherList: LList[A]): LList[A] = anotherList

  def map[B](transformer: Function1[A, B]): LList[B] = new Empty()

  def filter(predicate: Function1[A, Boolean]): LList[A] = this

  def flatMap[B](transformer: Function1[A, LList[B]]): LList[B] = Empty()
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

  override def map[B](transformer: Function1[A, B]): LList[B] = {
    Cons[B](transformer(head), tail.map(transformer))
  }

  override def filter(predicate: Function1[A, Boolean]): LList[A] = {
    if (predicate(head)) Cons[A](head, tail.filter(predicate))
    else tail.filter(predicate)
  }

  override def flatMap[B](transformer: Function1[A, LList[B]]): LList[B] = {
    transformer(head) ++ tail.flatMap(transformer)
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
  def find[A](list: LList[A], predicate: A => Boolean): A = {
    if (list.isEmpty) throw new NoSuchElementException()
    else if (predicate(list.head)) list.head
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
     val evenPredicate = new Function1[Int, Boolean] {
       override def apply(element: Int): Boolean =
         element % 2 == 0
     }

     val doubler = new Function1[Int, Int] {
       override def apply(value: Int): Int = value * 2
     }

    val doublerList = new Function1[Int, LList[Int]] {
      override def apply(v1: Int): LList[Int] =
        Cons(v1, new Cons(v1 + 1, new Empty()))
    }

     val numbers_doubled = first3Numbers.map(doubler)
     println(numbers_doubled)

     val doubledList = first3Numbers.map(doublerList)
     println(doubledList)

    // filter testing
    val onlyEvenNumbers = first3Numbers.filter(evenPredicate)
    println(onlyEvenNumbers)

    val flattedDoubled = first3Numbers.flatMap(doublerList)
    println(flattedDoubled)

    // find test
    val isfourPredicate = new Function1[Int, Boolean] {
      override def apply(element: Int): Boolean = element == 4
    }
//    println(LList.find(first3Numbers, isfourPredicate)) // exception
    println(LList.find(first3Numbers, evenPredicate)) // 2
  }
}