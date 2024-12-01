package com.rockthejvm.practice

import javafx.collections.transformation.SortedList

import scala.annotation.tailrec

abstract class LList[A] {
  def head: A

  def tail: LList[A]

  def isEmpty: Boolean

  def add(element: A): LList[A] = new Cons(element, this)

  override def toString: String = super.toString

  // concatenation
  infix def ++(anotherList: LList[A]): LList[A]

  def map[B](transformer: A => B): LList[B]
  def filter(predicate: A => Boolean): LList[A]
  def flatMap[B](transformer: A => LList[B]): LList[B]
  def foreach(function: A => Unit): LList[A]
  def sort(comparison: (A, A) => Int): LList[A]
  def zipWith[B, T](list: LList[T], zip: (A, T) => B): LList[B]
  def foldLeft[B](start: B)(folder: (A, B) => B): B
}

case class Empty[A]() extends LList[A] { // having empty of case class means all empties are the same
  override def head: A = throw new NoSuchElementException

  override def tail: LList[A] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def toString: String = "[]"

  override infix def ++(anotherList: LList[A]): LList[A] = anotherList

  override def map[B](transformer: A => B): LList[B] = new Empty()

  override def filter(predicate: A => Boolean): LList[A] = this

  override def flatMap[B](transformer: A => LList[B]): LList[B] = Empty()

  override def foreach(function: A => Unit): LList[A] = this

  override def sort(comparison: (A, A) => Int): LList[A] = this

  override def zipWith[B, T](list: LList[T], zip: (A, T) => B): LList[B] = {
    if (!list.isEmpty) throw new IllegalArgumentException("Zipping lists of non-equal length")
    else Empty()
  }

  override def foldLeft[B](start: B)(folder: (A, B) => B): B = start
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

  override def map[B](transformer: A => B): LList[B] = {
    Cons[B](transformer(head), tail.map(transformer))
  }

  override def filter(predicate: A => Boolean): LList[A] = {
    if (predicate(head)) Cons[A](head, tail.filter(predicate))
    else tail.filter(predicate)
  }

  override def flatMap[B](transformer: A => LList[B]): LList[B] = {
    transformer(head) ++ tail.flatMap(transformer)
  }

  override def foreach(function: A => Unit): LList[A] = {
    function(head)
    tail.foreach(function)
  }

  override def sort(comparison: (A, A) => Int): LList[A] = {
    // insertion sort, O(n²), stack recursive
    def insert(elem: A, sortedList: LList[A]): LList[A] = {
      if (sortedList.isEmpty) Cons(elem, Empty())
      else if (comparison(elem, sortedList.head) <= 0) Cons(elem, sortedList)
      else Cons(sortedList.head, insert(elem, sortedList.tail))
    }

    val sortedTail = tail.sort(comparison)
    insert(head, sortedTail)
  }

  override def zipWith[B, T](list: LList[T], zip: (A, T) => B): LList[B] = {
    if (list.isEmpty) throw new IllegalArgumentException("Zipping lists of non-equal length")
    else Cons(zip(head, list.head), tail.zipWith(list.tail,zip))
  }

  override def foldLeft[B](start: B)(folder: (A, B) => B): B = {
    tail.foldLeft(folder(head, start))(folder)
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
//     val evenPredicate: Int => Boolean = _ % 2 == 0
//
//     val doubler: Int => Int = _ * 2
//
//    val doublerList: (Int) => LList[Int] = x => Cons(x, new Cons(x + 1, Empty()))

     val numbers_doubled = first3Numbers.map(_ * 2)
     println(numbers_doubled)

     val doubledList = first3Numbers.map(x => Cons(x, new Cons(x + 1, Empty())))
     println(doubledList)

    // filter testing
    val onlyEvenNumbers = first3Numbers.filter(_ % 2 == 0)
    println(onlyEvenNumbers)

    val flattedDoubled = first3Numbers.flatMap(x => Cons(x, new Cons(x + 1, Empty())))
    println(flattedDoubled)

    // find test
    val isfourPredicate: (Int) => Boolean = _ == 4

//    println(LList.find(first3Numbers, isfourPredicate)) // exception
    println(LList.find(first3Numbers, _ % 2 == 0)) // 2

    first3Numbers.foreach(println)

    val another3Numbers = Cons(4, Cons(2, Cons(3, empty)))

    println(another3Numbers.sort(_ - _))

    println(first3Numbers.zipWith(another3Numbers, _ + _))

    println(first3Numbers.foldLeft(0)(_ + _))
  }
}