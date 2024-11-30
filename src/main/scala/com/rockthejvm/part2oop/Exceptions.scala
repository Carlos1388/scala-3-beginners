package com.rockthejvm.part2oop

object Exceptions {

  // Exceptions are specific instances on the same JVM, not just in java
  // exceptions crash the application

  val aString: String = null
  // although is null, in theory we can access String methods, since it is String type


  // 1 - how do we throw exceptions ourselves??
  // 2 - how do we recover from errors???

  // 1 - ................. - ........................
//  val aWeirdValue: Int = throw new NullPointerException // every instance from the type 'Throwable' is throwable. Under Throwable we have Error e.g. StackOverflow or OOM
  // Under Throwable we also have Exception == smth wrong with your program
  // Every exception crashes your program unless it iss catched.
  // An exception returns Nothing, no matter what's on the left

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    // code that might fail
    getInt(true) // an Int
  } catch {
    // which exceptions I might get, ordered in a special structure
    case e: RuntimeException => 54 // also an Int, but another cases could be another type. The compiler will infer type of total expression (try + catch)
    // as the lower common ancestor of the possible types (e.g. Int + String => Any)
    case e: NullPointerException => 35 // the match in the catch part will be in order
    //  so we have to be careful about the subtypes of Exception, for example a NPE is also a RE, so we should place more specific exceptions first
  } finally {
    // executed no matter what
    // usually used to close resources, e.g. close files even when code fails
    // returns Unit
  }

  // own exceptions
  class MyException extends RuntimeException {
//    fields or methods
    override def getMessage: String = "My exception"
  }

  // we can instantiate
  val myException = new MyException
//  val throwingMyException = throw new MyException


  /**
   * Exercises
   * 1 - Try to crash with StackOverflow error
   * 2 - Try to crash with OutOfMemory error
   * 3 - Find an elements matching a predicate in LList
   */

  // 1
  def SOCrash(): Unit = {
    def infinite(): Int = {
      1 + infinite()
    }

    infinite()
  }

  def OOMCrash(): Unit = {
    def bigNumber(n: Int, acc: String): String = {
      if (n == 0) acc
      else bigNumber(n - 1, acc + acc)
    }

    bigNumber(200000000, "Scala")
  }


  def main(args: Array[String]): Unit = {
//    println(aString.length) //Exception in thread "main" java.lang.NullPointerException
//    println(potentialFail) // 54 because NPE is RE, if NPE is before RE, then will print 35
//    println(SOCrash()) // crashed with stack overflow
    println(OOMCrash())
  }

}
