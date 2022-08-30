/**
 * cse250.examples.AVLTree.scala
 *
 * Copyright 2021 Oliver Kennedy (okennedy@buffalo.edu)
 *           2021 Andrew Hughes (ahughes6@buffalo.edu)
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 */

sealed trait AVLBalance
case object AVLUnbalanced extends AVLBalance

sealed trait AVLValidBalance extends AVLBalance
{
  def increaseLeft: AVLBalance
  def increaseRight: AVLBalance
  def decreaseLeft = increaseRight
  def decreaseRight = increaseLeft
}

case object AVLLeftHeavy extends AVLValidBalance
{
  def increaseLeft: AVLBalance = AVLBalanced
  def increaseRight: AVLBalance = AVLUnbalanced
}
case object AVLBalanced extends AVLValidBalance
{
  def increaseLeft: AVLBalance = AVLLeftHeavy
  def increaseRight: AVLBalance = AVLRightHeavy
}
case object AVLRightHeavy extends AVLValidBalance
{
  def increaseLeft: AVLBalance = AVLUnbalanced
  def increaseRight: AVLBalance = AVLBalanced
}

sealed trait AVLBalanceChange

case object AVLHeightChanged extends AVLBalanceChange
case object AVLHeightUnchanged extends AVLBalanceChange


class AVLTree[T: Ordering]
{
  var root = Option[Node]

  def insert(v: T): Unit =
    if(root.isEmpty) { root = Node(v) }
    else { root = root.get.insert(v) }

  case class Node(
    val value: T, 
    var left: Option[Node] = None,
    var right: Option[Node] = None,
    var balance: AVLValidBalance = AVLBalanced
  )
  {
    def insert(cmp: T): (Node, AVLBalanceChange) =
    {
      if(Ordering[T].lteq(cmp, value)){
        if(left.isEmpty) { 
          // Nothing down the left side.  Insertion point found!
          left = Some(Node(cmp)) 

          balance = balance.increaseLeft match {
            case AVLUnbalanced => throw new AssertionError("Can't be left heavy with no left node")
            case b:AVLValidBalance => b
          }

          return (
            this,
            if(balance == AVLLeftHeavy){ AVLHeightChanged } else { AVLHeightUnchanged }
          )
        } else {

          left.get.insert(cmp) match {
            case (n, AVLHeightUnchanged) => 
              left = Some(n)
              return (this, AVLHeightUnchanged)

            case (n, AVLHeightChanged) => 
              balance.increaseLeft match {
                case b:AVLValidBalance => 
                  balance = b
                  return (
                    this,
                    if(balance == AVLLeftHeavy){ AVLHeightChanged } else { AVLHeightUnchanged }
                  )

                // Otherwise, the left subtree is now 2 deeper than the right.
                case AVLUnbalanced => 

                  // Case 1
              }


          }

        }


        if(left.isEmpty) { left = Some(Node[T](cmp, parent = this)) }
        else { left.get.insert(cmp) }
      } else {
        if(right.isEmpty) { right = Some(Node[T](cmp, parent = this)) }
        else { right.get.insert(cmp) }
      }
    }

    private def replaceChild(from: AVLTreeNode[N], to: AVLTreeNode[T])
    {
      if(left.isDefined && left.get.eq(from)){ left = Some(to) }
      else if(right.isDefined && right.get.eq(from)){ right = Some(to) }
      else { throw new IllegalArgumentException("Not a child") }
    }

    def rotateLeft(): Unit =
    {
      assert(right.isDefined, "Rotate left, without a right child")
      val centerSubtree = right.get.left
      ifparent.get.replaceChild()

    }
  }
}