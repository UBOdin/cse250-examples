package cse250.examples.sectionA

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
        if(p.productId == s.productId){ result += ( (s, p) ) }
      }
    }

    result
  }
  
  def sortMergeJoin(sales: Seq[SaleRecord], prices: Seq[ProductPrice]): mutable.Buffer[(SaleRecord, ProductPrice)] =
  {
    val result = mutable.Buffer[(SaleRecord, ProductPrice)]()

    val sortedSales = sales.sortBy { _.productId }.iterator.buffered
    val sortedPrices = prices.sortBy { _.productId }.iterator.buffered

    while(sortedSales.hasNext && sortedPrices.hasNext){
      if(sortedSales.head.productId == sortedPrices.head.productId){
        result += ( (sortedSales.head, sortedPrices.head) )
        sortedPrices.next
      } else if(sortedSales.head.productId < sortedPrices.head.productId){
        sortedSales.next
      } else {
        sortedPrices.next
      }
    }

    result
  }

  def hashJoin(sales: Seq[SaleRecord], prices: Seq[ProductPrice]): mutable.Buffer[(SaleRecord, ProductPrice)] =
  {
    val result = mutable.Buffer[(SaleRecord, ProductPrice)]()

    val pricesTable = new mutable.HashMap[Int, ProductPrice]()
    for(p <- prices){
      pricesTable(p.productId) = p
    }

    for(s <- sales){
      result += ( (s, pricesTable(s.productId)) )
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