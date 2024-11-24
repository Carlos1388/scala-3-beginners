package com.rockthejvm.part2oop

object OOBasics {

  class Person(val name: String, age: Int) { // constructor signature
    // fields
    val allCaps = name.toUpperCase()

    // methods
    def greet(name: String): String = {
      s"${this.name} says: Hi, $name" // this is like self in python
    }

    // multiple methods with same name but different signatures
    def greet(): String = {
      s"Hi, everyone, my name is $name" // no need for disambiguation, compiler takes closest definition of name in the scope
    } // this method has the same name but different arguments, or signature (number of args and type of args)
    // the fact of having various methods with same name is called overloading

    // aux constructor
    def this (name: String) =
      this(name, 0) // we call the original constructor with age == 0 if we try to instantiate a Person without age

    // we can define as many as we like
    def this() =
      this("Jane Doe")

    // though, this is the same as having default arguments in constructor of the class
  }

  val aPerson: Person = new Person("John", 26)
  // val johns_age = aPerson.age will not compile, age is just a constructor argument, not a field. To make it a field, attach keyword val
  val john = aPerson.name
  val johnyelling = aPerson.allCaps

  val johnSayHiToDaniel = aPerson.greet("Daniel")
  val johnSaysHi = aPerson.greet()


  def main(args: Array[String]): Unit = {
    println(johnSayHiToDaniel)
    println(johnSaysHi)
  }
}
