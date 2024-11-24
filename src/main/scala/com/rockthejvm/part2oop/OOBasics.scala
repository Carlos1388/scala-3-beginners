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


  // Exercise 1
  class Writer(first: String, surname: String,val year: Int) {
    def fullname(): String = {
      s"$first $surname"
    }
  }

  class Novel(name: String,yearOfRelease: Int,author: Writer) {
    def authorAge(): Int = yearOfRelease - author.year
    def isWrittenBy(author: Writer): Boolean = author == this.author
    def copy(newEditionYear: Int): Novel = new Novel(name, newEditionYear, this.author)
  }

  /** Exercise 2: Immutable counter class
   * - constructed with initial count
   * - increment/decrement => new instance of counter every time, because we don't want to use variables
   * - overload to increment(n) and decrement(n) => New instance of counter
   * - print the current count
  */

  class Counter(count: Int = 0) {
    def increment(): Counter = new Counter(count + 1)
    def decrement(): Counter = if (count !=0) new Counter(count - 1) else this
    def increment(n: Int): Counter = {
      if (n <= 0) this
      else increment().increment(n - 1)
    }

    def decrement(n: Int): Counter = {
      if (n <= 0) this
      else decrement().decrement(n - 1)
    } // marked as tailrecursion but not really, if we test it then we'll get SO

    def show_count(): Unit = println(s"Current user count is: $count")
  }



  def main(args: Array[String]): Unit = {
    //println(johnSayHiToDaniel)
    //println(johnSaysHi)

    val counter = new Counter()
    counter.show_count()
    counter.increment(8).show_count() // 8
    counter.decrement().show_count() // 0
    counter.increment(10).decrement(5).show_count() // 5
  }
}
