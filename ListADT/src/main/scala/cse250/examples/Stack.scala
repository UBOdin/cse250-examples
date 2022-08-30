package cse250.examples
/**
 * cse250.examples.Stack.scala
 *
 * Copyright 2021 Oliver Kennedy (okennedy@buffalo.edu)
 *           2021 Andrew Hughes (ahughes6@buffalo.edu)
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 */

class Stack[A](_store: ListADT[A])
{

  /**
   * Push a new element on top of the stack
   * @param     elem      The element to push on top of the stack
   */
  def push(elem: A): Unit = 
  {
    _store.insert(_store.length, elem)
  }

  /**
   * Remove the element from the top of the stack.  The 'top' element prior to
   * the current element being added becomes the new top, and we return the old 
   * top.  This is an error if the stack is empty.
   */
  def pop: A =
  {
    val ret = top
    _store.remove(_store.length - 1)
    return ret
  }

  /**
   * Return the top element of the stack.  This is an error if the stack is
   * empty.
   */
  def top: A = 
  {
    return _store(_store.length - 1)
  }

  /**
   * Check to see if the stack is empty
   */
  def isEmpty: Boolean = _store.length <= 0

}