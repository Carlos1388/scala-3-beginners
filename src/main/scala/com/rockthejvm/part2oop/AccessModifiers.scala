package com.rockthejvm.part2oop

object AccessModifiers {

  //
  class Person(val name: String) {
    def sayHi(): String = s"Hi, My name is $name"
    protected def prsayHi(): String = s"Hi, my name is $name"
    private def watchNetflix(): String = s"I'm watching my fav series"  // only callable within Person class, not by child classes like Child class
  }

  val aPerson = new Person("Alice")


  class Kid(override val name: String, age: Int) extends Person(name) {  // you can override the constructor args
    def greetPolitely(): String = // no modifier == public
      prsayHi() + "I love to play" // protected method is callable
  }

  val aKid = new Kid("David", 5)

  // complication
  class KidWithParents(override val name: String, age: Int, momName: String, dadName: String) extends Person(name) {
    val mom = new Person(momName)
    val dad = new Person(dadName)

    //def everyoneSayHi(): String =
      //s"Hi, my name is $name, and here are my parents: " + mom.prsayHi() + dad.prsayHi()  //this is illegal
      // even though mom and dad are instances of person and Kid extends person,l I cannot call prsayHi() (protected)
      // on any other instance outside those. From the perspective of mom and dad instances, this code  (everyoneSayHi) is
      // public code, it is code outside them
  }

  def main(args: Array[String]): Unit = {

    println(aPerson.sayHi())
    // i cant call a protected method, it can only be called from inside the class or children classes
    //println(aPerson.prsayHi()) // illegal
    println(aKid.greetPolitely())

  }
}
