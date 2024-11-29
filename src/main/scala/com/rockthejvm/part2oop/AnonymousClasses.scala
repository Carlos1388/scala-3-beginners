package com.rockthejvm.part2oop

object AnonymousClasses {

  abstract class Animal {
    def eat(): Unit
  }

  class SomeAnimal extends Animal {
    override def eat(): Unit = println("I'm a weird animal")
  }

  val someAnimal = new SomeAnimal // only instance of SomeAnimal

  // if this Animal dataType is short-lived, the definition of the class is redundant.
  // we can easily instantiate an Animal on the spot without a class

  val someAnimal_v2 = new Animal:
    override def eat(): Unit = println("I'm a weird animal")

  // equivalent to something like this

  /*
   * class AnonymousClasses.AnonClass$1 extends Animal {
      override def eat(): Unit = println("I'm a weird animal")
    }

    val someAnimal_v2 = new AnonymousClasses.AnonClass$1
   */

  // done automatically by compiler, works for classes (abstract or no) and traits

  class Person(name: String) {
    def sayHi(): Unit = println(s"Hi, my name is $name")
  }

  // we can also do this on particular instances
  val jim = new Person("Jim") {
    override def sayHi(): Unit = println("MY NAME IS JIM")
  }





  def main(args: Array[String]): Unit = {

  }
}
