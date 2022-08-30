package cse250.examples
/**
 * cse250.examples.FixedArrayListV3.scala
 *
 * Copyright 2021 Oliver Kennedy (okennedy@buffalo.edu)
 *           2021 Andrew Hughes (ahughes6@buffalo.edu)
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 */

import scala.collection.mutable
import scala.reflect.ClassTag

class FixedArrayListV3[A](implicit tag: ClassTag[A])
  extends ListADT[A]
{
  var _capacity = 0
  var _data: Array[A] = null
  reserve(100)
  var _size = 0

  override def apply(idx: Int): A = 
  {
    if(idx >= _size){ throw new IndexOutOfBoundsException() }
    return _data(idx)
  }

  override def update(idx: Int, elem: A): Unit = 
  {
    if(idx >= _size){ throw new IndexOutOfBoundsException() }
    _data(idx) = elem
  }

  override def insert(idx: Int, elem: A): Unit = 
  {
    if(idx > _size){ throw new IndexOutOfBoundsException() }
    reserve(idx+1)
    for(shift <- _size.until(idx, -1)){
      _data(shift) = _data(shift-1);
    }
    _size += 1
    _data(idx) = elem
  }

  override def remove(idx: Int): Unit = 
  {
    if(idx >= _size){ throw new IndexOutOfBoundsException() }
    for(shift <- idx.until(_size-1)){
      _data(shift) = _data(shift+1)
    }
    _size -= 1
  }

  override def length: Int = 
    return _size

  def reserve(capacity: Int): Unit =
  {
    if(capacity <= _capacity){ return; }
    val amountToReserve = 
      if(capacity < 2 * _capacity){ 2 * _capacity }
      else { capacity }
    val newData = new Array[A](amountToReserve)
    for(i <- 0 until _size){
      newData(i) = _data(i)
    }
    _data = newData
  }

}