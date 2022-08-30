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

import scala.collection.mutable
import scala.reflect.ClassTag

class FixedArrayListV1[A](implicit tag: ClassTag[A])
  extends ListADT[A]
{
  val CAPACITY = 100

  val _data = new Array[A](CAPACITY)

  override def apply(idx: Int): A = 
  {
    if(idx >= length){ throw new IndexOutOfBoundsException() }
    return _data(idx)
  }
  override def update(idx: Int, elem: A): Unit = 
  {
    if(idx >= length){ throw new IndexOutOfBoundsException() }
    _data(idx) = elem
  }

  override def insert(idx: Int, elem: A): Unit = 
  {
    if(idx >= CAPACITY-1){ throw new IndexOutOfBoundsException() }
    if(idx != 0 && _data(idx-1) == null){
      throw new IndexOutOfBoundsException()
    }
    if(_data.last != null){
      throw new OutOfMemoryError()
    }
    var last = _data(idx)
    _data(idx) = elem
    var position = idx+1
    while(_data(position) != null){
      val temp = _data(position)
      _data(position) = last
      last = temp
      position += 1
    }
    _data(position) = last
  }

  override def remove(idx: Int): Unit = 
  {
    if(idx >= CAPACITY){ throw new IndexOutOfBoundsException() }
    if(_data(idx) == null){ throw new IndexOutOfBoundsException() }
    var position = idx
    while(_data(position) != null){
      _data(position) = _data(position+1)
      position += 1
    }
  }

  override def length: Int = 
  {
    var position = 0;
    while(position <= CAPACITY && _data(position) != null){
      position += 1
    }
    return position
  }

}