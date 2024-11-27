package com.rockthejvm.part2oop

object Inheritance {
  //
  class Animal {
    val creatureType = "wild"
    def eat(): Unit = println("nomnom")
  }

  class Cat extends Animal {
    def crunch() = {
      eat()
      println("crunch, crunch")
    }
  }

  val cat = new Cat

  class Person(val name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name, age) { // you need to specify a constructor from the Person class if it has two or more

  }

  // you can override methods of the superclass when extending
  class Dog extends Animal {
    override val creatureType: String = "domestic"

    // override def eat(): Unit = super.eat() // default implementation
    override def eat(): Unit = println("mmm, i like this bone")

    // a method worth overriding are the ones that every class have, like toString
    override def toString: String = "a dog"
    // when you print an instance of a class, you're printing instance.toString


  }

  val dog = new Dog

  // subtype polymorphism (inheritance means 'is a' relationship, so you can say that a Dog is an Animal)
  val new_dog: Animal = new Dog

  class Crocodile extends Animal {
    override val creatureType: String = "very wild"

    override def eat(): Unit = println("I can eat everything, I'm a croc")

    def eat(animal: Animal): Unit = println(s"I'm eating this fellow")
    // we can define methods with same name (overload) if the method has different signature:
    // different signature = different argument list (different number of args + different args types
    //       + different return type (optional)
  }

  /**
   * Exercise
   * which overloads are legal??
   *
   * Answers:
   * - illegal (dog is animal)  ----> wrong (Dog is more specific)
   * - legal, person is not animal ---> right
   * - legal, more args ----> right
   * - illegal, return type is optional ----> right, compiler can't know what you want by calling the eat method... int or unit?? it doesn't know
   * - legal if 3 is not implemented ---> wrong, is valid if you call with arguments in order.
   *
   * To know if an overload we can think "Will the compiler get confused?" If not, it is a valid overload
   */


  def main(args: Array[String]): Unit = {

    cat.eat() // we can do this because inherits eat from Animal
    cat.crunch() // also this, but not "animal.crunch()"

    dog.eat()
    new_dog.eat() // the most specific method will be called => "mm I like this bone"

  }
}
