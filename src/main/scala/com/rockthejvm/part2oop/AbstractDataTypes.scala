package com.rockthejvm.part2oop

object AbstractDataTypes {

  abstract class Animal { // abstract modifier means that a class can have members or methods without implementation
    val creatureType: String // abstract
    def eat(): Unit

    def preferredMeal: String = "anything"
  }

  // BUT every class that extends Animal must implement members
  // class Dog extends Animal {} // is illegal unless....
  class Dog extends Animal {
    override val creatureType: String = "domestic"

    override def eat(): Unit = println("crunching this bone")

    // overriding is legal for anything. There is a special kind of overriding in Scala
    // overriding a method with a val
    override val preferredMeal: String = "bones" // only possible for methods without args or keys { ... }
    // so... there is a difference between methods with and without parenthesis
    // a method without parenthesis is an "accessor method" and is eligible for override
  }

  // this will also be illegal
  //val anAnimal = new Animal // unless we define methods and fields

  //thi is legal though
  val aDog: Animal = new Dog


  // ultimate abstract data type: Traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  } // "something" that eats an animal. Everything can be left abstract

  // once defined trait, one can extend or inherit from the trait
  class TRex extends Carnivore {
    override def eat(animal: Animal): Unit = println("I'm a T-Rex, I eat animals")
  }

  // trait can also have constructor arguments (Since Scala 3)
  // traits can also be non-abstract
  // so... what is the difference between abstract types as traits and classes???
  // one practical difference and other not
  // 1 - PRACTICAL
  //    inheritance from ONE class
  //    but from SEVERAL traits
  //

  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"

    override def eat(): Unit = println("I'm a croc")

    override def eat(animal: Animal): Unit = println("croc eating animal")
  }

  // 2 - philosophical
  //     a class is expected to represent something, a concrete thing
  //     a trait is a description of the behaviour, a thing that another concrete things are supposed to do

  // MAIN DATA TYPES

  /**
   * ANY
   *  AnyRef --> every class extends AnyRef
   *    All classes + Strings + Lists...
   *      scala.Null --> the null reference, absence of a reference, it is a valid substitute for every class
   *  AnyVal --> Primitives data types extend this (Boolean, Char, Int,...)
   *    Int, Boolean, Char
   *
   *      .... scala.Nothing --> is under EVERYTHING, is a proper substitute for every value.
   *                            but can't be instantiated. The only way of getting Nothing is by throwing an exception
   *
   *
   */


  def main(args: Array[String]): Unit = {

  }
}
