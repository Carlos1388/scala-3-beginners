package com.rockthejvm.part2oop

import scala.language.postfixOps

object MethodNotation {

  class Person(val name: String,val age: Int,val favouriteMovie: String) {
    infix def likes(movie: String): Boolean =
      movie == favouriteMovie

    //infix def +(person: Person): String =
    //  s"${this.name} is hanging out with ${person.name}"

    infix def !!(pgLan: String): String =
      s"$name exclaims: How cool is $pgLan"

    // prefix position for defining the opposite operator, here the structure (unary_) is fixed (reserved)
    // unary operators supported by Scala are: -, +, ~, !
    def unary_- : String =
      s"$name's alter ego"

    // postfix position
    def isAlive: Boolean = true

    // apply, has a special meaning in Scala
    //def apply(): String =
    //  s"Hi, my name is $name and $favouriteMovie is my favourite movie"

    /**
     * Exercises
     * - a + operator that returns a new person with a nickname
     * - a UNARY + operator that increases the person's age
     * - an apply method with an int arg
     */

    infix def +(nickname: String): Person = {
      new Person(s"$name $nickname", age, favouriteMovie )
    }

    def unary_+ : Person = {
      new Person(name, age + 1, favouriteMovie)
    }

    def apply(n: Int): String = {
      s"$name watched $favouriteMovie $n times"
    }


  }

  val mary = new Person("Mary", 34, "Inception")
  val john = new Person("John", 36, "Fight Club")






  def main(args: Array[String]): Unit = {

    println(mary.likes("Fight Club"))
    println(mary likes "Fight Club") // thanks to 'infix' before 'def' <--- INFIX NOTATION
    // in fact, it also works without the 'infix'

    //println(mary + john) // mary + john == mary.+(john) , in fact the expression 2 + 1 is the + method applied to int
    // you can do 2.+(3)

    // Scala is infinite in what refers to method naming
    println(mary !! "Scala")
    println(mary.!!("Scala"))

    println(-mary)
    println(mary.isAlive) // or to resemble natural language:
    println(mary isAlive) // only works if we import scala.language.postfixOps
    // BUT! is highly discourages because it just replaces . with space

    // it is always possible to use the method 'apply' like this
    //println(mary()) // this is the same as mary.apply()

    // test for exercises
    println(s"name: ${mary.name}, age: ${mary.age}, movie: ${mary.favouriteMovie} ")

    val maryanne = mary + "Juana"
    println(maryanne.name)

    val mary_old = +mary
    println(mary_old.age)

    println(mary(5))




  }

}
