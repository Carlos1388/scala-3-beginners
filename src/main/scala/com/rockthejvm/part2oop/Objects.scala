package com.rockthejvm.part2oop

object Objects {

  // objects are singleton patterns in one line
  object MySingleton { // type + the only instance of this type
    val aField = 45
    def aMethod(x: Int) = x +1

  }

  val theSingleton = MySingleton
  val anotherSingleton = MySingleton

  val theSingletonField = MySingleton.aField
  val theSingletonMethod = MySingleton.aMethod(99)

  // an object as Singletons are not the same as objects in OOP (instances of classes) !!!!

  // other properties of objects as singletons
  class Person(name: String) {
    def sayHi(): String = s"Hi, my name is $name"
  }

  // companion object of the class Person. In the class Person we define values and methods dependant of the instance whereas
  // in the companion object we define methods and values non-dependant of the particular instance
  object Person {
    val N_EYES = 2
    def canFly(): Boolean = false
  }

  // the presence of companions have the property that both the class and object can access each other private methods and values
  val mary = new Person("Mary")
  val marysGreeting = mary.sayHi()

  // we can use the object method for methods non-dependant of instance ("static")
  val humansCanFly = Person.canFly()
  val nEyesHumans = Person.N_EYES

  // equality
  // 1 - equality of reference == two instances are equal if it points to the same instances of classes in memory
  //      equality of reference can be tested with 'eq' ( == in Java)
  val maryv2 = new Person("Mary")
  val sameMary = mary eq maryv2 // false
  val sameSingleton = theSingleton eq anotherSingleton // true

  // 2 - equality of "sameness", definition to decide by the programmer, usually as equality of value (.equals in Java)
  // this method is of the class and can be overwritten
  val sameMaryv2 = mary equals maryv2 // false because not the same instance and is not overwritten
  val sameMaryv3 = mary == maryv2 // same as equals, it will return false
  val sameSingletonv2 = theSingleton == MySingleton // true


  // objects can also extend classes
  object BigFoot extends Person("Big Foot") // you can have an UNIQUE object extending classes, abstract classes or traits


  // what a Scala application actually is???
  // Scala application = object + def main(args: Array[String]): Unit
  // we define the class and its only instance, with a main as a public static void method with a list of arguments as args
  // in Java this particular file will have the structure:

  /**
   * public class Objects {
   *  public static void main(String[] args) { ... }
   *  }
   */


  def main(args: Array[String]): Unit = {

    println(theSingleton == anotherSingleton) // true, they're just the same instance
    println(sameMaryv2)
    println(sameMaryv3)

  }
}
