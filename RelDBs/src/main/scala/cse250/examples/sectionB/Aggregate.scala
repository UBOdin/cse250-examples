package cse250.examples.sectionB

import scala.collection.mutable
import cse250.util.Timing.time
import cse250.examples.{ DataGenerator, SaleRecord }

object Aggregate
{
  val SALES = 10000000
  
  def groupByCount(data: Seq[SaleRecord]): Map[Int, Int] = 
  {
    val accumulator = mutable.HashMap[Int, Int]()
    for(s <- data){
      accumulator(s.productId) = 1 + accumulator.getOrElse(s.productId, 0)
    }
    accumulator.toMap
  }

  def groupBySum(data: Seq[SaleRecord]): Map[Int, Int] = 
  {
    val accumulator = mutable.HashMap[Int, Int]()
    for(s <- data){
      accumulator(s.productId) = 
        s.quantity + accumulator.getOrElse(s.productId, 0)
    }
    accumulator.toMap
  }

  def resultString(result: Map[Int, Int]) = 
    result.toSeq.sortBy { _._1 }.map { x => s"${x._1} -> ${x._2}" }.mkString("\n")

  def main(args: Array[String]): Unit = 
  {
    {
      val data = DataGenerator.sales(SALES)
      println("\n--- Timing ---")
      val counts: Map[Int, Int] = 
        try { time("Count Time") { groupByCount(data) } }
        catch { case _:NotImplementedError => println("Count not implemented"); Map.empty }
      val sums: Map[Int, Int] =
        try { time("Sum Time") { groupBySum(data) } }
        catch { case _:NotImplementedError => println("Sum not implemented"); Map.empty }

      println(s"\n\n--- Times Each Product Was Sold ---\n${resultString(counts)}")
      println(s"\n\n--- Number of Sales Per Product ---\n${resultString(sums)}")
    }
  }

}