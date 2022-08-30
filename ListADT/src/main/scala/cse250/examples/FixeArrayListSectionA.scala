package cse250.examples
/**
 * cse250.examples.FixedArrayList.scala
 *
 * Copyright 2021 Oliver Kennedy (okennedy@buffalo.edu)
 *           2021 Andrew Hughes (ahughes6@buffalo.edu)
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 */

class FixeArrayListSectionA
  extends ListADT[String]
{
  val CAPACITY = 100
  var _size = 0
  val _data = new Array[String](CAPACITY)

  override def apply(idx: Int): String = 
  {
    if(idx >= _size || idx < 0){ throw new IndexOutOfBoundsException() }
    return _data(idx)
  }

  override def update(idx: Int, elem: String): Unit = 
  {
    if(idx >= _size || idx < 0){ throw new IndexOutOfBoundsException() }
    _data(idx) = elem
  }

  override def length: Int = 
  {
    return _size
  }

  override def insert(idx: Int, elem: String): Unit = 
  {
    if(idx < 0 || idx > _size){ throw new IndexOutOfBoundsException() }
    reserve(idx)
    var position = _size
    while(position > idx){
      _data(position) = _data(position-1)
      position -= 1 
    }
    _size += 1 
    _data(idx) = elem
  }

  override def remove(idx: Int): Unit = ???

  def reserve(requiredCapacity: Int): Unit = ???


  
}

