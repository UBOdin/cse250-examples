package cse250.examples
/**
 * cse250.examples.DataStructureChoice.scala
 *
 * Copyright 2021 Oliver Kennedy (okennedy@buffalo.edu)
 *           2021 Andrew Hughes (ahughes6@buffalo.edu)
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 *
 */

import scala.util.Random

object DataStructureChoice {
  val SIZE = 100000;

  def main(args: Array[String]): Unit =
    {
      val elements = List.range(0, SIZE)
      var total = 0l
      for(i <- 1 until 10000){
        total = total + elements(Random.nextInt(SIZE))

      }
      println(s"Total: $total")
    }
}
