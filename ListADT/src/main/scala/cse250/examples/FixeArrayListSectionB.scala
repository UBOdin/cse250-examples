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

class FixeArrayListSectionB
  extends ListADT[String]
{

  val _capacity = 100
  val _data = new Array[String](_capacity)
  var _size = 0

  override def apply(idx: Int): String =
  {
    if(idx < 0 || idx >= _size) { throw new IndexOutOfBoundsException() }
    return _data(idx)
  }
  override def update(idx: Int, elem: String): Unit = 
  {
    if(idx < 0 || idx >= _size) { throw new IndexOutOfBoundsException() }
    _data(idx) = elem
  }

  override def length: Int = _size

  override def insert(idx: Int, elem: String): Unit = 
  {
    reserve(_size + 1)
    if(idx < 0 || idx > _size) { throw new IndexOutOfBoundsException() }
    var position = _size
    while(position > idx) {
      _data(position) = _data(position - 1)
      position -= 1
    }
    _data(idx) = elem
    _size += 1
  }

  override def remove(idx: Int): Unit = ???

  def reserve(space: Int): Unit = ???


  
}
