package com.rockthejvm.part1basics

object StringOps {

  val aString: String = "Hello, learning Scala"

  // string functions (methods)
  val secondChar = aString.charAt(1)
  val firstWord = aString.substring(0, 5) // all characters between 0 and 5 (excluded 5 included 0)
  val words = aString.split(" ") // Array("Hello,", "learning", "Scala")
  val startsWithHello = aString.startsWith("Hello") // true
  val allDashes = aString.replace(' ', '-')
  val allUppercase = aString.toUpperCase() // also toLowerCase
  val nChars = aString.length

  // other functions
  val reversed = aString.reverse
  val abunchofchars = aString.take(10) // 10 first characters

  //parse to numeric
  val numberAsString = "2"
  val number = numberAsString.toInt // toFloat, toBoolean, etc

  // most powerful feature, interpolation
  val name = "Alice"
  val age = 12
  val greeting = "Hello, I'm " + name + " and I am " + age + " years old."
  val greeting_v2 = s"Hello, I'm $name and I'm $age years old" // more readable, this is interpolation
  // you can inject whole expressions
  val greeting_v3 = s"Hello, I'm $name and I'm ${age + 1} years old"
  // f interpolation (f == format)
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.4f burgers per minute" // %2.2f specifies 2 digits with 2 decimal precissions
  // raw interpolation ignores escapes
  val escapes = "This is a \n newline"
  val escapes_raw = raw"This is a \n newline"

  // we can also create custom interpolations (in the future..)

  def main(args: Array[String]): Unit = {
    println(myth)
    println(escapes)
    println(escapes_raw)
  }
}
