package com.rockthejvm.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure {
  // Try = a potentially failed computation
  // Try also "a collection with at most one value"
  val aTry: Try[Int] = Try(43)
  val aFailedTry: Try[Int] = Try(throw new RuntimeException) // this doesn't crash the application because Try.apply() is called
  // by name, what means it is not evaluated until last moment

  // subtypes of Try: Successes and Failures (alternative construction)
  val aTry_v2: Try[Int] = Success(42)
  val aTry_v3: Try[Int] = Failure(new RuntimeException)

  // main API
  val checkSuccess = aTry.isSuccess
  val checkFailure = aTry.isFailure
  val aChain = aFailedTry.orElse(aTry) // to try a backup Try in case another Try fails

  // map, flatMap, filter ==> for-comprehensions
  val anIncrementedTry = aTry.map(_ + 1) // if failure in aTry or in the mapping function, then all failure, else apply function to value
  val aFlatMappedTry = aTry.flatMap(mol => Try(s"My Meaning of life is $mol")) // to create new tries from successes
  val aFilteredTry = aTry.filter(_ % 2 == 0) // if try is successful, then return success value (in ths case Success(42)), if try fails
  // then that fail is kept, but if predicate doesn't match in a success, it fails with 'NoSuchElement exception'

  // WHY USE: To avoid unsafe API that can throw exceptions
  def unsafeMethod(): String =
    throw new RuntimeException("No string for you")

  // defensive try-catch-finally
  val stringLengthDefensive = try {
    val aString = unsafeMethod()
    aString.length
  } catch {
    case e: RuntimeException => -1
  }

  // this can become hard to read, debug and probably doesn't cover all edge cases
  //
  // alternative:
  // purely functional
  val stringLengthPure = Try(unsafeMethod()).map(_.length).getOrElse(-1)

  // DESIGN
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException(("No string for you")))
  def betterBackupMethod(): Try[String] = Success("Scala")
  val stringLengthPure_v2 = betterUnsafeMethod().map(_.length)
  val aSafeChain = betterUnsafeMethod().orElse(betterBackupMethod()).map(_.length)

  /**
   * Exercise
   *  obtain a connection
   *  then fetch the url
   *  print the resulting HTML
   *
   */

  val host = "localhost"
  val port = "8080"
  val myDesiredURL = "rockthejvm.com/Home"

  class Connection {
    val random = new Random()

    def get(url: String): String = {
      if (random.nextBoolean()) "<html>Success</html>"
      else throw new RuntimeException("Cannot fetch page right now.")
    }

    def getSafe(url: String): Try[String] =
      Try(get(url))
  }

  object HttpService {
    val random = new Random()

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Cannot access to host/port combination")
    }
    def getSafeConnection(host: String, port: String): Try[Connection] = {
      Try(getConnection(host, port))
  }}



  def main(args: Array[String]): Unit = {

        // defensive style
        val connection = try {
          val httpservice = HttpService.getConnection(host, port)
          val httpage = try {
            httpservice.get(myDesiredURL)
          } catch {
          case e: RuntimeException => s"<html>${e.getMessage}</html>"
              }
        } catch {
            case e: RuntimeException => s"<html>${e.getMessage}</html>"
          }

    // functional style
    val maybeConnection = Try(HttpService.getConnection(host, port))
    val maybeHtml = maybeConnection.flatMap(conn => Try(conn.get(myDesiredURL)))
    val finalResult = maybeHtml.fold(e => s"<html>${e.getMessage}</html>", s => s)

    println(finalResult)


    // using the Safe Methods and for-comprehensions
    val maybeConnection_v2 = for {
    conn <- HttpService.getSafeConnection(host, port)
    html <- conn.getSafe(myDesiredURL)
    } yield html

    println(maybeConnection_v2)
  }
}
