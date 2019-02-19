package ch.feuermurmel.libfabricate.openscad

trait CanCombine[A] {
  def combine(elements: Seq[A], fn: Seq[Expression] => Expression): A
}

object CanCombine {
  def combine[A](elements: Seq[A], fn: Seq[Expression] => Expression)(implicit cc: CanCombine[A]) =
    cc.combine(elements, fn)
}
