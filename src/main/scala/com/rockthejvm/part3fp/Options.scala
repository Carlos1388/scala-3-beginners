package com.rockthejvm.part3fp

import scala.util.Random

object Options {

  // options = "collections" with at most one value
  val anOption: Option[Int] = Option(42)
  val anemptyOption: Option[Int] = Option.empty

  // REASON: work with unsafe APIs, option is a potential absence of a value
  // wrapping our values inside this structures will protect us from nulls
  def unsafe_method(): String = null

  // defensive style
  val stringLength = {
    val potential_null = unsafe_method()
    if (potential_null == null) -1
    else potential_null.length
  }

  // but when we have more than 1 value, this stringLength will be very difficult to read, and we will always miss an edge case to cover
  // the alternative is options

  // option-style: no need for null checks
  val stringLengthOption = Option(unsafe_method()).map(_.length)

  // subtypes of Option

  val aPresentValue: Option[Int] = Some(5) // extends Option
  val anemptyOption_v2: Option[Int] = None  // extends Option, but better to use Option(5) and Option.empty

  // functionalities of options

  // "standard" API
  val isempty = anOption.isEmpty
  val innerValue = anOption.getOrElse(90)
  val anotherOption = Option(46)
  val aChainedOption = anemptyOption.orElse(anotherOption) // if first is empty, returns second one, if second one is also empty, returns None

  // map, flatMap, filter, for
  val anIncrementedOption = anOption.map(_ + 1) // Some(43)
  val afilteredOption = anIncrementedOption.filter(_ % 2 == 0) // None
  val flatmappedOption = anOption.flatMap(value => Option(value * 10)) // Some(420)



  // use-case for orElse
  def unsafemethod(): String = null
  def fallbackmethod(): String = "valid result"

  val someResult = Option(unsafemethod()).orElse(Option(fallbackmethod()))

  // DESIGN
  // construct functions to return Options, it takes out the possibility of returning nulls
  def betterUnsafeMethod(): Option[String] = None
  def betterFallbackMethod(): Option[String] = Some("valid result")
  val betterChain = betterUnsafeMethod().orElse(betterFallbackMethod())

  // example: Map.get
  val phonebook = Map(
    "Daniel" -> 1234
  )
  val marrysPhoneNumber = phonebook.get("Mary") // returns an option
  // not need to crash, checks for nulls or if Mary is present in the map

  /**
   * Exercise
   *
   * - get the host and port from the config map
   *  try to open a connection
   *  print the connection successful string or "conn failed"
   */

  val config: Map[String, String] = Map(
    // comes from elsewhere
    "host" -> "176.45.32.1",
    "port" -> "8080"
  )

  class Connection {
    def connect(): String = "Connection successful"
  }

  object Connection {
    val random = new Random()

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  def main(args: Array[String]): Unit = {
//    println(someResult)
    val host = config.get("host")
    val port = config.get("port")
    val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
    val connectionStatus = connection.map(_.connect())

    println(connectionStatus.getOrElse("Failed to establish a connection"))

    // compact
    val connectionStatus_v2 =
      config.get("host").flatMap(h =>
        config.get("port").flatMap(p =>
          Connection(h, p).map(_.connect())
        )
      )

    println(connectionStatus_v2.getOrElse("Failed to establish a connection"))

    // for-comprehension
    val connectionStatus_v3 = for {
      h <- config.get("host")
      p <- config.get("port")
      conn <- Connection(h, p)
    } yield conn.connect()

    println(connectionStatus_v3.getOrElse("Failed to establish a connection"))


  }
}
