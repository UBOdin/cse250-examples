package cse250.examples

import scala.util.Random



object DataGenerator
{
  def sales(
    elements: Int,
    keyRange: Int = 10
  ): Seq[SaleRecord] = 
    (0 until elements).map { _ => 
      SaleRecord(
        productId = Random.nextInt(keyRange),
        quantity = Random.nextInt(10)
      )
    }

  def prices(
    elements: Int,
  ): Seq[ProductPrice] =
    (0 until elements).map { i => 
      ProductPrice(
        productId = i,
        price = Random.nextInt(100)
      )
    }

}