package cse250.examples
/**
 * cse250.examples.BreakItDownSpec.scala
 *
 * Copyright 2021 Oliver Kennedy (okennedy@buffalo.edu)
 *           2021 Andrew Hughes (ahughes6@buffalo.edu)
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 *
 */

import org.scalatest.flatspec.AnyFlatSpec

class FixedArrayListSpec extends AnyFlatSpec
{
  "FixedArraySequenceV1" should "allow updates" in {

    val list = new FixedArrayListV1();

    assert(list.length == 0)

    list.insert(0, "foo")
    assert("foo".equals(list(0)))
    assert(list.length == 1)

    list.insert(0, "bar")
    assert("bar".equals(list(0)))
    assert("foo".equals(list(1)))
    assert(list.length == 2)

    list.insert(1, "baz")
    assert("bar".equals(list(0)))
    assert("baz".equals(list(1)))
    assert("foo".equals(list(2)))
    assert(list.length == 3)

    list.remove(0)
    assert("baz".equals(list(0)))
    assert("foo".equals(list(1)))
    assert(list.length == 2)

    list.remove(1)
    assert("baz".equals(list(0)))
    assert(list.length == 1)
  }

  "FixedArraySequenceV2" should "allow updates" in {

    val list = new FixedArrayListV2();

    assert(list.length == 0)

    list.insert(0, "foo")
    assert("foo".equals(list(0)))
    assert(list.length == 1)

    list.insert(0, "bar")
    assert("bar".equals(list(0)))
    assert("foo".equals(list(1)))
    assert(list.length == 2)

    list.insert(1, "baz")
    assert("bar".equals(list(0)))
    assert("baz".equals(list(1)))
    assert("foo".equals(list(2)))
    assert(list.length == 3)

    list.remove(0)
    assert("baz".equals(list(0)))
    assert("foo".equals(list(1)))
    assert(list.length == 2)

    list.remove(1)
    assert("baz".equals(list(0)))
    assert(list.length == 1)
  }

}
