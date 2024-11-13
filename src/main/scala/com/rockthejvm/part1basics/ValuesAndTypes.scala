package com.rockthejvm.part1basics

object ValuesAndTypes {

  // values
  val meaningOfLife: Int = 42

  // assigning --> is not allowed
  //meaningOfLife = 45 // illegal

  val anInteger = 67 // : Int is optional (X-rays gives hints based on compiler)

  // common types - essential types
  val aBooleanValueF: Boolean = false
  val aBooleanValueT: Boolean = true

  val aChar: Char = 'a' // single quotes

  val anInt: Int =78 // 32 bit == 4 bytes
  val aShorts: Short = 5367 // 16 bit == 2 bytes
  val aLong: Long = 5278477878728974L // L for long number, 64 bits == 8 bytes
  val aFloat: Float = 2.4f // 4 bytes fp
  val aDouble: Double = 3.14 // any suffix at the end, is 8 bytes fp

  val aString: String = "Double Quotes" // single quotes != double quotes

  def main(args: Array[String]): Unit = {

  }

}
