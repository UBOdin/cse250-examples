package cse250.examples.sectionB

import scala.collection.mutable
import cse250.util.Timing.{ time, withTimeout }
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeoutException
import cse250.examples.{ SaleRecord, ProductPrice, DataGenerator }

object Join
{
  val NUM_PRODUCTS = Seq(100, 500, 1000, 5000, 10000, 50000, 100000)
  val SALES_PER_PRODUCT = 10

  def nestedLoopJoin(sales: Seq[SaleRecord], prices: Seq[ProductPrice]): mutable.Buffer[(SaleRecord, ProductPrice)] = 
  {
    val result = mutable.Buffer[(SaleRecord, ProductPrice)]()

    for(s <- sales){
      for(p <- prices){
        if(s.productId == p.productId){ result += ( (s, p) ) }
      }
    }

    result
  }
  
  def sortMergeJoin(sales: Seq[SaleRecord], prices: Seq[ProductPrice]): mutable.Buffer[(SaleRecord, ProductPrice)] =
  {
    val result = mutable.Buffer[(SaleRecord, ProductPrice)]()

    ???
    
    result
  }

  def hashJoin(sales: Seq[SaleRecord], prices: Seq[ProductPrice]): mutable.Buffer[(SaleRecord, ProductPrice)] =
  {
    val result = mutable.Buffer[(SaleRecord, ProductPrice)]()

    val priceTable = mutable.HashMap[Int, ProductPrice]()
    for(p <- prices){
      priceTable(p.productId) = p
    }

    for(s <- sales){
      result += ( (s, priceTable(s.productId)) )
    }
    
    result
  }

  def main(args: Array[String]): Unit = 
  {
    for( productCount <- NUM_PRODUCTS ){
      println(s" --- $productCount Products --- ")
      val sales = DataGenerator.sales(elements = productCount * SALES_PER_PRODUCT, keyRange = productCount)
      val prices = DataGenerator.prices(productCount)
    
      def trial(label: String, fn: (Seq[SaleRecord], Seq[ProductPrice]) => mutable.Buffer[(SaleRecord, ProductPrice)]): Unit =
      {
        try {
          time(label) { 
            withTimeout(1) {
              fn(sales, prices) 
            }
          }
        } catch { 
          case x:ExecutionException => 
            x.getCause match {
              case _:NotImplementedError => println(s"$label: Not Implemented")
            }
          case t:TimeoutException => println(s"$label: TIMEOUT")
        }
      }

      trial("Nested Loop Join", nestedLoopJoin) 
      trial("Sort Merge Join ", sortMergeJoin) 
      trial("Hash Join       ", hashJoin) 
    }
  }

}