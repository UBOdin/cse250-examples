package cse250.examples
/**
 * cse250.examples.BreakItDownSpec.scala
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

import org.scalatest.flatspec.AnyFlatSpec

class BreakItDownSpec extends AnyFlatSpec
{
  "BreakItDown" should "count 38 baked goods" in {
    val data: Seq[BreakItDown.FarmersMarket] =
      BreakItDown
        .readData("AMS-USDA-Directories-FarmersMarkets.csv")
       .drop(1)                        // drop the header
        .map { BreakItDown.parseFarmersMarket(_) }  // parse records

    assert(data.size == 87, s"Got ${data.size} rows instead")
    assert(BreakItDown.countTheBakedGoods(data) == 75, "The right number of baked goods")
  }

}
