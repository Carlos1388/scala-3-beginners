package com.rockthejvm.practice

import scala.annotation.tailrec
import scala.language.postfixOps

object TuplesMapsExercises {

  /**
   * Social network = Map[String, Set[String]]
   * Friend relationships are MUTUAL
   *
   * - add a person to the network
   * - remove a person from the network
   * - add friend relationship (network, a, b) adds a to b and b to a
   * - unfriend
   *
   * - number of friends of a person
   * - who has the most friends
   * - how many people have no friends
   * + if there is a social connection between two people (direct or not)
   *
   * Daniel <-> Mary <-> Jane <-> Tom   ==> returns true for "Daniel" and "Tom" bc there is connection between them
   */

  val socialnetwork: Map[String, Set[String]] = Map().withDefaultValue(Set())


  def addPerson(network: Map[String, Set[String]], newPerson: String): Map[String, Set[String]] = {
    // check if person already exists
    if (network.contains(newPerson)) network
    else network + (newPerson -> Set())
  }

  def removePerson(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
      val newnetwork = network.view.filterKeys(!_.==(person)).toMap
      newnetwork.map(pers => pers._1 -> (pers._2 - person) )

  def addFriend(network: Map[String, Set[String]], person1: String, person2: String): Map[String, Set[String]] = {
    addPerson(network, person1)
    addPerson(network, person2)
    val newpair1 = person1 -> (network(person1) + person2)
    val newpair2 = person2 -> (network(person2) + person1)
    network + newpair1 + newpair2
  }

  def removeFriend(network: Map[String, Set[String]], person1: String, person2: String): Map[String, Set[String]] = {
    val newpair1 = person1 -> (network(person1) - person2)
    val newpair2 = person2 -> (network(person2) - person1)
    if (network.contains(person1) && network.contains(person2)) network + newpair1 + newpair2
    else network
  }

  def countfriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) -1
    else network(person).size
  }

  def mostfriends(network: Map[String, Set[String]]): String = {
    network.map(person => (person._1 -> countfriends(network, person._1))).toSeq.sortBy(_._2).reverse.head._1
  }

  // solution by Daniel:
  /*
  def mostFriends(network: Map[String, Set[String]]): String =
    if (network.isEmpty) throw new RuntimeException("Network is empty, no-one with most friends")
    else {
      val best = network.foldLeft(("", -1)) { (currentBest, newAssociation) =>
        // code block
        val currentMostPopularPerson = currentBest._1
        val mostFriendsSoFar = currentBest._2

        val newPerson = newAssociation._1
        val newPersonFriends = newAssociation._2.size

        if (mostFriendsSoFar < newPersonFriends) (newPerson, newPersonFriends)
        else currentBest
      }

      best._1
    }

   */

  def nofriends(network: Map[String, Set[String]]): Int = {
    network.view.count(person => countfriends(network, person._1) == 0)
    // or network.count(person => person._2.isEmpty)
  }

  def socialConnection(network: Map[String, Set[String]], person1: String, person2: String): Boolean = {
    @tailrec
    def search(discovered: Set[String], considered: Set[String]): Boolean = {
      if (discovered.isEmpty) false
      else {
        val person = discovered.head
        val personsFriends = network(person)

        if (personsFriends.contains(person2)) true
        else search(discovered - person ++ personsFriends -- considered, considered + person)
      }
    }

    if (!network.contains(person1) || !network.contains(person2)) false
    else search(network(person1), Set(person1))
  }


  def main(args: Array[String]): Unit = {
    val socialnetwork_v1 = addPerson(socialnetwork, "Carlos")
//    println(socialnetwork_v1)
    val socialnetwork_v2 = addFriend(socialnetwork_v1, "Carlos", "Daniela")
//    println(socialnetwork_v2)
////    val socialnetwork_v3 = removePerson(socialnetwork_v2, "Daniela")
////    println(socialnetwork_v3)
//    val socialnetwork_v4 = removeFriend(socialnetwork_v2, "Carlos", "Daniela")
//    println(socialnetwork_v4)
//    val socialnetwork_v5 = removeFriend(socialnetwork_v1, "Carlos", "Daniela")
//    println(socialnetwork_v5)

//    println(countfriends(socialnetwork_v2, "Carlos"))

    val socialnetwork_v6 = addFriend(addFriend(socialnetwork_v2, "Daniela", "Juanma"), "Juanma", "Miguel")
//    println(mostfriends(socialnetwork_v6))
//    println(socialnetwork_v6)
//    println(nofriends(socialnetwork_v4))

    println(socialConnection(socialnetwork_v6, "Carlos", "Miguel"))
  }
}
