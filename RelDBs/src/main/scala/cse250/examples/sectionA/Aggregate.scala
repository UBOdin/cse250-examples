package cse250.examples.sectionA

import scala.collection.mutable
import cse250.util.Timing.time
import cse250.examples.{ DataGenerator, SaleRecord }

object Aggregate
{
  val SALES = 1000000

  def groupByCount(data: Seq[SaleRecord]): Map[Int, Int] = 
  {
    val aggregate = mutable.HashMap[Int, Int]()
    for(s <- data){
      aggregate(s.productId) = 
        1 + aggregate.getOrElse(s.productId, 0)
    }
    return aggregate.toMap
  }

  def groupBySum(data: Seq[SaleRecord]): Map[Int, Int] = 
  {
    val aggregate = mutable.HashMap[Int, Int]()
    for(s <- data){
      aggregate(s.productId) = 
        s.quantity + aggregate.getOrElse(s.productId, 0)
    }
    return aggregate.toMap
  }

  def resultString(result: Map[Int, Int]) = 
    result.toSeq.sortBy { _._1 }.map { x => s"${x._1} -> ${x._2}" }.mkString("\n")

  def main(args: Array[String]): Unit = 
  {
    {
      val data = DataGenerator.sales(SALES)
      println("\n--- Timing ---")
      val counts = time("Count Time") { groupByCount(data) }
      val sums = time("Sum Time") { groupBySum(data) }

      println(s"\n\n--- Times the Product Was Sold ---\n${resultString(counts)}")
      println(s"\n\n--- Number of Sales Per Product ---\n${resultString(sums)}")
    }
  }

}