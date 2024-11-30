package com.rockthejvm.part2oop

val meaning_of_life = 42

def computeMyLife = "Scala42"

// the scala compiler creates synthetic object known as the name of the package
// and that object will wrap these two definitions above
// so when we import com.rockthejvm.part2oop.* we import these two definitions or whatever we define (we can also define methods)
// these are like global variables. But it is not recommended


object PackagesImports { // top-level

  // packages =  "folders" in fact usually are the same

  // fully qualified name
  val aList: com.rockthejvm.practice.LList[Int] = ??? // throws a NonImplementedError

  // alternatively we can simply import the symbol
  import com.rockthejvm.practice.LList // we can add the import state anywere
  val anotherList: LList[Int] = ???

  // importing under an alias
  import java.util.{List as JList} // in Scala2 "as" -> "=>"
  val aJavaList: JList[Int] = ???

  // import everything
  import com.rockthejvm.practice.*
  val aPredicate: Predicate[Int] = ???

  // we can import several symbols in the same line
  import PhysicsConstants.{PLANCK, SPEED_OF_LIGHT, EARTH_GRAVITY}
  val c = SPEED_OF_LIGHT

  // import everything BUT a symbol
  object PlayingPhysics {
    import PhysicsConstants.{PLANCK as _, *} // everything but PLANCK
//    val planck = PLANCK // not available, error
    val c = SPEED_OF_LIGHT // will work
  }

  // default imports
  // scala.*, scala.Predef.*, java.lang.*,... and other not-as-important packages

  // exports
  // Scala3 introduces the concept of exports
  // these are alias for field or methods from an enclosing instance
  class PhysicsCalculator {
    import PhysicsConstants.*
    def computePhotonEnergy(wavelenght: Double): Double = {
      PLANCK / wavelenght
    }
  }

  object ScienceApp {
    val physicsCalculator = new PhysicsCalculator // an object with another object or class as a field

    export physicsCalculator.computePhotonEnergy // we take the method computePhotonEnergy out from physicsCalculator object,
    // and we put it in the scope of ScienceApp, so now we can use computePhotonEnergy as a free method in ScienceApp

    def computeEquivalentMass(wavelenght:  Double): Double = {
      computePhotonEnergy(wavelenght) / (SPEED_OF_LIGHT * SPEED_OF_LIGHT) // without having to do physicsCalculator.computePhotonEnergy(wave...
    }

  }


  def main(args: Array[String]): Unit = {

  }
}

object PhysicsConstants {
  // constants / utils / other things are usually stored under an object
  val SPEED_OF_LIGHT = 299792458
  val PLANCK = 6.62e-34 // scientific notation
  val EARTH_GRAVITY = 9.8
}