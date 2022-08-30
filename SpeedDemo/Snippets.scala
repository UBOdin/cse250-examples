import scala.util.Random

def time[U](op: => U): U =
{
  val start = System.currentTimeMillis
  val ret = op
  val end = System.currentTimeMillis
  println(s"Took: ${(end - start) / 1000.0}s")
  return ret
}

/** 
 * "Scala-ify" the (Linked)List constructor
 */
def withAList(n: Int) = List.range(0, n)

/** 
 * "Scala-ify" the Array constructor
 */
def withAnArray(n: Int) = Array.range(0, n)

/**
 * For 10^1 through 10^6 records, time how long it takes to do 10k lookups
 */
def timeForTenThousandLookups(createSequence: Int => Seq[Int]) = 
{
  // Loop with exponent in 1, 2, 3, 4, 5, 6
  for(exponent <- 1 until 7) {

    // Compute the size of our collection as 10^exponent
    val size: Int = Math.pow(10, exponent).toInt

    // Let the user know what's happening
    println(s"Looking up 10,000 values in a list of size $size")

    // Use the provided function to create a collection of the desired size
    val elements = createSequence(size)

    // How long does it take to ...
    val result = time { 

      var total = 0l

      // ... make 10,000 ...
      for(i <- 1 until 10000){ 

        // ... random lookups to our collection
        total += elements(Random.nextInt(size)) 
      }
      
      /* result = */ total

    }
  }
}

timeForTenThousandLookups(withAList)
timeForTenThousandLookups(withAnArray)

