package cse250.util

import scala.concurrent.duration._
import scala.concurrent.{ Future, Await }

object Timing
{
  implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

  def time[A](label: String)(op: => A): A = 
  {
    val start = System.currentTimeMillis()
    val ret = op
    val end = System.currentTimeMillis()
    println(s"$label: ${end - start}ms")
    return ret
  }

  def withTimeout[A](timeout: Int)(op: => A): A =
  {
    val result = Future { op }
    Await.result(result, timeout.seconds)    
  }
}