package com.rockthejvm.part4power

import scala.util.Random

object PatternMatching {

  // switch on steroids
  val random = new Random()
  val avalue = random.nextInt(100)

  val description = avalue match
    case 1 => "the first"
    case 2 => "the second"
    case 3 => "the third"
    case _ => s"something else: $avalue"
  // if there is no default case then it will return an Exception (MatchError)

  // decomposing values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", avalue)

  val greeting = bob match {
    case Person(n, a) if a < 18 => s"Hello there, my name is $n and I'm $a years old"
    case Person(n, a) => s"Hello there, my name is $n and I'm not allowed to say my age"
    case _ => "I don't know who I am"
  }

  /*
  // patterns are match in sequence, if first pattern contains second, It will match with first pattern CAREFUL!!
  // Put the more specific pattern first
  // The type returned by the match case is the lowest common ancestor of all types on the right hand side of the branch
  */

  // pattern matching on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Cat(meowStyle: String) extends Animal

  val anAnimal: Animal = Dog("Terra Nova")
  val animalPM = anAnimal match {
    case Dog(somebreed) => "I've detected a dog"
    case Cat(meowStyle) => "A cat detected"
    case _ => "Another animal detected"
  } // compiler will tell us that not every pattern is considered (warning: 'match may not be exhaustive')
  // so we have to add the Cat case and the general Animal case


  /*
  Exercise
  show(Sum(Sum(Number(2),Number(3)),Number(4))) = "2 + 3 + 4"
  show(Prod(Sum(Number(2),Number(3)),Number(4))) = "(2 + 3) * 4"
  show(Sum(Prod(Number(2),Number(3)),Number(4))) = "2 * 3 + 4"
   */

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(expr: Expr): String = expr match {
    case Sum(e1, e2) => s"${show(e1)} + ${show(e2)}"
    case Prod(e1, e2) => {
      def showparenthesis(exp: Expr) = exp match {
        case Sum(e1, e2) => s"(${show(exp)})"
        case Prod(e1, e2) => show(exp)
        case Number(n) => show(exp)
      }

      s"${showparenthesis(e1)} * ${showparenthesis(e2)}"
    }
    case Number(n) => s"$n"
  }

  def main(args: Array[String]): Unit = {
    println(description)
    println(greeting)
    println(show(Sum(Sum(Number(2),Number(3)),Number(4))))
    println(show(Prod(Sum(Number(2),Number(3)),Number(4))))
    println(show(Sum(Prod(Number(2),Number(3)),Number(4))))
  }
}
