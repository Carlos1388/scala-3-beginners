package com.rockthejvm.part3fp

object TuplesMaps {

  // tuples = finite ordered "lists" / group of values under a same value
  val aTuple = (2, "jvm") // Tuple2[Int, String] == (Int, String)
  val firstField = aTuple._1
  val secondField = aTuple._2
  val aCopiedTuple = aTuple.copy(_1 = 54)

  // tuples of 2 elements
  val aTuple_v2 = 2 -> "jvm"  // this is IDENTICAL to (2, "jvm")

  // maps: data structures that make associations between keys and values
  val aMap = Map()

  val phonebook: Map[String, Int] = Map(
    "Jim" -> 555,
    "Daniel" -> 789,
    "Jane" -> 123
  ).withDefaultValue(-1)

  // core APIs
  val phonebookhasDaniel = phonebook.contains("Daniel")
  val marysPhoneNumber = phonebook.apply("Mary") // crash with exception if key is not in the map and there's not a default value

  // modifying maps
  // add a pair of key-value
  val newPair = "Mary" -> 567
  val newphonebook = phonebook + newPair // returns a new map

  // remove a key
  val phonebookwithoutDaniel = phonebook - "Daniel" // returns a new map

  // turning a map into linear collection and vice versa
  val linearphonebook = List(
    "Jim" -> 555,
    "Daniel" -> 789,
    "Jane" -> 123
  )

  val phonebook_v2 = linearphonebook.toMap

  val linearphonebook_v2 = phonebook.toList // toSeq, toVector, toArray, to everything else that extends from Seq

  // map, flatMap, filter are also available for maps
  /*
  Map("Jim" -> 123, "JIM" -> 999), when using map function here, you can't really tell which key associates to what value
                                    because there's no difference and no because there is no order in maps
  when doing this you would obtain Map("JIM" -> ??? we don't know ???)
   */
  val aProcessedPhonebook = phonebook.map(pair => (pair._1.toUpperCase(), pair._2))

  // filtering keys
  val noJphonebook = phonebook.view.filterKeys(!_.startsWith("J")).toMap

  // most common use case is transforming values
  val prefixNumbers = phonebook.view.mapValues(number => s"0255-$number").toMap

  // many linear collections have methods to create maps
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  val nameGroupings = names.groupBy(name => name.charAt(0)) // Map[Char, List[String]]
  // that associates first character to the list of names



  def main(args: Array[String]): Unit = {
    println(phonebook)
    println(phonebookhasDaniel)
    println(marysPhoneNumber)

    println(nameGroupings)

  }
}
