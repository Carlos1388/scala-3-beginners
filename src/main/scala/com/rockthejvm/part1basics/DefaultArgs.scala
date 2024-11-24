package com.rockthejvm.part1basics

import scala.annotation.tailrec

object DefaultArgs {

  @tailrec
  def sumUntilTailrec(x: Int, accumulator: Int = 0): Int = // default value of accumulator is 0
    if (x <= 0) accumulator
    else sumUntilTailrec(x - 1, accumulator + x)

  val sumUntil100 = sumUntilTailrec(100) // additional arg passed automatically

  def savePicture(dirPath: String, name: String, format: String, width: Int, height: Int): Unit =
    println("Saving Picture in format " + format + " in path " + dirPath)

  // if most of the time this function is called using format jpg and 1920*1080 is convenient to declare args with default values
  def savePicture_2(dirPath: String, name: String, format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit =
    println("Saving Picture in format " + format + " in path " + dirPath)


  def main(args: Array[String]): Unit = {
    savePicture_2("/user/carlos/photos", "myphoto") //you don't need to specify anything else
    // when passing some vealues and leaving default arguments, name the argument for the compiler to know which args we are referring
  }
}
