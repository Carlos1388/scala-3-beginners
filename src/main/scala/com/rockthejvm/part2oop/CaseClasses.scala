package com.rockthejvm.part2oop

object CaseClasses {

  case class Person(name: String, age: Int) { // when defined as case class, there are some properties
   // do things // this is possible
  }
  // 1 - class args are fields
  val daniel = Person("Daniel", 99)
  val danielsAge = daniel.age

  // 2 - special to Strings + equals and hashcode
  val danieltostring = daniel.toString // Person(Daniel, 99)
  val danielDuped = new Person("Daniel", 99)
  val isSameDaniel = daniel == danielDuped // true because we have a case class, if we didn't == is implemented in terms of reference equality
  // hashcode is implemented conveniently in terms of arguments of the class

  // case classes are for the case when we want to store data, compare, etc. Not for large pieces of code

  // 3 - utility methods
  val danielYounger = daniel.copy(age=75) // new Person, everything is constant except for the args we pass
  // other advanced utility methods

  // 4 - CC's have companions objects
  val thePersonSingleton = Person
  val daniel_v2 = Person.apply("Daniel", 99) // or Person("Daniel", 99). The apply on the singleton works as a constructor

  // 5 - CC's are serializable --> we can send serialized things 'over the wire' for another jvm (for distributed applications) e.g. Akka


  // 6 - CC's have extractor patters for PATTERN MATCHING

  // // Case classes capabilities and restrictions
  // case classes must have arguments! because if we had it, every instance would be the same, it makes no sense,
  // but it exists case objects

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  // we can define a key class with arguments but no arglist
  case class CCWithArgListNoArgs() // legal but makes sense in the context of generics (CCWithArgListNoArgs[A]())
  val ccna = new CCWithArgListNoArgs()
  val ccna_v2 = new CCWithArgListNoArgs()

  /**
   * Exercise: use cclasses for LList
   */

  def main(args: Array[String]): Unit = {

//    println(daniel)
    println(isSameDaniel)


  }
}
