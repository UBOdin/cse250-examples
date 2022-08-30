package cse250.examples

import scala.collection.mutable
import cse250.util.Timing.time

object Aggregate
{
  val SALES = 100000

  def groupByCount(data: Seq[SaleRecord]): Map[Int, Int] = 
  {
    val result = mutable.HashMap[Int, Int]()
    for(record <- data){
      if(result.contains(record.productId)){
        result(record.productId) += 1
      } else {
        result(record.productId) = 1
      }
    }
    return result.toMap
  }

  def groupBySum(data: Seq[SaleRecord]): Map[Int, Int] = 
  {
    val result = mutable.HashMap[Int, Int]()
    for(record <- data){
      if(result.contains(record.productId)){
        result(record.productId) += record.quantity
      } else {
        result(record.productId) = record.quantity
      }
    }
    return result.toMap
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