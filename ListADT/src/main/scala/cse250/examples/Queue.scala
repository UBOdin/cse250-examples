package cse250.examples
/**
 * cse250.examples.Queue.scala
 *
 * Copyright 2021 Oliver Kennedy (okennedy@buffalo.edu)
 *           2021 Andrew Hughes (ahughes6@buffalo.edu)
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 */

class Queue[A](_store: ListADT[A])
{
  
  /**
   * Append a new element to the queue
   * @param     elem      The element to enqueue
   */
  def enqueue(elem: A): Unit = 
  {
    _store.insert(_store.length, elem)
  }

  /**
   * Remove the element from the front of the queue.  This is an error if the 
   * queue is empty.
   */
  def dequeue: A =
  {
    val ret = _store(0)
    _store.remove(0)
    return ret
  }

  /**
   * Return the frontmost element of the queue.  This is an error if the stack 
   * is empty.
   */
  def front: A = 
  {
    return _store(0)
  }

  /**
   * Check to see if the stack is empty
   */
  def isEmpty: Boolean = _store.length <= 0
}
