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

abstract class LList[A] {
  def head: A

  def tail: LList[A]

  def isEmpty: Boolean

  def add(element: A): LList[A] = new Cons(element, this)

  override def toString: String = super.toString
}

class Empty[A] extends LList[A] {
  override def head: A = throw new NoSuchElementException

  override def tail: LList[A] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def toString: String = "[]"
}

//class Cons(value: Int, next: LList) extends LList { // cons means "constructor"
//override def head: Int = value
//override def tail: LList = next
class Cons[A](override val head: A, override val tail: LList[A]) extends LList[A] { // trick for not having to override them every time
  override def isEmpty: Boolean = false

  override def toString: String = {
    @tailrec
    def concatenateElements(remainder: LList[A], acc: String): String =
      if (remainder.isEmpty) acc
      else concatenateElements(remainder.tail, s"$acc, ${remainder.head}")

    s"[${concatenateElements(this.tail, s"$head")}]"
  }
}

object LListTest {
  def main(args: Array[String]): Unit = {

    val empty = new Empty[Int]

    println(empty)
    println(empty.isEmpty)

    val first3Numbers = new Cons(1, new Cons(2, new Cons(3, empty)))

    println(first3Numbers)

    val first3numbers_v2 = empty.add(1).add(2).add(3) // when added, numbers are first in list

    println(first3numbers_v2)
    println(first3numbers_v2.isEmpty)

    val someStrings = new Cons("dog", new Cons("cat", new Empty))

    println(someStrings)
  }
}