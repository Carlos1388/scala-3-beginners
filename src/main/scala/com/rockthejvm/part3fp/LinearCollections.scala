package com.rockthejvm.part3fp

import scala.util.Random

object LinearCollections {

  // Sequence (Seq) trait that describes a linear ordered collection = well-defined ordering + indexing
  def testSeq(): Unit = {
    val aSequence = Seq(1, 2, 3, 4)
    // main API: index an element
    val thirdElement = aSequence.apply(2) // element 3
    // map, flatMap, filter, withFilter
    val anIncrementedSeq = aSequence.map(_ + 1)
    val aFlatMapSeq = aSequence.flatMap(x => Seq(x, x + 1))
    val aFilteredSeq = aSequence.filter(_ % 2 == 0)
    // ==> we have for comprehensions
    // other methods
    val reversed = aSequence.reverse
    val concatenation = aSequence ++ Seq(5,6,7) // List(1,2,3,4,5,6,7)
    val sortedSequence = aSequence.sorted // List(1,2,3,4) (same bc is already sorted)
    val sum = aSequence.foldLeft(0)(_ + _) // 10
    val stringRepresentation = aSequence.mkString("[",",","]")


    println(aSequence)
    println(concatenation)
    println(sortedSequence)
    // it will return lists because Lists conform a valid Seq datatype (extends Sequence trait)
  }

  // lists

  def testLists(): Unit = {
    val aList = List(1,2,3)
    // same API as Seq
    // additionally, we have head and tail
    val firstElement = aList.head
    val restElements = aList.tail
    // appending and prepending
    val aBiggerList = 0 +: aList :+ 4 // the +  stands at the side of the elements being added
    val prepending = 0 :: aList // :: is equivalent to Cons in LList
    // utility methods
    val scalax5 = List.fill(5)("Scala")
  }

  // range
  // they don't necessarily have to contain all elements 'inside' it
  def testRanges(): Unit = {
    val aRange: Seq[Int] = 1 to 10 // the 'to' is a method on the Int type that creates the Range data structure
    // the range don't evaluate numbers 1 to 10, to know if smth is inside the range, numbers are not stored in memory
    val anonInclusiveRange = 1 until 10
    // same Seq API
    (1 to 10).foreach(_ => println("Scala"))
  }

  // arrays
  // int[] natural arrays on the JVM
  def testArrays(): Unit = {
    val anArray = Array(1,2,3,4,5,6) // int[] on the JVM
    // most SeqAPIs
    // arrays are NOT Seqs
    val aSequence = anArray.toIndexedSeq // this will be a Seq[Int]

    // every other linear collection is immutable but arrays are modifiable
    anArray.update(2, 99) // Array(1,2,99,4,5,6) in the same object, no new array is allocated
  }

  // vectors = fast Seqs for a large amount of data
  def testVectors(): Unit = {
    val aVector = Vector(1,2,3,4,5,6)
    // the same Seq APIs
  }

  def smallBenchmark(): Unit = {

    val maxRuns = 1000
    val maxCapacity = 1000000

    def getWriteTime(collection: Seq[Int]): Double = {
      val random = new Random()
      val times = for {
        i <- 1 to maxRuns
      } yield {
        val index = random.nextInt(maxCapacity)
        val element = random.nextInt()
        val currentTime = System.nanoTime()
        val updatedCollection = collection.updated(index, element)
        System.nanoTime() - currentTime
      }

      // compute avg
      times.foldLeft(0L)(_ + _) * 1.0 / maxRuns  // 0L is a long, not an integer
      // or times.sum * 1.0 / maxRuns
    }

    val numbersList = (1 to maxCapacity).toList
    val numbersVector = (1 to maxCapacity).toVector

    println(getWriteTime(numbersList))
    println(getWriteTime(numbersVector))
  }


  // sets
  def testSets(): Unit = {
    val aSet = Set(1,2,3,4,5,4,3,4) // no ordering guaranteed
    // equals + hashcode = hashset
    // main API: test if in the set
    val contains3 = aSet.contains(3) // true
    val contains3_v2 = aSet.apply(3) // true
    val contains3_v3 = aSet(3) // true, same meaning as above
    // adding / removing
    val aBiggerSet = aSet + 4 // [1,2,3,4,5]
    val aSmallerSet = aSet - 4 // [1,2,3,5]
    // concatenation
    val anotherSet = Set(4,5,6,7,8)
    val muchBiggerSet = aSet.union(anotherSet)
    val muchBiggerSet_v2 = aSet ++ anotherSet // same
    val muchBiggerSet_v3 = aSet | anotherSet // same
    // difference
    val aDiffSet = aSet.diff(anotherSet)
    val aDiffSet_v2 = aSet -- anotherSet // same
    // intersection
    val anIntersection = aSet.intersect(anotherSet) // [4,5]
    val anIntersection_v2 = aSet & anotherSet // [4,5]




  }


  def main(args: Array[String]): Unit = {
//    testSeq()
//    testRanges()
    smallBenchmark() // 3 orders of magnitude of difference
  }
}
