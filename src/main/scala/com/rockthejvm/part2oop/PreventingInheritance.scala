package com.rockthejvm.part2oop

object PreventingInheritance {

  // how to prevent some methods from being inherited??
  class Person(name: String) {
    final def enjoyLife(): Int = 42 // final for preventing enjoyLife from being overwritten

  }

  class Adult(name: String) extends Person(name) {
    // override def enjoyLife(): Int = 999 // this is illegal because of the final
  }

  // you can put final before class
  final class Animal // cannot be inherited
  // class Cat extends Animal // illegal

  // sealing a type hierarchy
  sealed class Guitar(nStrings: Int) // sealed is a keyword that restricts inheritance outside this file
  class ElectricGuitar(nStrings: Int) extends Guitar(nStrings)
  class AcousticGuitar extends Guitar(6)

  // no modifiers = "no encouraging" inheritance
  // as good practice, scala classes that can be modified should be marked as "open" and well documented
  open class ExtensibleGuitar(nStrings: Int)

  def main(args: Array[String]): Unit = {

  }
}
