package com.rockthejvm.part2oop

object Enums {


    enum Permissions {
      case READ, WRITE, EXECUTE, NONE
      // add fields/methods
      def openDocument(): Unit =
        if (this == READ) println("opening document...")
        else print("reading not allowed")
    }


  val somePersmissions: Permissions = Permissions.READ

  // constructor args
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  // companion objects for enum like on every other class
  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = // whatever
      PermissionsWithBits.NONE
  }

  // standard API of enums (built-in on every enum)
  val somePermisionsOrdinal = somePersmissions.ordinal // index in which this instance occurs in the enum definition
  // 0 -> READ
  // 1- WRITE ... etc
  val allPermisions = PermissionsWithBits.values // array of all possible values of the enum
  // create instance from string
  val readPermission: Permissions = Permissions.valueOf("READ") // Permissions.READ

  def main(args: Array[String]): Unit = {
    somePersmissions.openDocument()
  }
}
