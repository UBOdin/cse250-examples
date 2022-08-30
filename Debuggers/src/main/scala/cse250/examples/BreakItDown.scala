package cse250.examples
/**
 * cse250.examples.BreakItDown.scala
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

import java.net.URL
import scala.io.Source

object BreakItDown {

  class FarmersMarket(
    val name: String,
    val lat: Int,
    val lon: Int,
    val website: URL,
    val sellsBakedGoods: Boolean
  )

  def readData(file: String): Seq[String] =
    {
      val lines:Seq[String] =
        Source.fromFile(file)
          .getLines
          .toSeq

      // Drop the header from the file
      return lines.drop(1)
    }

  def parseFarmersMarket(input: String): FarmersMarket =
    {
      val columns = input.split(",")
      new FarmersMarket(
        columns(1),
        columns(18).toInt,
        columns(19).toInt,
        new URL(columns(2)),
        columns(27) match {
          case "Y" => true
          case "N" => false
        }
      )
    }

  def countTheBakedGoods(markets: Seq[FarmersMarket]): Int =
    {
      var count = 0;
      for(market <- markets) {
        if(market.sellsBakedGoods){ count += 1 }
      }
      return count
    }

  def main(args: Array[String]): Unit =
    {
      val data: Seq[FarmersMarket] =
        readData("AMS-USDA-Directories-FarmersMarkets.csv")
          .drop(1)                        // drop the header
          .map { parseFarmersMarket(_) }  // parse records

      print(s"${countTheBakedGoods(data)} markets sell baked goods")
      // Should print "75 markets sell baked goods"
    }

}
