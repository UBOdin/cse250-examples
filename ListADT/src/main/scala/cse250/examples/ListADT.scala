package cse250.examples
/**
 * cse250.examples.ListADT.scala
 *
 * Copyright 2021 Oliver Kennedy (okennedy@buffalo.edu)
 *           2021 Andrew Hughes (ahughes6@buffalo.edu)
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 */

/**
 * A simple ADT describing mutable sequences of strings.
 */
trait ListADT[A]
{
  /**
   * Insert an element at the specified position
   * @param    idx     The position to insert at
   * @param    elem    The element to insert
   */
  def insert(idx: Int, elem: A): Unit

  /**
   * Remove the element at the specified position
   * @param    idx     The position of the element to remove
   */
  def remove(idx: Int): Unit

  /**
   * Retrieve the length of the represented sequence
   * @return           The length of the sequence
   */
  def length: Int

  /**
   * Retrieve the element at the specified position
   * @param    idx     The position of the element to retrieve
   * @return           The element at position idx
   */
  def apply(idx: Int): A

  /**
   * Retrieve the element at the specified position
   * @param    idx     The position of the element to replace
   * @param    elem    The element to replace
   */
  def update(idx: Int, elem: A): Unit
}
