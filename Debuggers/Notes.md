### x, y are Doubles

* How to read exceptions

### Malformed URL

* IntelliJ Debugger
  * Breakpoints
  * Inspecting state

### Count is Wrong

* Coming up with a Minimal Reproduction in a Test Case
```scala
  behavior of "BreakItDown"
  it should "count 38 baked goods" in {
    val data: Seq[BreakItDown.FarmersMarket] =
      BreakItDown
        .readData("AMS-USDA-Directories-FarmersMarkets.csv")
//        .drop(1)                        // drop the header
        .map { BreakItDown.parseFarmersMarket(_) }  // parse records
    assert(BreakItDown.countTheBakedGoods(data) == 38, "The right number of baked goods")
  }
```
```scala
    assert(data(0).name.equals("Alexandria Bay Farmers Market"), data(0).name)
    assert(data(0).sellsBakedGoods, "Alexandria Bay Sells Baked Goods")
    assert(data.size == 87, "The right number of baked goods")
```

### DataStructureChoice

```scala
fork in run := true
javaOptions in run += "-agentlib:hprof=cpu=samples"
javaOptions in run += "-agentlib:hprof=cpu=samples,depth=10"
```
